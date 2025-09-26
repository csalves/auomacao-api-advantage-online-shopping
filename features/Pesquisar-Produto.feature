#language:en

Feature: Pesquisar Produto

  Scenario: GET /api/v1/products/search - pesquisar produto por nome
    Given base uri "https://www.advantageonlineshopping.com"
    When envia GET "/catalog/api/v1/products/search" com query params
      | name                    | HP ElitePad 1000 G2 Tablet |
      | quantityPerEachCategory | -1                         |
    Then status é 200
    And response contém o produto "[HP ElitePad 1000 G2 Tablet]"