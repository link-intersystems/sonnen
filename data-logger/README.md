The sonnen-data-logger is a Spring Boot application that retrieves status data from
a sonnenBatterie and persists it in a mongodb.

## Docker

### Docker Compose Setup

The easiest way to start the data logger is through docker compose.

The git respsitory contains example configurations and scripts in [src/main/linux](src/main/linux).

That directory also contains a [README](src/main/linux/README.md) that contains the steps to follow.


### Manual Docker Setup

To start the docker data-logger application you need a mongo db. Either you have an existing instance that you
can connecto to or you can start up a docker mongo db.

If you want to start a docker mongo db you have to make sure that the data-logger can access the mongo db. Therefore
they should be in the same network. To achieve this you should create a network first.

     docker network create sonnen-net

Startup a mongo db connected to the network

     docker run -d --name sonnen-db --network=sonnen-net -p "27017:27017" -e MONGO_INITDB_ROOT_USERNAME=root -e MONGO_INITDB_ROOT_PASSWORD=example mongo:4.4.13

The data-logger is a Spring Boot application and needs an `application.properties` file to configure it.

You can use this template and edit the `spring.sonnen` properties at the top. 

    spring.sonnen.api-url=<EDIT_ME>
    spring.sonnen.authToken=<EDIT_ME>
    
    # Do not start tomcat. We only want to execute a rest client application.
    spring.main.web-application-type=none
    #logging.pattern.console=
    spring.main.banner-mode=off
    
    spring.data.mongodb.host=sonnen-db
    spring.data.mongodb.authentication-database=admin
    spring.data.mongodb.username=root
    spring.data.mongodb.password=example
    spring.data.mongodb.database=sonnen

Now you can start the data-logger: 

    docker run -d --name data-logger --network=sonnen-net -v $pwd/application.properties:/application.properties linkintersystems/sonnen-data-logger:latest -s PT10.000S

If you need a web gui you can also start mongo express

    docker run -d --name sonnen-db-browser -p 8081:8081 --network=sonnen-net -e ME_CONFIG_MONGODB_ADMINUSERNAME=root -e ME_CONFIG_MONGODB_ADMINPASSWORD=example -e ME_CONFIG_MONGODB_URL=mongodb://root:example@sonnen-db:27017/ mongo-express