FROM openjdk:14.0-jdk
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} search-0.0.1.jar
ENTRYPOINT ["java","-jar","/search-0.0.1.jar"]