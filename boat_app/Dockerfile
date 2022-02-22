FROM adoptopenjdk/openjdk11:latest
RUN mkdir /opt/app
COPY target/boat_app-0.0.1-SNAPSHOT.jar /opt/app
CMD ["java", "-jar", "/opt/app/boat_app-0.0.1-SNAPSHOT.jar"]

