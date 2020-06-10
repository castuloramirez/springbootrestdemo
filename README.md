# Location API Spring Boot Rest V1.1

App prototype backend to handle locations. REST API that handles locations. The API accepts a json and returns a json too, if the response is required.

Create a Location

The App creates a new location by providing a name, a type with possible values premium or standard and coordinates using lat and lng. The system assigns the location an id and return it back within the response.

An example values to create a location:

name: Medellin, Colombia

lat: 48.2

lng: 15.6

type: premium

Search for a Location

Search for locations based on type or by defining a rectangular area with two points p1, p2 (lat,lng) and returning all locations within it. It is possible to use both criteria together and the result set might be limited by limit. By te way the results are ordered by type where premium ones come first.

An example to search for a location:

p1: 46.6, 15.4

p2: 48.8, 17.5

type: premium

limit: 2


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
 
