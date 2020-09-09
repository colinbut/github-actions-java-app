FROM openjdk:8
COPY target/github-actions-java-app-*-SNAPSHOT.jar /usr/local/bin/app.jar
RUN chmod +x /usr/local/bin/app.jar
CMD ["java", "-jar", "/usr/local/bin/app.jar"]