# Spring Boot Rest API with H2 DB,JPA and Lombok

### A simple rest api using 
  <br/>**Spring Boot 3.2.1**
  <br/>**Java 17**
  <br/>**H2 Database**
  <br/>**Custom Exception handling**
  <br/>**unit test**

## How to run the Application

**Start the application using any of the below mentioned command**

> **Note: command need to run inside the root folder of this project**

- **using maven** <br/>``` mvn spring-boot:run```

- **from jar file**
   Create a jar file using '**mvn clean install**' command and then execute
   <br/> ``` java -jar target/rest-demo-0.0.1-SNAPSHOT.jar```

- **Browser or REST client**
  <br/>```http://localhost:8080/api/transaction/1```
  <br/> Body ```{ "transactionId": "123456789", "accountNumber": "545343423" }```


- **cURL**
  <br/>```curl --location --request POST 'http://localhost:8080/api/transaction/save' \
  --header 'Content-Type: application/json' \
  --data-raw '{
  "transactionId": "123456789",
  "accountNumber": "545343423"
  }'```


## How to Run Unit Test Cases

**Run the test cases using any of the commands mentioned below**

> **Note:** These commands need to run inside the root folder of this project i.e inside the **rest-demo** folder

- **To run all the test cases**
  <br/>```mvn test```


- **To run a particular test class**
   <br/>```mvn -Dtest=RestDemoApplicationTests test```
   <br/>or
   <br/>```mvn -Dtest=TransactionControllerTest test```


