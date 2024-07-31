package com.springdata.tennis_player;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;


/*
This small project shows how to use spring-jdbc using a tennis-player-example.
To run the query, we use a CommandLineRunner (interface in Spring Boot which has only one method called run()).
(This method is launched as soon as the context is loaded.)
*/
@SpringBootApplication
public class TennisPlayerApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PlayerDao dao;

	public static void main(String[] args) {
		SpringApplication.run(TennisPlayerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// inserting a player
		logger.info("All Players Data: {}", dao.getAllPlayers());
		// getting a player by id
		logger.info("Player with Id 3: {}", dao.getPlayerById((3)));
		// inserting a new player
		logger.info("Inserting Player 4: {}", dao.insertPlayer(new Player(4, "Thiem", "Austria", new Date(System.currentTimeMillis()), 17)));
		// updating a player
		//logger.info("Updating Player with Id 4: {}", dao.updatePlayer(new Player(4, "Thiem", "Austria", new Date("1993-09-03"), 17)));
		// deleting a player using the update method
		logger.info("Deleting player with Id 2: {}", dao.deletePlayerById(2));
		// running the execute method in the service class to create a table
		dao.createTournamentTable();
		// Using custom row Mapper
		logger.info("French Players: {}", dao.getPlayerByNationality("France"));
	}

}
