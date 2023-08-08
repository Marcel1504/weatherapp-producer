ARG IMAGE
FROM $IMAGE
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]