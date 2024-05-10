FROM openjdk:17

WORKDIR /app

COPY target/OrdersService-0.0.1-SNAPSHOT.jar /app/OrdersService.jar

EXPOSE 8010

ENTRYPOINT ["java", "-jar", "OrdersService.jar"]