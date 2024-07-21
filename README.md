# DEMO MICROSERVICES


### ***[Postman Documentation](https://documenter.getpostman.com/view/11695309/2sA3kUH2qW)***

___________________________________

### Run the project with docker

- Download all the docker images from the following links:


- Run the following command to start the containers:


**Acount Services**
```bash
docker run -d -p 8081:8081 bp-account-services
```

**Client Services**
```bash
docker run -d -p 8082:8082 bp-client-services
```

**Api Gateway**
```bash
docker run -d -p 8080:8080 bp-api-gateway
```

**Discovery Server**
```bash
docker run -d -p 8761:8761 bp-discovery-server
```