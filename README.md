# congenial-goggles

## How to build
```
./gradlew openApiGenerate build
```

## How to run
```
java -jar build/libs/counties-energy-1.0-SNAPSHOT.jar
```

## API use
See api-spec.yml for OpenAPI spec reference.
TL;DR:
`POST /user` with JSON `{"surname": "Foo", "firstName": "Bar", "emailAddress": "taz@jazz.com"}` to create user
`GET /user?lastName=Foo` to search for users with last name "Foo"