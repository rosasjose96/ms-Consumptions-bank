FROM openjdk:8
VOLUME /temp
EXPOSE 8094
ADD ./target/ms-Consumptions-bank-0.0.1-SNAPSHOT.jar consumption-service.jar
ENTRYPOINT ["java","-jar","/consumption-service.jar"]