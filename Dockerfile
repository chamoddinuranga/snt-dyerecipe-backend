#FROM eclipse-temurin:17-jdk-focal
#
#WORKDIR /app
#
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#RUN ./mvnw dependency:go-offline
#
#COPY src ./src
#
#CMD ["./mvnw", "spring-boot:run"]

#FROM openjdk:17-jdk
#
#COPY target/snt-dye-backend-0.0.1-SNAPSHOT.jar .
#
#EXPOSE 8080
#
#ENTRYPOINT ["java",".jar","snt-dye-backend-0.0.1-SNAPSHOT.jar"]