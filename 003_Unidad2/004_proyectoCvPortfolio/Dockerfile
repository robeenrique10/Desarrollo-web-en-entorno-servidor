# Etapa 1: Compilación de la aplicación
FROM maven:3.9.6-eclipse-temurin-27 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final ligera para ejecución
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Vercel inyectará la variable $PORT automáticamente
EXPOSE 80

ENTRYPOINT ["java", "-jar", "app.jar"]