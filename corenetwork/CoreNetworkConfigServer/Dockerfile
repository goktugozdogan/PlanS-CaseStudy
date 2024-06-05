FROM maven:3.9.6 as build
RUN mkdir -p /app/deploy
WORKDIR /app

COPY src src
COPY pom.xml pom.xml

RUN mvn clean install -DskipTests

FROM openjdk:17-jdk
WORKDIR /app
COPY --from=build /app/deploy/*.jar app.jar
EXPOSE 8086
ENTRYPOINT ["java", "-Xmx400m", "-jar","/app/app.jar"]
