# Collatz REST API
_by Martin Scheelke_
---
&nbsp;
&nbsp;

A very simple REST API/microservice for asynchronous calculation of Collatz series terms - see 
[Collatz Conjecture](https://en.wikipedia.org/wiki/Collatz_conjecture).
The interface returns immediately when it receives an instruction to calculate a Collatz term. The 
result can then be retrieved by polling the interface.
 
This project demonstrates the following ideas:

- Spark Java - a lightweight, drop-in HTTP server.
- Dependency injection without using a framework such as Spring.
- Kotlin's tail recursion optimisation - Java and Kotlin are combined in single Gradle project.
- Test Driven Development.
- CI/CD with Docker and CircleCI.
- RESTAssured for declarative, JUnit driven REST interface tests.

## Install and Test Instructions 

&nbsp;
&nbsp;

#### Clone
 
> Clone this repo to your local machine:
```
https://github.com/martin-scheelke/collatz.git
```

&nbsp;  

#### Setup

> - Install the gradle wrapper: 
  ```shell script
  gradle wrapper --gradle-version 6.3
  ```

&nbsp;

#### Run unit and integration tests data store access

&nbsp;

> - Edit the properties file at ./collatz/src/main/resources/.properties:
> - Specify the DAO class: Set collatz.data.CollatzDAOImpl

```shell script
 gradlew clean test --info
 ```

&nbsp;

#### Run unit and integration tests with thread safe data store


&nbsp;
> - Edit the properties file at ./collatz/src/main/resources/.properties
> - Specify the DAO class: Set collatz.data.ConcurrentCollatzDAOImpl 

> - To run the tests using in-memory caching:
```shell script
 gradlew clean test --info
 ```

&nbsp;+
> - 
```shell script
 gradlew clean test --info
 ```

> Test outputs can be found at ../collatz/build/reports/tests/test/index.html


&nbsp;

#### Manually test the REST API

> Start the REST service:

```shell script
gradlew runREST
```

> Example tests with curl:
```shell script
curl -X PUT http://localhost:4567/collatz/323
curl -X GET http://localhost:4567/collatz/323
curl -X GET http://localhost:4567/collatz
curl -X DELETE http://localhost:4567/collatz/323
curl -X DELETE http://localhost:4567/collatz
```
&nbsp;

#### CircleCi integration

For CI/CT integration with CircleCi - build and test in a docker image - see:
&nbsp;
https://app.circleci.com/pipelines/github/martin-scheelke/collatz

&nbsp;

#### Code Style

- The Java code style conforms to http://google.github.io/styleguide/