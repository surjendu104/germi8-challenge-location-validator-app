FROM openjdk:17-jdk
EXPOSE 8080
ADD target/location-validator.jar location-validator.jar
ENTRYPOINT ["java","-jar","/location-validator.jar"]