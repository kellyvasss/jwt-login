# jwt-login

## Pre Requiers

* MySQL and create a database named "mydatabase"
* JDK 1.8
* Internet connection
* Choose your admin username and password in the "Login" file located in resources package 
* Environment variables for the username and password for your MySQL database-connection.
* To avoid setting up environment varibles, set your name and password directly in the properties file.
* Any IDE that accepts Java and SpringBoot, for example IntelliJ, Eclipse or NetBeans (This is built with IntelliJ).

## Run the applicatiation
* If you want to test the diffrent endpoints (GET, PUT, DELETE, POST) you need the have Postman/Insomnia or any other service that allows you to test API's.
* The route for the endpoints always starts with "http://localhost:8080",then it will be different routes, for example ("http://localhost:8080/login") to make the loginrequest.

## Security configuratiation
By default, the first user you are creating (in the code, provided the username and password value in the loginfile) will have the "ADMIN" role, meaning you can access all the endpoints included in the project. 
Others will have "USER" role and their access will be limited. 

## Links to download all the neccesarys

* POSTMAN: https://www.postman.com/downloads/
* INSOMNIA: https://insomnia.rest/download
* MYSQL + INSTALLATIONGUIDE: https://www.mysql.com/downloads/ | https://www.youtube.com/watch?v=k5tICunelSU
* IntelliJ (windows): https://www.jetbrains.com/idea/download/?fromIDE=&section=windows



