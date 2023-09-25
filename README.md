# Loan Calculator
The Loan Calculator bases on payback time, desired amount and interest rate

# Requirements
**The task should be solved using Java as the programming language. The solution should be returned with full source.**  

# Task description
Create an application which can be used for calculation of the cost from a housing loan. 
The application should have a simple user interface (web or console) where the user can specify the desired amount and the payback time in years. For simplicity we assume a fixed
interest of 3.5% per year during the complete payback time. The interest should be connected to the loan type in such a manner that different loan types can have different
interests. When selecting amount and payback time, the application should generate a monthly payback plan based on the series loan principle, i.e. you pay back an equal amount 
each month and add the generated interest. The interest is calculated every month.   

The application should be made in such a manner that it can easily be extended to calculate a payment plan for other types of loans, for instance car loan, spending loan etc. with
different interests, nominal value and payback time. Also bear in mind that it should be easy to extend the application to cover other payback schemes as well. 
We do not expect this to be implemented.  

We will evaluate code quality and correctness of the application.

# Solution
The solution is based on Spring Boot and Spring Data JPA. The application is a REST service with a single endpoint. The endpoint is used to calculate the loan payment plan.

# Example Case
The following example case is used to test the application:
* Loan amount: 120000.00
* Loan period: 10
* Loan type: EDUCATION_LOAN
* Interest rate: 0.035
* Monthly payment: 1186.63


## DataBase
The application is using H2 in-memory database.
The database interface can be found after application start at http://localhost:8080/h2-console
The JDBC URL is jdbc:h2:mem:loan-calculator
The username is sa
The password is empty

# REST API
The application is using Swagger to document the REST API.
## Swagger UI
The Swagger UI can be found after application start at http://localhost:8080/swagger-ui.html
All REST API endpoints can be tested using Swagger UI.

## Postman collection
The Postman collection attached to the project. [The collection](src/main/resources/loan-calculator.postman_collection.json)  contains the following requests:
* Calculate loan payment plan
* Fetch all calculated loan payment plans

## Call API By Console
The application can be called by console using curl command:
* Calculate loan payment plan
```
curl -X 'POST' \
  'http://localhost:8080/api/v1/loans/monthly/payments/calculate' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
   "loanAmount": 120000,
    "loanPeriod": 10,
    "loanType": "EDUCATION_LOAN",
    "interestRate": 1.6
}

```
* Fetch all calculated loan payment plans
```
curl -X 'GET' \
  'http://localhost:8080/api/v1/loans/monthly/payments?loanType=EDUCATION_LOAN' \
  -H 'accept: application/json'
```

## How to build
The application can be build using the following command:
```
mvn clean install
```
## How to run
The application can be run using the following command:
```
mvn spring-boot:run
```
## How to test
The application can be tested using the following command:
```
mvn test
```