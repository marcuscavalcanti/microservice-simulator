# Microservice Simulator

The goal of this project, is to act as a Microservice simulator, so you can simulate success and error scenarios. This project also simulates latency conditions, that is very useful to check if your retry and exponential backoff policies are working as expected.

You can configure which status codes will be returned. In the same way, you can configure a latency range.

## Configuring

Enter in **src/main/resources** directory and edit **application.properties** file.

Default values:


```
microservice.latency=10, 18, 25, 67, 85, 130, 200, 300, 500, 1000
microservice.httpStatusCode=NO_CONTENT, OK, ACCEPTED, CREATED, INTERNAL_SERVER_ERROR, SERVICE_UNAVAILABLE
```

In **httpStatusCode** property, you have to use Spring's [HttpStatus](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/http/HttpStatus.html) enum values.

## Running

Do this sequence of steps (command line):

```
mvn install
java -jar target/*.jar
time curl -v localhost:8080/mock
```

You can also use POST, PUT and DELETE verbs.
