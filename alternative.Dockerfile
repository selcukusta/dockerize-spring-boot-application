FROM gcr.io/distroless/java:8
COPY target/sample-0.0.1-SNAPSHOT.jar /usr/app/labs/sample-0.0.1-SNAPSHOT.jar
EXPOSE 8080  
ENTRYPOINT ["java","-jar","/usr/app/labs/sample-0.0.1-SNAPSHOT.jar"]