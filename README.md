Welcome !

###This project has been created with following requirements and still is work in progress:

##Product Comparison Service:
As an end customer that wants to buy some product, I have many options from where to buy it, such as different websites and
different retail shops. This can evidently make it hard to choose from where to buy.
The aim of this task is to design and implement a service that helps the end customer to decide which website or retail shop
they can use to buy their product by comparing data from different providers.
The Product Comparison service should provide the below features:
- Service should provide an endpoint that accepts product name and category as a search options and returns a list of
product info that matches it.
- The service should support multiple data sources for importing new products to the service data base (push, pull, batch
data import, ....).
- Please provide full implementation for one data source and through an exception for other data sources. Your code
should be flexible and allow for introducing new data sources.
- The solution should be documented but should also be understandable (try to follow SOLID and clean code principles).
- Performance and test cases are important.
- The system should be easy to scale and maintain.

#####The Stack
The solution should be based on java or python as programing language, however you may select any framework you are
familiar with. The solution should not be over engineered and running it on containers is preferred. We will be looking into how
you solve the business logic with focus on good object-oriented design practices and API design!

#####Deliverables:
- Once you are done, please upload your solution to your github and share the link.

Bonus Points (nice to have)
If you want to go the extra mile you may consider the below points:
- (Done) Delivering the solution running on container based env such as docker.
- (Done per understanding) Assume there is an AI service that uses customer reviews for different products and provides recommendations on
product providers, so you need to return the search result ranked based on result from recommendation service.
- (Description at last)The service should be of high availability**

##Solution

To run the project you would need following tools:
1. Java
2. Maven
3. docker

##Build process:
    cd /productsearch
    mvn clean install
    docker build -t relayr-product .
    cd..

##Run:
    docker-compose up

#APIs (To be called in order):
    /relayr/api/v1/data POST - Load data to search database from given sources
    /relayr/api/v1/search POST - search database

    For request body and response please start the application an use 
    http://localhost:8080/relayr/swagger-ui.html

At present we have only single data source "amazon" whose data is loaded from a json file in resources.
Multiple data sources can be configured in the application. At present we have used bean based implementation,
 but the data sources can be taken out as separate services.

The product search service is supported by Mongo image, which is used to store the loaded data from the data sources and
also support search of that data.

#High Availability
The docker image can be deployed using docker swarm following the given blog:
https://dzone.com/articles/go-microservices-part-5-deploying-on-docker-swarm