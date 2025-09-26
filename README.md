# Testes Automatizados - Advantage Online API

Este projeto usa Java + Cucumber + RestAssured para automatizar testes de API do site Advantage Online Shopping.

O foco principal é testar endpoints de produtos, incluindo pesquisa de produtos e atualização de imagens de produtos via API.

Para mais informações, consulte a documentação da API em https://www.advantageonlineshopping.com/api/docs/

---

## Pré-requisitos

- **Java 17+**
- **Maven**

Verifique com:
```bash
java -version
mvn -version

1. Clone do repositório:

git clone https://github.com/seu-usuario/Advantage-Online-API.git
cd Advantage-Online-API

2. Instale as dependências do Maven:

mvn clean install

Todas as dependências (Cucumber, RestAssured, Hamcrest, JUnit) estão configuradas no pom.xml.

Estrutura dos Testes

Cucumber Features:
Localizadas em src/test/resources/features.
Contêm os arquivos .feature com cenários de teste da API:

Pesquisar-Produto.feature → pesquisa de produtos
Atualizar-Produto.feature → atualização de imagem de produto

Steps Definitions:
Localizadas em src/test/java/advancedonlineapi/ProductSteps.java.
Contêm a implementação dos steps do Cucumber, incluindo:

Login e captura de token JWT

Requisições GET e POST com RestAssured

Validação de status code e conteúdo do JSON

Imagens e Recursos:
Localizadas em src/test/resources/images/.
Exemplo: Beats Studio_IMG.jpg usado para atualizar imagem do produto

Execução dos Testes

Rodar todos os cenários: mvn test

Rodar um cenário específico:
Exemplo: mvn test -Dcucumber.options="--name 'POST /api/v1/product/image/467625264/45/BLACK/?product_id=15 - atualizar imagem de produto'"

Ou poderão ser executados por alguma IDE de sua preferência:
IntelliJ IDEA, Visual Studio Code ou outros

Contato

Para dúvidas ou contribuições:

Desenvolvedor: Claudio Alves

E-mail: claudio_alvesmith@hotmail.com