FROM eclipse-temurin:17-jdk-focal
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} life-blog.jar
ENTRYPOINT ["java","-jar","/life-blog.jar"]