# Dockerize Spring Boot Application

You can copy the required files into the build context and create the image with `main.Dockerfile`. Also, you can create `.jar` package on the host machine and use it into the build context with `alternative.Dockerfile`.

> :warning: In order to specify different `.dockerignore` file for different `Dockerfile`, you need to enable [**Experimental Features**](https://docs.docker.com/docker-for-mac/faqs/#experimental-features) on **Docker**.

## Run

### Create Bridge Network

- `docker network create labs`

### Run MongoDB Container

- `docker container run --name spring-boot-server -p 27017:27017 -d --network labs mongo:3.6.15-xenial`

### Build Docker Image from Spring Boot App

- `DOCKER_BUILDKIT=1 docker build -t selcukusta/labs-sample:1.0.0 -f main.Dockerfile .`

```
 => [internal] load build context
 => => transferring context: 7.02kB
```

**_or_**

- `./mvnw clean package`
- `DOCKER_BUILDKIT=1 docker build -t selcukusta/labs-sample-alternative:1.0.0 -f alternative.Dockerfile .`

```
 => [internal] load build context
 => => transferring context: 23.16MB
```

### Run Spring Boot App Container

- `docker container run --name spring-boot-app -p 8080:8080 -e SPRING_DATA_MONGODB_HOST=spring-boot-server -e SPRING_DATA_MONGODB_DATABASE=Employee -d --network labs selcukusta/labs-sample:1.0.0`

  **_or_**

- `docker container run --name spring-boot-app -p 8080:8080 -e SPRING_DATA_MONGODB_HOST=spring-boot-server -e SPRING_DATA_MONGODB_DATABASE=Employee -d --network labs selcukusta/labs-sample-alternative:1.0.0`

### Insert new records to MongoDB

```
{
  "_id" : ObjectId("5dca6bf755c84366dee4941d"),
  "firstName": "John",
  "lastName": "Doe"
}
```

```
{
  "_id" : ObjectId("5dca6c3503917ab3d9b1ca20"),
  "firstName": "Jane",
  "lastName": "Doe"
}
```

### Test Your App

- `curl http://localhost:8080/employee/`
- `curl http://localhost:8080/employee/5dca6bf755c84366dee4941d`
- `curl http://localhost:8080/employee/5dca6c3503917ab3d9b1ca20`
