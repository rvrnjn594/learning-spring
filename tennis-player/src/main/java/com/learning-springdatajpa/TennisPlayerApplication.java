package springdatajpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;


/*
This small project shows how to use spring-jdbc using a tennis-player-example.
To run the query, we use a CommandLineRunner (interface in Spring Boot which has only one method called run()).
(This method is launched as soon as the context is loaded.)
*/
@SpringBootApplication
public class TennisPlayerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TennisPlayerApplication.class, args);
	}

}
