
![architecture](https://cloud.githubusercontent.com/assets/20100300/16337672/e80b2d7c-39dc-11e6-8918-b5b1df9499d9.JPG)

## Business Services
### 1.Product Catalogue Service

	It offers the following functionality by using Embedded H2 Database.
    
        	1. GET  	/productCatalogueService/products – gives the list of all products
            2. GET 	 	/productCatalogueService/search  – gives the list of products for matching name,type
            3. GET 	 	/productCatalogueService/searchByType – gives the list of products for matching type
            4. POST  	/productCatalogueService/products – saves the given product
            5. DELETE 	/productCatalogueService/products/{id} – delete the given product

### 2. Pricing Service

	It offers the following functionality by using Embedded H2 Database.
    
    		1. GET  	/pricingService/products/price/get
            
    The above method gives the price value for the given name and type of the product. This method first
    contact the “Product Catalogue Service” to get the “product id” for given name and type (This has been
    called using “load-balancer-client”). Then, get the price based on the “product id” attribute.
    
## Eureka (Service Discovery)
	This will maintain the all instances of business services, client API gateway and also all other instances
	of eureka service discovery in the cluster.
	
## Config Server
	This will have the list of all instances of eureka service discovery which will be maintained in any revision
	control system.  Here, we used “github” so that we can dynamically change eureka server instance in case of any
	modification in the service discovery cluster. No need to re-start the cluster, dynamically cluster will get 
	refreshed on modification in “github
	
## Client API Gateway (ZUUL Proxy)
	Any request from client will be re-directed to actual business through ZUUL Proxy. The following steps involved
	in redirecting to the actual business service url.
			
		1. ZUUL contacts the “Config Server” to know the instances of the Service Discovery.
		2. ZUUL will be provided the actual business service url by the Eureka. Here, Eureka will perform load 
		   balance using “Ribbon” before providing the actual url.
		3. Finally, ZUUL will contact the actual business service url and redirect the response of the respective 
		   service to the requested client

## HYSTRIX
	We can monitor all the requests to the business services by using the “Hystrix Turbine Stream”. This will give
	clear picture of all the requests information like how many got passed, how many got failed, how many still 
	processing etc. for a specific period of time.
	
## Execution
	1. Clone the project using "https://github.com/kranthiB/globoMart.git"
	
	2. Discovery Service :-- Go to “serviceDiscovery” folder, execute the following commands
           (This will run 3 instances of eureka service discovery)

		mvn spring-boot:run -Dspring.profiles.active=primary 	(Runs on http://localhost:8071)
		mvn spring-boot:run -Dspring.profiles.active=secondary 	(Runs on http://localhost:8072)
		mvn spring-boot:run -Dspring.profiles.active=tertiary 	(Runs on http://localhost:8073)

	3. Config Server :- Go to “configServer” folder, execute the following command
	
		mvn spring-boot:run 					(Runs on http://localhost:8000)

	4. Product Catalogue Service :- Go to “productCatalogueService” folder, execute the following command
	
		mvn spring-boot:run

	   Depends on number of instances required, execute the above command that many times.
	   Each time, it runs on any random port.
	   
	5. Pricing Service :- Go to “pricingService” folder, execute the following command
	
		mvn spring-boot:run

	   Depends on number of instances required, execute the above command that many times.
	   Each time, it runs on any random port.
	   
	6. ZUUL Proxy :- Go to “clientGateway” folder, execute the following command
	
		mvn spring-boot:run 					(Runs on http://localhost:8003)

	7. Monitoring :- To monitor all the services using the hystrix turbine steam , go to “hystrixDashboard” 
	   folder, execute the following command

		mvn spring-boot:run 					(Runs on http://localhost:8004)
		
## APP Test

### View the service discovery either using port 8071 or 8072 or 8073

![2](https://cloud.githubusercontent.com/assets/20100300/16338562/2bd9d7a0-39e3-11e6-9481-8747f60c3ae1.png)

### http://localhost:8003/productCatalogueService/products
### http://localhost:8003/productCatalogueService/products/searchByType?type=tv
### http://localhost:8003/productCatalogueService/products/search?name=BMW&type=car
![1](https://cloud.githubusercontent.com/assets/20100300/16338558/273ebc4c-39e3-11e6-9a20-aed50acb9736.png)
### http://localhost:8003/pricingService/products/price/get?name=ALTO&type=car
![3](https://cloud.githubusercontent.com/assets/20100300/16338566/30a42132-39e3-11e6-95c0-71e89222ae4a.png)






