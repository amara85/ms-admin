FROM eclipse-temurin:17

WORKDIR /app

COPY target/ms-admin-0.0.1-SNAPSHOT.jar /app/ms-admin-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "ms-admin-0.0.1-SNAPSHOT.jar"]