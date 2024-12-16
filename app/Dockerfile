FROM maven:3.9.8-eclipse-temurin-21 AS build

WORKDIR /build

COPY src /build/src
COPY pom.xml /build/

RUN mvn clean package -Dmaven.test.skip 

FROM openjdk:21 AS runner

COPY --from=build build/target/web-0.0.1-SNAPSHOT.jar /web.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "web.jar"]
