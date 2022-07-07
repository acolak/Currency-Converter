FROM openjdk:11
MAINTAINER acolak
VOLUME /tmp
EXPOSE 8080
ADD target/currency-converter-0.0.1-SNAPSHOT.jar CurrencyConverter_docker.jar
ENTRYPOINT ["java","-jar","/CurrencyConverter_docker.jar"]