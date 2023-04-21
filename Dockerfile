# mvn package -Dmaven.test.skip=true
FROM openjdk:20-jdk
VOLUME /main-app
ADD ./target/userservice-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENV APP_DB_URI jdbc:mariadb://database:3306/demoapp
ENV APP_DB_USERNAME demoapp
ENV APP_DB_PASSWORD demoapp
ENTRYPOINT ["java", "-jar","/app.jar"]
