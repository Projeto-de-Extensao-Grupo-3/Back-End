FROM openjdk:26-ea-21-oracle
COPY API_CRUDBackend/target/API_CRUDBackend-0.0.1-SNAPSHOT.jar /
WORKDIR /
EXPOSE 8080
CMD ["java","-jar","./API_CRUDBackend-0.0.1-SNAPSHOT.jar"]