## ExchangeAPI Project - Ahmet Colak
This project consist of simple Currency Converter project that have below features:

- Get Currency Exchange Rates
- Convert Currency
- Viewing the currency exchange rate
- Viewing the currency exchange rate history
- Viewing the currency exchange rate history of specific date
- Viewing the currency exchange rate history of specific date range
- Viewing the currency exchange rate history of specific date range with specific currency

### TOOLS & TECH
- Java 11
- Spring Boot 2.5.6
    - Spring Data MongoDB
    - Spring Data Rest
    - Spring Data Web
- MongoDB
- Docker
- SpringDoc OpenApi
- Lombok
- Maven
- Junit 5, Mockito

### 1.1 How To Install
##### Note: you should have installed docker. https://www.docker.com/products/docker-desktop
##### Note2 : You should have minimum Java 11 version and Maven.

first clone or download project then

`cd currency-converter--main`

### 1.2 How to Run
Run **runProject.sh** file in root directory

    > chmod +x runProject.sh 
    
    > ./runProject.sh

*You don't need to mongodb separately, docker file contains mongodb image, and it can set required settings as well.

Run tests
```
$ cd currency-converter-main
$ mvn test
```

## 1.3 Usage  

#### Sample Requests:  #####
http://localhost:8080/exchange-api/get-exchange-rate-list/TRY/EUR,USD,BRL

http://localhost:8080/exchange-api/convert-currency/12/EUR/TRY


#### Swagger (You can also test api via swagger in browser)
http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/


![img.png](img.png)