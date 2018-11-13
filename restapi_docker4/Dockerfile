FROM openjdk:8-jdk

ADD restapi-0.0.1.jar restapi-0.0.1.jar
RUN sh -c 'touch /restapi.jar'
ENTRYPOINT ["java", "-Dspring.data.mongodb.uri=mongodb://restapi_docker4/Downloads","-Djava.security.egd=file:/dev/./urandom","-jar","/restapi-0.0.1.jar"]