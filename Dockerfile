FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/github-calculator-1.0.jar
COPY ${JAR_FILE} github-calculator-1.0.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/github-calculator-1.0.jar"]