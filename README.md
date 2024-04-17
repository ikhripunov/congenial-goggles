# congenial-goggles
This service allows to create users and search for users by their last name via REST API. 

### Assumptions:
* The service assumes that every created user is a new unique user
* The only required field for the user is lastName
* Search only does exact match
* Search requires lastName to be 1 character or more
* No pagination for search

## Requirements
* Java 21
* Kotlin 1.9.23
* Gradle 8.7

## How to run
Run the following command to build and run:
```bash
./gradlew openApiGenerate bootRun
```

The result would be a running dev server available on `http://localhost:8080/v1/users`

## How to build
Run the following command to build artefact:
```bash
./gradlew openApiGenerate bootRun
```
alternatively one can use Docker image:
```bash
docker run --rm -u gradle -v "$PWD":/home/gradle/project -w /home/gradle/project gradle gradle openApiGenerate build  
```

## API use
Service can be found on `http://localhost:8080/v1/users`
### Creating users
To create user:
```bash
curl -X POST -H Content-Type:\ application/json -d '{"firstName":"Foo", "lastName":"Bar", "email":"foobar@gmail.com"}' http://localhost:8080/user   
```
That should return 204 No Content.
Wrong user (no lastName) should result in 400 Bad Request.
### Searching for users
To search for users with lastName "Foo":
```bash
curl http://localhost:8080/user\?lastName\=Foo      
```
That should return 200 OK and a JSON array of User objects:
```json
[
  {
    "id": "UUID",
    "firstName": "String",
    "lastName": "String",
    "email": "String"
  }
]
```
Wrong query without lastName value should result in 400 Bad Request.