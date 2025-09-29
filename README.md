# Testes Automatizados - Advantage Online API

Este projeto usa Java + Cucumber + RestAssured para automatizar testes de API do site Advantage Online Shopping.

O foco principal é testar endpoints de produtos, incluindo pesquisa de produtos e atualização de imagens de produtos via API.

Para mais informações, consulte a documentação da API em https://www.advantageonlineshopping.com/api/docs/

Além disso, cada execução gera logs em arquivo TXT, permitindo acompanhar cada passo do teste como evidência.


---

## Pré-requisitos

- **Java 17+**
- **Maven**

Para verificar a instalação e suas versões, abra o terminal (Prompt de Comando, PowerShell ou Terminal do Linux/macOS) e digite os comandos abaixo:
```sh
java -version
```
```sh
mvn -version
```

### 1. Clone do repositório:
```sh
git clone https://github.com/seu-usuario/Advantage-Online-API.git
```

```sh
cd Advantage-Online-API
```

### 2. Instale as dependências do Maven:
```sh
mvn clean install
```

Todas as dependências (Cucumber, RestAssured, Hamcrest, JUnit) estão configuradas no pom.xml.

## Estrutura dos Testes

### Cucumber Features:

Localizadas na pasta features, contêm os arquivos .feature com cenários de teste da API:

**Pesquisar-Produto.feature** - pesquisa de produtos

**Atualizar-Produto.feature** - atualização de imagem de produto

### Steps Definitions:

Localizadas em src/test/java/advancedonlineapi/ProductSteps.java, contêm a implementação dos steps do Cucumber, incluindo:

- Login e captura de token JWT

- Requisições GET e POST com RestAssured

- Validação de status code e conteúdo do JSON

- Criação de log de execução em .txt, durante a execução, cada step registra informações de request e response nesse arquivo
(arquivo gerado em target/logs/api-report-DDMMAAAA_HHMMSS.txt)

## Imagens e Recursos:

Localizadas em src/test/resources/images/

Exemplo: Beats Studio_IMG.jpg usado para atualizar imagem do produto

## Execução dos Testes

Os testes poderão ser executados por alguma IDE de sua preferência:

IntelliJ IDEA, Visual Studio Code ou outros

## Contato

### Para dúvidas ou contribuições:

Desenvolvedor: Claudio Alves

E-mail: claudio_alvesmith@hotmail.com