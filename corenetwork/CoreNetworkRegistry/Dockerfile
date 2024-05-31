FROM openjdk:22-jdk
VOLUME /tmp
ARG JAR_FILE=deploy/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Xmx400m", "-jar","/app.jar"]
EXPOSE 8761