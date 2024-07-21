# DEMO MICROSERVICES


### ***[Postman Documentation](https://documenter.getpostman.com/view/11695309/2sA3kUH2qW)***

___________________________________

### Run the project with docker

- Download all the docker images from the following links:
    
    - [Discovery Server](https://hub.docker.com/r/joewatsonsbf/bp-discovery-server) 
    - [Account Services](https://hub.docker.com/r/joewatsonsbf/bp-account-services)
    - [Client Services](https://hub.docker.com/r/joewatsonsbf/bp-client-services)
    - [Api Gateway](https://hub.docker.com/r/joewatsonsbf/bp-api-gateway)



Run the following command to start the containers:


**Acount Services**
```bash
docker run -d -p 8081:8081 joewatsonsbf/bp-account-services
```

**Client Services**
```bash
docker run -d -p 8082:8082 joewatsonsbf/bp-client-services
```

**Api Gateway**
```bash
docker run -d -p 8080:8080 joewatsonsbf/bp-api-gateway
```

**Discovery Server**
```bash
docker run -d -p 8761:8761 joewatsonsbf/bp-discovery-server
```