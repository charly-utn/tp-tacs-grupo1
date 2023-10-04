FROM maven:3.8-openjdk-17-slim

COPY . /tp-tacs

WORKDIR /tp-tacs

RUN mvn package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/tacs-grupo1-1.0-SNAPSHOT.jar"]
