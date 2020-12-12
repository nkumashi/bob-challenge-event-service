# Event-Service

This service is responsible for listening to the events generated by [`employee-service`](https://github.com/takeaway/bob-challenge-employee-service) and saving them in its own database. The [`event-service`](https://github.com/takeaway/bob-challenge-event-service/) must also expose a `REST API` that contains just one endpoint:
   - Get all events related to a specific employee in ascending order, i.e, _the oldest event must appear first_. Response should be in JSON Array format.

# Install and run

# Requirements
	- Docker/Docker Compose
	- maven
	- JDK 1.8
	- Git client
	
# Running the application

	- Naviagte to the root folder of the bob-challenge-employee-service project. Launch the supporting services: ZooKeeper, Kafka and MySQL database by running the command:
```
docker-compose up -d
```
	
	- Open the bob-challenge-employee-service project in an IDE like Eclipse/STS. Launch the application as a SpringBoot project.

	- Open the bob-challenge-event-service project in an IDE like Eclipse/STS. Launch the application as a SpringBoot project.

# Application resources available:
	- http://localhost:8181/employee-service/api/employees
	- http://localhost:8181/employee-service/api/employee/{employeeId}
	- http://localhost:8181/employee-service/api/departments
	- http://localhost:8181/employee-service/api/deployment/{deploymentId}
	- http://localhost:8182/event-service/api/events

