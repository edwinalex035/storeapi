#Store API

This a Rest API based on the requirements provided. This api is developed using Maven and Spring boot version 2.0.6.RELEASE. The application works with an H2 database. The scripts are available in the resources folder. In the folder db/h2.
When the application starts this create the tables (schema.sql) and create the test data ( test-data.sql)

##Secutity and Authentication
The Security functionality is developed with Spring Security, OATH2 and JWT. Before you start consuming the different services, you must get a Bearer Token consuming the following service: localhost:8080/oauth/token.
This service required to select the Basic authentication Type in Postman and share
```
Username: storeId
Password: storeId
```

And in the body, share the following parameters:
```
username:ADMIN
password:ADMIN
grant_type:password
```

The username/name are the users created in the database before mentioned. If the process was sucesfully the service will return a Token which is the one that we should use in the following service.

##Rest API Functionalilty

###Product List
The are different methods in order to get the list of products. The first one is to consume the URL: localhost:8080/api/products/. This will return all the products available. The information is going to be ordered by name
The second one is the URL: localhost:8080/api/products/?page=0&size=2. This contains pagination functionality. Replace the values of page and size.
The third option is the URL: localhost:8080/api/products/?page=0&size=3&orderBy=name&direction=asc. In this, we have the option of pagination and also defined the sort in the data.

Keep in mind, if you introduce a negative value for the page or size, the application will take by default the following values:
```
page = 0
size = 5
```

And in case of sort, it is just available to do it by:
```
orderBy = name|popularity
direction = asc|desc
```
If you introduce a different value the default sort is by name and desc.

###Search By Name
This functionality is similar to the previous one. We use the same URLs and tto filter by name we should sent the parameter in this way: localhost:8080/api/products/?name=pop. To can also use the pagination functionality taking as an example
the following URL: localhost:8080/api/products/?page=0&size=2&name=o. And You can also use the sort information like this: localhost:8080/api/products/?page=0&size=3&orderBy=popularity&direction=desc&name=o

All of this functionalities use the **GET** verb and it do not required authentcation.

###Add / Modify / Delete Products

This functionality requires the use of an access token. In order to consume this services you need to get the token as was previously mentioned in the Security and Authencation section
To set the token in postman go to the Authorization tab, select Bearer Token and paste the token in the token field.
###Add Product
In order to create a product, you have to use the URL: localhost:8080/api/products/ with **POST** HTTP verb. And in order to provide the information, in the tab body, select **raw** and then **JSON(application/json**.
The expected parameter are like this:
```
{
	"name": "Mani japonese",
	"description": "Mani japonese",
	"price": 0.25
}
```
== Modify Price and Stock
This service uses the **PUT** HTTP verb and use the following URL: localhost:8080/api/products/{id}, where id indicates the product that you need to modify. And in order to provide the information, in the tab body, select **raw** and then **JSON(application/json**.
The expected parameter are like this:
```
{
    "id": 5,
    "description": "Mani japonese",
    "price": 0.35,
    "stock": 10
}
Everything you modify a product, the old price and new price is saved in LOG_PRICE table to keep the track of the changes made. And it is also display at log level.
```
###Delete a Product
This service uses de **DELETE** HTTP verb. The URL is the following: localhost:8080/api/products/{id}, where id indicates the product you want to delete.

###Like Functionality
This service allows to give likes to the different products. This funtionality uses the **PUT** HTTP verb and the URL is the following: localhost:8080/api/products/like/{id}, where id indicates the product you want to give like.

###Buy Products
If you want to buy any product, you can do it using the following URL: localhost:8080/api/buy/ with the **POST** HTTP verb. The parameter expected are the following:
```
{
	"productId": 5,
	"quantity": 2
}
```
With this information, first we validate if the requested quantity is lower of the stock available, if not the purchase is not completed. This information is saved in BUY_PRODUCT table.

##Building and Deploying
This is a Maven project. Currently it can be compiled with java 1.8. Please execute the following commands:
```
maven clean package
```
And you can start testing the application. Please note, that I did not share a database dump because I use an In-Memory Database. The scripts are available into the resources folder.

##Building and Deploying in Docker

As an enhancement the application can be run in a Docker Container. To do this, a maven plugin is been used. The steps to follow are:
Generate the jar file
```
mvn clean package
```
Generate the Docker image. The name used is test/dockeragent
```
mvn com.spotify:dockerfile-maven-plugin:1.4.8:build
```

Run the image as follow:
```
docker run -p 8080:8080 test/dockeragent
```
