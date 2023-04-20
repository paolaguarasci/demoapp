FROM openjdk:20-jdk
VOLUME /main-app
ADD ./target/florencedemo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENV FLORENCE_APP_DB_URI jdbc:mariadb://database:3306/florence
ENV FLORENCE_APP_DB_USERNAME florence
ENV FLORENCE_APP_DB_PASSWORD florence
ENTRYPOINT ["java", "-jar","/app.jar"]
