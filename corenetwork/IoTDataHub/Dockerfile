FROM maven:3.9.6 as build
RUN mkdir -p /app/IoTDataHub/deploy
WORKDIR /app

COPY pom.xml ./
COPY IoTDataHub/src IoTDataHub/src
COPY IoTDataHub/pom.xml IoTDataHub
COPY SharedHub/src SharedHub/src
COPY SharedHub/pom.xml SharedHub

RUN mvn clean install -P IoTDataHub -DskipTests

FROM openjdk:17-jdk
WORKDIR /app
COPY --from=build /app/IoTDataHub/deploy/*.jar app.jar
EXPOSE 8088
ENTRYPOINT ["java", "-Xmx400m", "-jar","/app/app.jar"]
