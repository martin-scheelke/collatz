# Collatz
_by Martin Scheelke_
---
&nbsp;
&nbsp;

## Install and Test Instructions 

&nbsp;
&nbsp;

#### Clone
 
> Clone this repo to your local machine using:
```
`https://github.com/martin-scheelke/collatz.git`
```

&nbsp;  

#### Setup

> - Install the gradle wrapper: 
  ```shell script
  gradle wrapper --gradle-version 6.3
  ```
> - Clean the project:
 ```shell script
  gradle clean
  ```

> - If persistence support is required: Install PostgreSQL v10.3 and start the PostGreSQL service
> - Set the flyway username and password in build.gradle and initialise the collatz.database:
 ```shell script
  gradle clean flywayClean flywayMigrate
  ```

&nbsp;

#### Run Unit and Integration Tests with in-memory caching

&nbsp;

> - Edit the properties file at ./collatz/src/main/resources/.properties:
> - Specify the service class: Set service.impl=collatz.restapi.service.ServiceImpl

```shell script
 gradlew test --info
 ```

> Test outputs can be found at ../collatz/build/reports/tests/test/index.html

&nbsp;

#### Run Unit and Integration Tests using persistence

&nbsp;
> - Edit the properties file at ./collatz/src/main/resources/.properties:
> - Set username and password for the collatz.database. e.g. postgres.user=postgres,  postgres.pass=
> - Specify the service class: Set service.impl=collatz.restapi.service.PersistentServiceImpl

```shell script
 gradlew test --info
 ```

> Test outputs can be found at ../collatz/build/reports/tests/test/index.html

&nbsp;
> - Edit the properties file at ./collatz/src/main/resources/.properties
> - Set  username and password for the collatz.database and ensure there is a public scheam

> - To run the tests using in-memory caching:
```shell script
 gradlew test --info
 ```

&nbsp;

#### Manually test the REST API

> Start the REST service:

```shell script
gradlew runREST
```

> Test with curl:
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
This is only available for in-memory caching as the collatz.database environment is not setup.

&nbsp;

#### Code Style

- Java Coding style is according to http://google.github.io/styleguide/