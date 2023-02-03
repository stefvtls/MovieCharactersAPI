package no.noroff.moviecharactersapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



/**
 The MovieCharactersApiApplication class is the main class of the Movie Characters API.
 This class uses the @SpringBootApplication annotation to indicate that this is a Spring Boot application.
 It also uses the @OpenAPIDefinition annotation to provide information about the API, such as its title and version.
 The main method uses the SpringApplication.run method to run the application.
 */
@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "MovieAPI",
                version = "1.0",
                description = "The Movie Characters API is a RESTful web API that provides access to a PostgreSQL database. " +
                        "The database stores information about movie characters, movies they appear in, and the franchises these movies belong to. " +
                        "The API was developed using Java 17, Spring Web, Spring Data JPA, Hibernate, and PostgreSQL. \n\n\n" +
                        "Project repo can be found at: https://github.com/stefvtls/MovieCharactersAPI\n\n" +
                        "Authors: Jim Buissink and Stefania van't Laar-Sapór"
        )
)
public class MovieCharactersApiApplication {

    public static void main(String[] args) {

        SpringApplication.run(MovieCharactersApiApplication.class, args);
    }

}
