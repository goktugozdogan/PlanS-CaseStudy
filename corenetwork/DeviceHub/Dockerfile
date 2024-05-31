FROM maven:3.9.6 as build
RUN mkdir -p /app/DeviceHub/deploy
WORKDIR /app

COPY pom.xml ./
COPY DeviceHub/src DeviceHub/src
COPY DeviceHub/pom.xml DeviceHub
COPY SharedHub/src SharedHub/src
COPY SharedHub/pom.xml SharedHub

RUN mvn clean install -P DeviceHub -DskipTests

FROM openjdk:17-jdk
WORKDIR /app
COPY --from=build /app/DeviceHub/deploy/*.jar app.jar
EXPOSE 8086
ENTRYPOINT ["java", "-Xmx400m", "-jar","/app/app.jar"]
