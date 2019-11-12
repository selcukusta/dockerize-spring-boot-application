FROM maven:3.6.2-jdk-8-slim as builder
COPY src /usr/src/labs/src
COPY pom.xml /usr/src/labs
RUN mvn -f /usr/src/labs/pom.xml clean package

FROM gcr.io/distroless/java:8
COPY --from=builder /usr/src/labs/target/sample-0.0.1-SNAPSHOT.jar /usr/app/sample-0.0.1-SNAPSHOT.jar  
EXPOSE 8080  
ENTRYPOINT ["java","-jar","/usr/app/sample-0.0.1-SNAPSHOT.jar"]  