# JuniorWorkersAPI

Restfull API provider for the Junior Workers project

JuniorWorkersAPI front-end web app can be found at https://github.com/JohnDelta/junior_workers

## About
junior workers

# Build Info

## Maven project

## Requirements
- JDK 1.8+
- Maven 3.8+

## dependencies
- jaxrs-ri (Jersey)
- org.json
- MySQL connector java
- jjwt

## Setup the MySQL Database (DB name: junior_workers, User: juniorworkers_user, Password: juniorworkers_password)
- Localhost mysql server is set to localhost:3306. Change it from com.junior_workers.database_controllers.Database.java if you use a different host
- Login to MySQL command line
- Find the folder: JuniorWorkersAPI/DatabaseScript/script.sql and run it to your MySQL command line to create the database
- Create a new controller account for the new database

` create user 'juniorworkers_user'@'%' identified by 'juniorworkers_password'; `

- Give user the necessary privileges

` grant all on junior_workers.* to 'juniorworkers_user'@'%'; `

## Install API (setup DB first required)
- clone the project to a location and open it

` git clone https://github.com/JohnDelta/9laugh_api.git `

` cd 9laugh_api `

- build and run the project locally on your local server
	
# API Requests (local testing server: localhost:8080/api)

## login
- url: localhost:8080/api/user/login
- method: POST
- content-type: application/json
- body: {username: "", password: ""}
- returns: application/json

## account create
- url: localhost:8080/api/user/create
- method: POST
- content-type: application/json
- body: {email: "", password: "", firstname: "", lastname: "", role: ""}
- returns: application/json









