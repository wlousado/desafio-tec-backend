FROM adoptopenjdk/openjdk11
COPY build/libs/desafio-tec-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
