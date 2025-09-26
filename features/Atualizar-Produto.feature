#language:en

  Feature: Atualizar Produto

    Scenario: POST /api/v1/product/image/467625264/45/BLACK/?product_id=15 - atualizar imagem de produto
      Given esteja logado "/accountservice/accountrest/api/v1/login"
      And base uri "https://www.advantageonlineshopping.com"
      When envia POST "/catalog/api/v1/product/image/467625264/45/BLACK/?product_id=15"
      Then status é 200
      And response contém "Product was updated successful"