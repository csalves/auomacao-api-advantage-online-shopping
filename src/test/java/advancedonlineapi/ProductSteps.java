package advancedonlineapi;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.File;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ProductSteps {

    private Response response;
    private String token;

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

        response.then().statusCode(200);

        token = response.jsonPath().getString("statusMessage.token");
    }

    @Given("base uri {string}")
    public void base_uri(String uri) {
        RestAssured.baseURI = uri;
    }

    @When("envia GET {string} com query params")
    public void i_send_get_with_query_params(String endpoint, DataTable dataTable) {
        Map<String, String> queryParams = dataTable.asMap(String.class, String.class);

        response = RestAssured
                .given()
                .queryParams(queryParams)
                .when()
                .get(endpoint);
    }

    @When("envia POST {string}")
    public void i_send_post(String endpoint) {
        File image = new File("src/test/resources/images/Beats Studio_IMG.jpg");

        response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .multiPart("file", image)
                .when()
                .post(endpoint);
    }

    @Then("status é {int}")
    public void status_is(Integer statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("response contém o produto {string}")
    public void response_contains_product(String expectedProductName) {
        String actualName = response.jsonPath().getString("products[0].productName");
        assertThat(actualName, equalTo(expectedProductName));
    }

    @Then("response contém {string}")
    public void response_contains(String expectedMessage) {
        String actualMessage = response.jsonPath().getString("reason");
        assertThat(actualMessage, equalTo(expectedMessage));
    }
}
