# Prices
_Autor: José Luis Ganfornina García_

### Resumen
- [Contenido](#Contenido)
- [Architecture and methodology](#Architecture-and-methodology)
- [Stack tecnológico](#Stack)
- [Documentación](#Documentación)
- [Instrucciones](#Instrucciones)
- [Casos de uso](#Casos-de-uso)

## Contenido

Implementación de un microservicio que expone un endpoint que dado unos criterios de búsqueda, devuelve el precio del producto buscado.

## Architecture and methodology

La aplicación ha sido diseñada usando una arquitectura limpia, lo que ofrece una mejor organización del código, mantenibilidad y testabilidad. Específicamente he usado una arquitectura hexagonal.


## Stack

El stack tecnológico que he usado para la implementación del microservicio es:

- JDK 11
- Spring Boot 2.5.0
- Maven
- Junit 5
- Mockito
- Swagger
- Lombok
- Base de datos en memoria H2


## Documentación

Para generar la documentación necesaria para hacer uso de la API-REST se ha añadido y configurado la librería de Swagger que expone una interfaz web que permite consultar la documentación y realizar pruebas.
Swagger está disponible en:

http://localhost:8080/swagger-ui/


## Instrucciones

Para facilitar la ejecución del proyecto se ha añadido maven wrapper. Este wrapper permite el uso de maven sin tenerlo instalado en el sistema. Además, su uso nos permite asegurar que todos los integrantes del equipo están usando la misma versión de maven.
En cualquier caso, el uso de maven wrapper no es obligatorio y podemos usar los comandos de maven.

A continuación se describen los comandos necesarios con maven y maven wrapper

Ejecutar los tests:

    mvn clean test 
    
    mvnw clean test


Ejecutar el microservicio:

    mvn clean spring-boot:run
    
    mvnw clean spring-boot:run


Crear el jar:

    mvn clean package
    
    mvnw clean package


Ejecutar el microservicio desde el jar

    java -jar ./target/kairos-prices-0.0.1-SNAPSHOT.jar


## Casos de uso

El único caso de uso implementado hasta el momento es el de la búsqueda del precio de un producto para una fecha concreta
Para probar el caso de uso, además de swagger podemos probarlo accediendo a la siguiente url:

    http://localhost:8080/api/prices?productId=35455&brandId=1&applicationDate=2020-06-14T16:00:00

Esta llamada al endpoint nos devolvería el siguiente json:

    {
        "priceId": 2,
        "brandId": 1,
        "productId": 35455,
        "startDate": "2020-06-14T15:00:00",
        "endDate": "2020-06-14T18:30:00",
        "priority": 1,
        "price": 25.45,
        "currency": "EUR"
    }
