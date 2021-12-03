# Movie API
## Projeto teste de API RESTful com carregamento inicial da base de dados em banco em memória H2, através de arquivo csv embarcado. Criação das tabelas na inicialização.

---

### Desenvolvimento
Java 11 - Spring boot ver2.6.1 - Spring Tools 4.6.0

---

### Dependências

```xml
<dependencies>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-data-rest</artifactId>
	</dependency>
	<dependency>
   		<groupId>org.springframework.boot</groupId>
   		<artifactId>spring-boot-starter-data-jpa</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-devtools*</artifactId>
		<scope>runtime</scope>
		<optional>true</optional>
	</dependency>
	<dependency>
		<groupId>com.h2database</groupId>
		<artifactId>h2</artifactId>
		<scope>runtime</scope>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-test</artifactId>
		<scope>test</scope>
	</dependency>
</dependencies>
```
\* devtools opcional

---

### Recurso disponível
Endpoint [GET] : http://localhost:8080/v1/movies/intervalo-premios\
Response: 200

```json
{
    "min": [
        {
            "producer": "Joel Silver",
            "interval": 1990,
            "previousWin": 1991,
            "followingWin": 1
        },
        {
            "producer": "Bo Derek",
            "interval": 1984,
            "previousWin": 1990,
            "followingWin": 6
        }
    ],
    "max": [
        {
            "producer": "Buzz Feitshans",
            "interval": 1985,
            "previousWin": 1994,
            "followingWin": 9
        },
        {
            "producer": "Matthew Vaughn",
            "interval": 2002,
            "previousWin": 2015,
            "followingWin": 13
        }
    ]
}
```
### Requisitos
- JDK 11

### Execução
- git clone https://github.com/koneski/movieapi.git\
- Run As Spring Boot App...

---
### Banco H2
- Console: http://localhost:8080/h2-console\
- JDBC URL: jdbc:h2:mem:moviesdb\

O console H2, pode ser desabilitado no arquivo ../resources/application.properties, modificando a linha spring.h2.console.enabled=true para false.

---

### Testes de Integração
movieapi/src/test/java/com/movieapi/MovieapiApplicationTests.java
- Run as JUnit test
---
