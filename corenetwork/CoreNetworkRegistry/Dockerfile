FROM maven:3.9.6 as build
RUN mkdir -p /app/CoreNetworkRegistry/deploy
WORKDIR /app

COPY CoreNetworkRegistry/src CoreNetworkRegistry/src
COPY CoreNetworkRegistry/pom.xml CoreNetworkRegistry

RUN mvn clean install -DskipTests

FROM openjdk:17-jdk
WORKDIR /app
COPY --from=build /app/CoreNetworkRegistry/deploy/*.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java", "-Xmx400m", "-jar","/app/app.jar"]
