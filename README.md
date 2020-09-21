# ms-fiap-pedido

API utilizada para entrega de fase 6 Containerization Strategy do MBA
da FIAP de [Arquitetura de Soluções](https://www.fiap.com.br/online/mba/mba-em-arquitetura-de-solucoes/).

O mercado de startups brasileiras vem crescendo cada vez mais, e em 2019 já
tivemos novos unicórnios (termo utilizado para startups que chegaram no
valor de mercado de 1 bilhão de dólares), algumas delas são: 

* **99**: Aplicativo de transporte disruptivo, sendo uma das principais
concorrentes da Uber no Brasil.
* **iFood**: Abrange grande parte do mercado de delivery de comida por aplicativo,
tornando o modo de pedir comida mais simplificado.
* **Nubank**: Fintech pioneira no segmento financeiro, inovou com seu cartão de
crédito sem anuidade e serviços com a NuConta.
* **PagSeguro**: Nascida do grupo UOL, a Fintech é referência de inovação e
qualidade no acirrado mercado de meios de pagamentos presenciais com suas
maquininhas.

Imagine agora que você é o Arquiteto de Soluções um desses 4 unicórnios de
sucesso, e a sua missão é:

Implementar um Microsserviço em Docker de uma aplicação presente no diagrama
arquitetural proposto. O código do Microsserviço deve ser hospedado em um
GitHub público e conter as camadas Domain, Repository, Service e Controller.

## GitHub flow

O projeto utiliza o fluxo de versionamento [GitHub flow](https://guides.github.com/introduction/flow/).

## Pré requisitos

### Java 11

<details><summary><b>Instruções</b></summary>

O Java 11 pode tanto ser instalado através da JDK contida no site
da [Oracle](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
ou no site do [OpenJDK](https://openjdk.java.net/projects/jdk/11/)

Como alternativa é possível utilizar o [SDKMan](https://sdkman.io/)
e instalar o Java através do comando:

```console
foo@bar:~$ sdk install java <version>
```

Para listagem de todas as versões do Java disponíveis, execute o comando:

```console
foo@bar:~$ sdk list java
```

</details>

### Gradle (opcional)

<details><summary><b>Instruções</b></summary>

O projeto foi concebido para que a instalação do Gradle fosse opcional,
para tanto, é possível rodar as configurações do projeto após instalação
do Java pelos arquivos **gradle.bat** em sistemas Windows e **gradlew**
 em sistemas Unix, que interagem com o arquivo **gradle-wrapper.jar**
 contido na pasta **gradle/wrapper** na raiz do projeto.

Caso mesmo assim se deseje rodar o projeto pelo Gradle na máquina,
o mesmo pode ser instalado através do [site](https://gradle.org/install/).

Como alternativa é possível utilizar o [SDKMan](https://sdkman.io/)
e instalar o Maven através do comando:

```console
foo@bar:~$ sdk install gradle
```

Para listagem de todas as versões do Gradle disponíveis, execute o comando:

```console
foo@bar:~$ sdk list gradle
```

</details>

## Estrutura

```console
.
├── build
│     ├── generated
│     │     ├── openapi-code-server
│     │     │     └── src
│     │     │         └── main
│     │     │             └── java
│     │     │                 └── org
│     │     │                     └── openapi
│     │     │                         └── cambio
│     │     │                             └── server
│     │     │                                 ├── api
│     │     │                                 │     ├── ApiUtil.java
│     │     │                                 │     └── V1Api.java
│     │     │                                 └── model
│     │     │                                     ├── Erro.java
│     │     │                                     ├── Item.java
│     │     │                                     ├── ItemPedidoRequest.java
│     │     │                                     ├── ParametroInvalido.java
│     │     │                                     ├── PedidoId.java
│     │     │                                     ├── Pedido.java
│     │     │                                     └── PedidoPostRequest.java
│     │     └── source
│     │           └── kapt
│     │                 └── main
│     │                       └── br
│     │                           └── com
│     │                               └── fiap
│     │                                   └── mba
│     │                                       └── mspedido
│     │                                           ├── converters
│     │                                           ├── dtos
│     │                                           └── models
│     ├── libs
│     │     └── ms-fiap-pedido-0.0.1-SNAPSHOT.jar
│     └── reports
│           └── detekt
├── build.gradle
├── config
│     ├── detekt
│     └── sonarqube
│         └── sonarqube-h2.yml
├── docker-compose.yml
├── Dockerfile
├── gradle
│     └── wrapper
│         ├── gradle-wrapper.jar
│         └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
├── plugins
│     ├── configs
│     │     └── detekt
│     │         └── config.yml
│     ├── docs.gradle
│     ├── ides.gradle
│     ├── jacoco.gradle
│     ├── kotlin.gradle
│     ├── lint.gradle
│     ├── mapstruct.gradle
│     ├── openapi.gradle
│     ├── security.gradle
│     └── sonarqube.gradle
├── README.md
├── settings.gradle
└── src
    ├── main
    │     ├── kotlin
    │     │     └── br
    │     │         └── com
    │     │             └── fiap
    │     │                 └── mba
    │     │                     └── mspedido
    │     │                         ├── configs
    │     │                         ├── converters
    │     │                         ├── dtos
    │     │                         ├── exceptions
    │     │                         ├── factories
    │     │                         ├── models
    │     │                         ├── repositories
    │     │                         ├── resources
    │     │                         │     └── impl
    │     │                         ├── services
    │     │                         │   └── impl
    │     │                         └── MsPedidoApplication.kt
    │     └── resources
    │         ├── application-dev.yaml
    │         ├── application.yaml
    │         ├── i18n
    │         │     └── messages_pt.properties
    │         └── openapi
    │             └── pedido-api.yaml
    └── test
        └── kotlin
            └── br
                └── com
                    └── fiap
                        └── mba
                            └── mspedido
```

## Arquitetura

- [Gradle](https://gradle.org/)
- [Kotlin](https://kotlinlang.org/)
- [Mapstruct](http://mapstruct.org/)
- [MongoDB](https://www.mongodb.com/)
- [Open API 3.0](https://swagger.io/specification/)
- [Open API Code Generator](https://github.com/OpenAPITools/openapi-generator/tree/master/modules/openapi-generator-gradle-plugin)
- [Spring Boot 2.3](https://projects.spring.io/spring-boot/)
- [Webflux](https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html)

## Execução

### Clone

```console
foo@bar:~$ git clone https://github.com/arthurfnsc/ms-fiap-pedido.git
foo@bar:~$ cd ms-fiap-pedido
```

### Execução Linux | Windows

O projeto pode ser executado em ambiente Linux ou Windows, sendo os comandos
diferenciando por duas opções Linux e Windows respectivamente

```console
foo@bar:ms-fiap-pedido [./gradlew | gradlew.bat] clean build 
```

Isso gerará um arquivo **JAR** que usaremos com o **Dockerfile** e
**docker-compose**

Em seguida é preciso rodar o **docker-compose**

```console
foo@bar:ms-fiap-pedido docker-compose up --build
```

A aplicação estará rodando na porta **8080** em 
[http://localhost:8080/mspedido/swagger-ui/](http://localhost:8080/mspedido/swagger-ui/)

Foram criadas 2 rotas, a fim de exercitar os conceitos:

### POST /mspedido/v1/pedidos

Exemplo de payload:

```json
{
  "cpf": "00000000000",
  "horario_entrega": "2020-09-20T23:44:03.089Z",
  "id_cupom": "123",
  "id_endereco": "456",
  "id_forma_pagamento": "789",
  "id_restaurante": "987",
  "itens": [
    {
      "id_item": "123",
      "observacao": "minha observacao a",
      "quantidade": 2
    },
    {
      "id_item": "456",
      "observacao": "minha observacao b",
      "quantidade": 4
    }
  ]
}
```

Exemplo de request

```curl
curl -X POST "http://localhost:8080/mspedido/v1/pedidos" \
-H  "accept: application/json" -H  "Content-Type: application/json" \
-d "{\"cpf\":\"00000000000\",\"horario_entrega\":\"2020-09-20T23:44:03.089Z\",\"id_cupom\":\"123\",\"id_endereco\":\"456\",\"id_forma_pagamento\":\"789\",\"id_restaurante\":\"987\",\"itens\":[{\"id_item\":\"123\",\"observacao\":\"minha observacao a\",\"quantidade\":2},{\"id_item\":\"456\",\"observacao\":\"minha observacao b\",\"quantidade\":4}]}"
```

### GET /mspedido/v1/pedidos/{id}

Exemplo de request

```curl
curl -X GET "http://localhost:8080/mspedido/v1/pedidos/5f67e9918b7efc2640093105" \
-H  "accept: application/json"
```

## Qualidade de Código

- [ArchUnit](https://www.archunit.org/)
- [AssertJ](https://assertj.github.io/doc/)
- [Detekt](https://github.com/arturbosch/detekt)
- [JaCoCo](https://www.eclemma.org/jacoco/)
- [JUnit 5](https://junit.org/junit5/)
- [Markdown Lint](https://github.com/appmattus/markdown-lint)
- [Mockito](https://site.mockito.org/)
- [PITest](https://pitest.org/)
- [SonarQube](https://www.sonarqube.org/)

## Relatórios

- [Verificar versão de dependência](https://github.com/ben-manes/gradle-versions-plugin)

```console
foo@bar:ms-fiap-pedido [./gradlew | gradlew.bat] dependencyUpdates
```

- [Documentação classes](https://github.com/Kotlin/dokka)

```console
foo@bar:ms-fiap-pedido [./gradlew | gradlew.bat] dokka
```

- [Projeto](https://docs.gradle.org/current/userguide/project_report_plugin.html)

```console
foo@bar:ms-fiap-pedido [./gradlew | gradlew.bat] projectReport
```

## Segurança

- [OSS Index](https://github.com/OSSIndex/ossindex-gradle-plugin/)

```console
foo@bar:ms-fiap-pedido [./gradlew | gradlew.bat] audit
```

- [OWASP](https://jeremylong.github.io/DependencyCheck/dependency-check-gradle/index.html)

```console
foo@bar:ms-fiap-pedido [./gradlew | gradlew.bat] dependencyCheckAggregate
```

## Testes

```console
foo@bar:ms-fiap-pedido [./gradlew | gradlew.bat] test
```

### JaCoCo

A task de **test** está associada à task **jacocoTestReport** (para mais
informações **plugins/jacoco.gradle**)

### PITest

Os [Testes de Mutantes](https://blog.caelum.com.br/testes-de-mutantes/amp/)
são bem úteis para se descobrir comportamentos inesperados no nosso código que
não estão cobertos. 

Para executá-los no projeto utilize a task:

```console
foo@bar:ms-fiap-pedido [./gradlew | gradlew.bat] pitest
```

### SonarQube

O **SonarQube** é uma ferramenta de análise estática de código. Nesse projeto
colocamos um arquivo com o [docker-compose](https://docs.docker.com/compose/compose-file/)
na pasta **config/sonarqube/sonarqube-h2.yml** caso se deseje executar a
análise em ambiente local. Para tanto, execute os seguintes comandos:

```console
foo@bar:ms-fiap-pedido docker-compose -f config/sonarqube/sonarqube-h2.yml up
```

O **SonarQube** estará disponível na porta **9000**. Para o usuário defaut
o login é **admin** e a senha é **admin**.

Com o **SonarQube** em execução rode o comando:

```console
foo@bar:ms-fiap-pedido [./gradlew | gradlew.bat] sonarqube
```

## Principais problemas

### Erros ao importar projeto em IDE

É comum ao importar o projeto em uma IDE que classes contidas nos
pacotes **org.openapi.cambio.server** apresentem erros de compilação.

Isso ocorre porque ao utilizar a estratégia de API First é necessário
a geração das classes para que o projeto possa compilar.

A geração das classes se encontra atrelada ao goal **compileKotlin** no
ciclo de vida do **Gradle**, e pode ser melhor detalhado em  
**"plugins/openapi.gradle** na raiz do projeto.

```groovy
compileKotlin.dependsOn(
    generateApiServer
)

sourceSets.main.java.srcDir "$apiServerOutput/src/main/java"
```

![Gradle Lifecycle](/imgs/lifecycle.png)

Uma das formas de resolver o problema é a execução da task **compileKotlin**

```console
foo@bar:ms-fiap-pedido [./gradlew | gradlew.bat] compileKotlin
```

Ou a execução de outras tasks que tenham relação direta com o **compileKotlin**,
como **build** ou **bootRun**.

As classes geradas se encontrarão no diretório **org.openapi.cambio.server**
dentro do projeto **application/rest-api**

```console
.
└── build
    └── generated
        └── openapi-code-server
            └── src
                └── main
                    └── java
                        └── org
                            └── openapi
                                └── cambio
                                    └── server
                                        ├── api
                                        └── model
```

Mesmo após a geração de classes, é comum algumas IDEs ainda não sincronizarem
as novas classes no projeto aberto, para tanto, lembre-se de sincronizar o  
projeto para que as novas classes entrem no classpath do projeto, e com isso,  
possam ser importadas por outras classes.
