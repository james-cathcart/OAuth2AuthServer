# Spring OAuth2 Minimalist Demo

This is an evolving project meant to provide a working example of Spring's Oauth2 implementation.

# How To Run
There is a directory called _docker_ in the root of the project. In this directory are three files: a Dockerfile, postgres sql dump, and a database initialization script. These will be used to build a postgres image pre-polulated with our default test users (admin@fakemail.com, clerk@fakemail.com, user@fakemail.com).

## Build the Docker Image
Navigate `docker/database` directory and run this command:

```
docker build -t oauth-postgres .
```

## Create & Run the Container
```
docker run --name oauth-db -p 5432:5432 -e POSTGRES_PASSWORD=devP@ss -d oauth-postgres
```

## Run the Project
Import the project into IntelliJ (or your IDE of choice). 

### Add the ENCRYPT_KEY Environment Variable
In IntelliJ, edit your run configuration and add an environment variable called ENCRYPT_KEY and give it a value. This value is an encryption/decryption key for Spring's Cloud Config server. Do not lose this key as you cannot decrypt the property values without it.

Now, run the project. 

## Try it out with the Postman Collection

### Get User Details from Token
Use the Postman collection in the root directory of the project to test the various endpoints. You must first use the post request to obtain an access token. Once a token is received, copy the access_token and use it as the Bearer token on the Get User Info Postman request. This is the endpoint that other protected resources can use to validate access tokens.

### Secured Endpoints 
There are also three endpoints that are secured via the WebSecurityConfigurerAdapter. The Postman Collection has requests for these endpoints as well as requests to generate tokens with different Authorities for testing. Simply paste the access_token from the desired OAuth token request into the Bearer token Authorization section of the Postman request.
