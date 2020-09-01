FROM openjdk:11
COPY ./build/libs/ /tmp
WORKDIR /tmp
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "./desafio-0.0.1-SNAPSHOT.jar"]