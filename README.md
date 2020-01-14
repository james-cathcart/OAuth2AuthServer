# Spring OAuth2 Minimalist Demo

This is an evolving project meant to provide a working example of Spring's Oauth2 implementation.

# How To Run
There is a directory called _docker_ in the root of the project. In this directory are three files: a Dockerfile, postgres sql dump, and a database initialization script. These will be used to build a postgres image pre-polulated with our defult test user (admin@fakemail.com).

## Build the Docker Image
Navigate _docker_ directory and run this command:

`docker build -t oauth-postgres .`

## Create & Run the Container
`docker run --name oauth-db -p 5432:5432 -e POSTGRES_PASSWORD=devP@ss -d oauth-postgres`

## Run the Project
Import the project into IntelliJ (or your IDE of choice). Now run the project. 

## Try it out with the Postman Collection
Use the Postman collection in the root directory of the project to test the various endpoints. You must first use the post request to obtain an access token. Once a token is received, copy the access_token and use it as the Bearer token on the Get User Info Postman request. This is the endpoint that other protected resources can use to validate access tokens.

### Secured Endpoints 
There are also three endpoints that are secured via the WebSecurityConfigurerAdapter. The Postman Collection has requests for these endpoints. Simply paste the access_token from the Initial OAuth token request into the Bearer token Authorization section of the Postman request.

## Known Issues
There is currently only one user and the entity relationship in the database is incorrect. A OneToMany relationship was used when a ManyToMany relationship should have been used. This means no additional users and authorities can be added due to the constraints Hibernate has put on the join table. That being said, that user has all authorities and can be used to hit the protected AppController endpoints.


