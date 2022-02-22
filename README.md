# boatApplication
Sample Boat Application


# boat_app_client

## This is a Frontend Application.

* This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 12.2.0.

* Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

* Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.

* To login in `localhost:4200`, use 
    * username: `openwt`
    * password: `password`

# boat_app

## This is a Backend Application.

### This project was generated from https://start.spring.io/ with
 * Maven 3.6.3
 * Open JDK 11.0.13

### To run this App:

*  run  `mvn clean package` to generate *.jar file in target folder.
* run `java -jar target/boat_app-0.0.1-SNAPSHOT.jar` to run the application.
* The application is run in port 8080.

## Test database
* The database console can be accessed in `localhost:8080/h2-console` enabled for test purposes only.

* Configuraion can be found in `application.properties`

* By default, the database is empty with new deployment.

* Copy file `spring-boot-h2-db.mv.db` to your home directory to use the sample data.

## Deployment in Docker

The backend `boat_app` can also be deployed in docker.

### To deploy in a docker container 

* run `mvn clean package`
* run `docker build -t boat_app .` to build the docker image
* run `docker run -d -p 8080:8080 --name boat_app_container boat_app` to deploy in a docker container.

