FROM adoptopenjdk/openjdk11:aarch64-debian-jre-11.0.16_8
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]