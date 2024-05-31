FROM maven:3.9.6 as build
RUN mkdir -p /app/UserHub/deploy
WORKDIR /app

COPY pom.xml ./
COPY UserHub/src UserHub/src
COPY UserHub/pom.xml UserHub
COPY SharedHub/src SharedHub/src
COPY SharedHub/pom.xml SharedHub

RUN mvn clean install -P UserHub -DskipTests

FROM openjdk:17-jdk
WORKDIR /app
COPY --from=build /app/UserHub/deploy/*.jar app.jar
EXPOSE 8087
ENTRYPOINT ["java", "-Xmx400m", "-jar","/app/app.jar"]
