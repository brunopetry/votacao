# Votação de assembléias API REST

#### Tecnologias utilizadas
- Java 8;
- Spring boot;
- Hibernate;
- Swagger;
- Maven;
- Banco de dados MySql;
- Git;
- Heroku;

#### Aplicação disponível no heroku
https://lit-earth-87604.herokuapp.com/swagger-ui/

#### Utilizando aplicação localmente
Endereço local:
http://localhost:8080/swagger-ui/

Para executar localmente existem dois métodos:
##### Método 1 - Heroku
- Instalar The Heroku CLI, disponível em: https://devcenter.heroku.com/articles/heroku-cli
- Instalar o Spring Boot CLI, disponível em: https://docs.spring.io/spring-boot/docs/current/reference/html/cli.html
- Adicionar o jdk 8 no path
- Adicionar o Maven no path

Depois de terminar as instalações, basta executar o seguintes comandos:

1. Compilar projeto utilizando o Maven:
> mvn clean install

2. Startar o projeto utilizando o heroku:
> heroku local

##### Método 2 - Utilizando o eclipse
1. Basta importar o projeto maven no eclipse;
2. Executar o maven install;
3. Executar o projeto;

#### Acesso ao banco de dados
Banco de dados disponibilizado pelo heroku.

Os dados de conexão, usuário e senha estão disponíveis no arquivo "src\main\resources\application.properties"




