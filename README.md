# ProgressSoft Assignment
This file explains the application's basic usage and how it was created.

### Basic usage
To run the application, do the following:
* Clone the application from [my repo](https://github.com/Kinan-Diraneyya/progresssoft-assignment/)
* Make sure that docker is running
* Navigate to the project's root and run `docker-compose up --build`

### Endpoints
* Insert a new deal: http://localhost:8080/api/deals POST
* Update a deal using its ID: http://localhost:8080/api/deals/{id} PUT
* Delete a deal using its ID: http://localhost:8080/api/deals/{id} DELETE
* Return a deal using its ID: http://localhost:8080/api/deals/{id} GET
* Retrun all deals: http://localhost:8080/api/deals GET

### About the requirements
* **The request:** I choose to keep the deal's timestamp auto generated, but to have the ID manually inserted. This is because you specifically asked to prevent duplicate records insertion, which is only possibly if IDs were manually inserted.
* **Validation:** The request is thoroughly validated, including a custom validator that only allows ISO currencies.
* **Database:** The choice of database was insignificant given the lack of relations, but I prefer relational databases.
* **Logging, Documentation, and testing:** I used AI to generate the code for most (but not all) of these tasks. Code generated by AI is committed with notes saying so.
* **Makefile:** I did not use makefile because my docker compose has a build stage and spins up containers for both of the app and its database.

Thank you for taking the time to read the above and my regards.