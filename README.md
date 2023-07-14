# germin8-challenge-location-validator-app
This app is created on Java, Spring Boot, MySQL.
here I have given an endpoing ```/api/v1/getStores/{city}```  
Replace the city by city name then you will get the stores in the city and it store the data in mysql database   

here is the sample call
```bash
http://localhost:8080/api/v1/getStores/Delhi
```  
Sample API response
```json
[
    {
        "id": 1,
        "storeName": "NEW DELHI AIRPORT",
        "storeDisplayName": "NEW DELHI AIRPORT",
        "city": "Delhi",
        "address": "SHOPPERS STOP SU 20-23 SHA,, TERMINAL ID IGI AIRPORT, Delhi, Delhi, 110037",
        "latitude": "28.556986",
        "longitude": "77.0615644"
    },
    {
        "id": 2,
        "storeName": "Too Faced - Select City Walk",
        "storeDisplayName": "Too Faced - Select City Walk",
        "city": "Delhi",
        "address": "Select city walk District centre, Saket NORTH, Delhi, DELHI, 110017",
        "latitude": "28.5285",
        "longitude": "77.219"
    }
]

```  

I have uploded it in github and created auto build and push to docker hub by github actions. 

### RUN CONFIGURATION
1. Pull the docker image
```bash
docker pull surjendu104/location-validator-app:latest
```
2. RUN the image by this command
```bash
docker run -p 8080:8080 --network host -e SERVER_HOST=<SERVER_HOST> -e SERVER_PORT=<SERVER_PORT> -e DB_PORT=<DB_PORT> -e DB_NAME=<DB_NAME> -e DB_USERNAME=<DB_USERNAME> -e DB_USER_PASSWORD=<DB_USER_PASSWORD> surjendu104/location-validator-app
```
3. Example call
```bash
docker run -p 8080:8080 --network host -e SERVER_HOST=127.0.0.1 -e SERVER_PORT=8080 -e DB_PORT=3306 -e DB_NAME=shoppers_stop_data -e DB_USERNAME=root -e DB_USER_PASSWORD=RbSp@321611 surjendu104/location-validator-app
```
