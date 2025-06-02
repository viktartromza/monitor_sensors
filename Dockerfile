FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /opt/ms
COPY pom.xml ./
COPY ./src ./src
RUN mvn clean install

FROM maven:3.8.4-openjdk-17
WORKDIR /opt/ms
COPY --from=builder /opt/ms/target/*.jar /opt/ms/*.jar
EXPOSE 8585
ENTRYPOINT ["java", "-jar", "/opt/ms/*.jar"]