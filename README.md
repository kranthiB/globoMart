
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
            
    The above method gives the price value for the given name and type of the product. This method first 		contact the “Product Catalogue Service” to get the “product id” for given name and type (This has been 		called using “load-balancer-client”). Then, get the price based on the “product id” attribute


