package advancedonlineapi;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ProductSteps {

    private Response response;
    private String token;
    private static final String REPORT_PATH;
    private static String currentScenario = "";

    // Cria um nome único para o arquivo de log usando data e hora
    static {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
        REPORT_PATH = "target/logs/api-report-" + timestamp + ".txt";
    }

    /**
     * Função auxiliar que salva informações no relatório (arquivo .txt).
     * Sempre que essa função é chamada, ela escreve um título e um conteúdo no log.
     */
    private void salvarLog(String titulo, String conteudo) {
        try (FileWriter writer = new FileWriter(REPORT_PATH, true)) {
            writer.write("[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "] ");
            writer.write(titulo + "\n");
            writer.write(conteudo + "\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Given("esteja logado {string}")
    public void que_esteja_logado(String loginEndpoint) {
        String body = "{\n" +
                "  \"email\": \"adminirai@examplii.com\",\n" +
                "  \"loginPassword\": \"Sla1533!\",\n" +
                "  \"loginUser\": \"jowjarazr\"\n" +
                "}";

        response = RestAssured
                .given()
                .baseUri("https://www.advantageonlineshopping.com")
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(loginEndpoint);

        // Salva o request e o response no relatório
        salvarLog(currentScenario + " → LOGIN REQUEST", body);
        salvarLog(currentScenario + " → LOGIN RESPONSE", response.prettyPrint());

        response.then().statusCode(200);

        // Extrai o token do JSON de resposta
        token = response.jsonPath().getString("statusMessage.token");
    }

    @Given("base uri {string}")
    public void base_uri(String uri) {
        RestAssured.baseURI = uri;
        salvarLog(currentScenario + " → BASE URI", uri);
    }

    @When("envia GET {string} com query params")
    public void i_send_get_with_query_params(String endpoint, DataTable dataTable) {
        // Pega os dados da tabela do Cucumber e transforma em um formato que a API consegue entender (chave → valor)
        Map<String, String> queryParams = dataTable.asMap(String.class, String.class);

        response = RestAssured
                .given()
                .queryParams(queryParams)
                .when()
                .get(endpoint);

        // Salva a resposta no relatório
        salvarLog(currentScenario + " → GET " + endpoint, response.prettyPrint());
    }

    @When("envia POST {string}")
    public void i_send_post(String endpoint) {
        File image = new File("src/test/resources/images/Beats Studio_IMG.jpg");

        response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token) // adiciona o token no header
                .multiPart("file", image) // envia o arquivo com a imagem para atualizar o produto
                .when()
                .post(endpoint);

        salvarLog(currentScenario + " → POST " + endpoint, response.prettyPrint());
    }

    @Then("status é {int}")
    public void status_is(Integer statusCode) {
        response.then().statusCode(statusCode);
        salvarLog(currentScenario + " → STATUS CODE",
                "Esperado: " + statusCode + " | Obtido: " + response.getStatusCode());
    }

    @Then("response contém o produto {string}")
    public void response_contains_product(String expectedProductName) {
        String actualName = response.jsonPath().getString("products[0].productName");

        salvarLog(currentScenario + " → VALIDAÇÃO PRODUTO",
                "Esperado: " + expectedProductName + " | Obtido: " + actualName);

        assertThat(actualName, equalTo(expectedProductName));
    }

    @Then("response contém {string}")
    public void response_contains(String expectedMessage) {
        String actualMessage = response.jsonPath().getString("reason");

        salvarLog(currentScenario + " → VALIDAÇÃO RESPONSE",
                "Esperado: " + expectedMessage + " | Obtido: " + actualMessage);

        assertThat(actualMessage, equalTo(expectedMessage));
    }
}
