# Drones-task

## Quickstart

1. Clone the repository
2. Open the project in your IDE: IntelliJ IDEA (recommended) or Eclipse
3. Run the project (by running the `main` method in `DronesApplication.java`)
4. Open http://localhost:8080/ in your browser!


## database dashboard (optional)

- The H2 Database Console is a web-based database management tool that allows you to interact with an H2 in-memory database.

1. Configure the database credentials (username, password) in `application.properties`.
2. go to http://localhost:8080/h2-console
3. set "JDBC URL" to "jdbc:h2:mem:drones"
4. enter the username and password

- This can be useful for debugging and inspecting the contents of your H2 database.

## End points

### 1. Register new drone
```http
POST http://localhost:8080/drones/register
```

Body:
``` javascript
{
    "serialNumber": "DRONE_1",
    "model": "CRUISERWEIGHT",
    "weightLimit": 229,
    "batteryCapacity": 92,
    "state": "LOADING"
}
```
---

### 2. loading a drone with medication
```http
POST http://localhost:8080/medications/attach
```

Body:
``` javascript
{
    "name": "medication-21",
    "weight": 140,
    "code": "MED_21",
    "image": null,
    "drone_sn": "DRONE_1"
}
```
---

### 3. checking loaded medication items for a given drone
```http
GET http://localhost:8080/drones/{DRONE_SN}
```
- Replace *DRONE_SN* with drone's Serial Number
RESPONSE:
``` javascript
{
    "serialNumber": "DRONE_1",
    "model": "CRUISERWEIGHT",
    "weightLimit": 229,
    "batteryCapacity": 92,
    "state": "LOADING",
    "medications": [
        {
            "id": 3,
           "name": "medication-21",
            "weight": 140,
            "code": "MED_21",
            "image": null
        }
    ]
}
```
---

### 4. checking available drones for loading
```http
GET http://localhost:8080/drones/available
```

- Returns dron objects with "IDLE" or "LOADING" states

---

### 5. check drone battery level for a given drone
```http
GET http://localhost:8080/drones/{DRONE_SN}/battery
```

- Replace *DRONE_SN* with drone's Serial Number


Response:
``` javascript
{
    "batteryCapacity": 88
}
```


---

---

## Test
- Right-click on the project or the src/test/java directory and choose "Run All Tests."
