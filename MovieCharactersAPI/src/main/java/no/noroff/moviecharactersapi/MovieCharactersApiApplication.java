package no.noroff.moviecharactersapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "MovieAPI",
                version = "1.0",
                description = "The Movie Characters API is a RESTful web API that provides access to a PostgreSQL database. " +
                        "The database stores information about movie characters, movies they appear in, and the franchises these movies belong to. " +
                        "The API was developed using Java 17, Spring Web, Spring Data JPA, Hibernate, and PostgreSQL." +
                        "Project repo can be found at: https://github.com/stefvtls/MovieCharactersAPI\n" +
                        "Authors: Jim Buissink and Stefania van't Laar-Sapór"
        )
)
public class MovieCharactersApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieCharactersApiApplication.class, args);
    }

}
