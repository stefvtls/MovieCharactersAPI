# Movie Characters API

The project follows the guidelines provided in the Noroff assignment and includes the minimum requirements as described in the Appendix A-B.



## Part A of the assignment

Hibernate has been used to create a PostgresSQL database which could be used to store and manipulate movie characters. It stores information about **characters, movies** they appear in and the **franchises** they belong to. These entities adhere to the following business rules:

<ol>
  <li> One movie contains many characters, and character can play in multiple movies. </li>
  <li> One movie belongs to one franchise, but a franchise can contain multiple movies. </li>
</ol>

There are two seperate layers in this project. The *Repositories* layers is responsible for encapsulating data access through the use of JPA repositories. The *Services* layer is responsible for encapsulating any business logic

Dummy data for this database is provided and can be found at the following location: */MovieCharactersAPI/src/main/resources/data.sql*


## Part B of the assignment

Spring Web has been used to create a RESTful Web API which is able to acces and modify our entities. Besides generic CRUD operations for the three entities, there are five dedicated endpoints for additional updating and reporting:

<ol>
  <li> Update the characters in a movie by supplying a movie id in the path and an array of character id's in the body of a request. </li>
  <li> Update the movies in a franchise by supplying a movie id in the path and an array of character id's in the body of a request. </li>
  <li> Return all movies in a franchise by supplying a franchise id in the path</li>
  <li> Return all characters in a movie by supplying a movie id in the path</li>
  <li> Return all characters in a franchise by supplying a franchise id in the path</li>
</ol>

With GET and POST requests, rather than showing or supplying the related data as enities, DTOs are used which facilitates these requests by only requiring a relevant id.

Documentation for this project has been created using Swagger / Open API.


## Getting Started
Clone the repository:
`git clone https://github.com/stefvtls/MovieCharactersAPI.git`
This project uses Gradle Wrapper, so you don't need to have Gradle installed on your machine.

To use the wrapper  to build the project:
`cd MovieCharactersAPI/MovieCharactersAPI`

To build the project:
`./gradlew build` or `bash ./gradlew build`

To run the project:
`./gradlew run` or `bash ./gradlew run`

Alternatively:
Open the project in IntelliJ Ultimate with JDK 17.



## Setting up database connection
Before running the project, go to the file located in src/main/resources/application.properties set up the following:


`spring.datasource.url= jdbc:postgresql://localhost:5432/<nameOfYourMovieDatabase>`

`spring.datasource.username= <DatabaseUsernameLogin>`

`spring.datasource.password= <DatabasePassword>`


## Using the API
If you want to test the API, you should run the the project as described in the previous steps and open the [Swagger UI](http://localhost:8080/swagger-ui/index.html#/).



## Built With

• IntelliJ IDEA with JDK 17.

• SpringBoot

• Spring Web

• Swagger / OpenAI

• PostgreSQL

• Gradle



## Contributors
Stefania van 't Laar-Sapór @stefvtls & Jim Buissink @jimosine



## License
This project is licensed under the MIT License.



## Acknowledgments
Special thanks to the Noroff for providing the opportunity to develop this project.



## Note
This is a student project and it should not be used as a production-ready software.
