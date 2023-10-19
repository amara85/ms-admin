FROM eclipse-temurin:17

LABEL mentainer="amara.ghoufa@gmail.com"

WORKDIR /app

COPY target/springboot-restful-webservices-0.0.1-SNAPSHOT.jar /app/springboot-restful-webservices.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "springboot-restful-webservices.jar"]