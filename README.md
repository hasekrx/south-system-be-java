## Análise de dados

* Esse projeto foi desenvolvido com base no teste https://github.com/rh-southsystem/desafio-hyperflame-desenvolvedor

## Objetivo do projeto
* Esse projeto ira criar um relatorio de saida com os dados:
  * Quantidade de clientes no arquivo de entrada
  * Quantidade de vendedor no arquivo de entrada
  * ID da venda mais cara
  * O pior vendedor 

### Para configurar e executar

* Instalar [Java JDK 11+](https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html)
* Instalar [Maven 3.8.5] (https://maven.apache.org/download.cgi)
* crie um folder data/in e data/on localizado em **%HOME_PATH%**
* execute o commando **mvn-spring-boot:run** para executar a aplicação
* Adicione um novo file *.dat no folder data/in e a aplicação ira gerar um report de saída no folder data/out
* Um arquivo de entrada exemplo esta localizado na pasta data/in

### Para executar com debug 

* execute o comand **mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"**


### Para executar os testes

* execute o comando **mvn test**


#### Tecnologias 

* Java 11
* Spring Boot 2.6.7
* Apache Camel 3.16.0