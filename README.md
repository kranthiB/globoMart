
![architecture](https://cloud.githubusercontent.com/assets/20100300/16337672/e80b2d7c-39dc-11e6-8918-b5b1df9499d9.JPG)

## Business Services
### 1.Product Catalogue Service
	It offers the following functionality by using Embedded H2 Database.
        	1. GET  /productCatalogueService/products – gives the list of all products
            		http://localhost:8003/productCatalogueService/products
            2. GET /productCatalogueService/search – gives the list of products for matching name and type
            		http://localhost:8003/productCatalogueService/products/searchByType?type=tv
            3. GET /productCatalogueService/searchByType – gives the list of products for matching type
            		http://localhost:8003/productCatalogueService/products/search?name=BMW&type=car
