# Golden Raspberry API

API RESTful em Java com Spring Boot que importa dados do Golden Raspberry Awards e fornece estatísticas dos produtores com maiores e menores intervalos entre prêmios.

## Requisitos

- Java 21
- Maven

## Como rodar

```bash
mvn spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

## Como rodar os testes

```bash
mvn test
```

## Endpoint

**GET /producers/intervals**

Retorna os produtores com maior e menor intervalo entre vitórias na categoria Pior Filme.
