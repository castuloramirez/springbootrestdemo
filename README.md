# Spring Boot Rest Demo

Tools : IntelliJ Idea 2019.3, Postman
Technologies : Java 8, Spring Boot

1)	Running the app

springbootrestdemo> mvn clean install -U    
springbootrestdemo> mvn spring-boot:run


2)	Full Search: http://localhost:8080/locations/  
 
3)	Adding a Location: http://localhost:8080/locations/
{
            "name": "Vienna",
            "lat": "48.21",
            "lng": "16.36",
             "type": "premium"
}


Result with the Id
{
    "id": 4,
    "name": "Vienna",
    "lat": "48.21",
    "lng": "16.36",
    "type": "premium"
}
 

 



4)	Searching by Type: http://localhost:8080/locations/search
 
{
"type": "premium"
}

 


{
"type": "standard"
}


 

5)	Searching by Point: http://localhost:8080/locations/search
{ 
   "p1": "46.6, 15.4",
   "p2":"48.5, 18"
}

 


6)	Searching by all criterias: http://localhost:8080/locations/search




	{ "p1": "46.6, 15.4",
	
	"p2": "49.8, 18.5",
	
	"type": "premium",
	
	"limit": "2"
	}
 
