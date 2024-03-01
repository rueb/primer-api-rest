# FROM  openjdk:11
#VOLUME [ "/data" ]
#EXPOSE 8080
# ADD ./target/mi-primer-api-rest-1.0.jar app.jar
#ENTRYPOINT [ "java","-jar","/app.jar" ]
FROM openjdk:11
VOLUME /tmp
#EXPOSE 8080
EXPOSE 8092
ARG JAR_FILE=target/mi-primer-api-rest-1.0.jar 
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]