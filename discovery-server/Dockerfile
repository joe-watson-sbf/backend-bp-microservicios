FROM openjdk:17

COPY target/*.jar app.jar
COPY src/main/resources/application.yml application.yml

EXPOSE 8761/tcp

ENTRYPOINT ["java","-jar","/app.jar"]