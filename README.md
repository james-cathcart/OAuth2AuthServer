# Spring OAuth2 Minimalist Demo

This is an evolving project meant to provide a working example of Spring's Oauth2 implementation.

# How To Run
There is a directory called _docker_ in the root of the project. In this directory are three files: a Dockerfile, postgres sql dump, and a database initialization script. These will be used to build a postgres image pre-polulated with our defult test user (admin@fakemail.com).

## Build the Docker Image
Navigate _docker_ directory and run this command:

`docker build -t oauth-postgres .`

## Create & Run the Container
`docker run --name oauth-db -p 5432:5432 -e POSTGRES_PASSWORD=devP@ss -d oauth-postgres`

## Set Active Profile for Run Configuration
Import the project into IntelliJ (or your IDE of choice). Now run the project. 

## Try it out
Use the Postman collection in the root directory of the project to test token endpoint. Once a token is received, copy the access_token and use it as the Bearer token on the Get User Info Postman request. This is the endpoint that other protected resources can use to validate access tokens.

There is currently only one user and the entity relationship is incorrect in the database so no additional users and authorities can be added due to the constraints Hibernate has put on the fields. That being said, that user has all authorities and can be used to hit the protected AppController endpoints.


