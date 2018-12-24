package com.example.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}

	//@Bean return an instance of a bean. When Spring sees such methods,
	// it will call them at startup up time, and save the instance for autowired injection.
	// This is a way to get beans without annotating them as components.

	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository, GameRepository gameRepository, GamePlayerRepository gpRepository,ShipRepository shipRepository) {
		return (args) -> {
			Player ObiWan = new Player("obi_wan@gmail.com");
			Player Leia = new Player("leia@gmail.com");
			Player Luke = new Player("luke_skywalker@gmail.com");
			Player Han = new Player("Solo@gmail.com");
			Player Chewie = new Player("chewie@gmail.com");

			playerRepository.save(ObiWan);
			playerRepository.save(Leia);
			playerRepository.save(Luke);
			playerRepository.save(Han);
			playerRepository.save(Chewie);

			Game g1 = new Game();
			Game g2 = new Game();
			Game g3 = new Game();
			Game g4 = new Game();
			Game g5 = new Game();
			Game g6 = new Game();
			Game g7 = new Game();

			gameRepository.save(g1);
			gameRepository.save(g2);
			gameRepository.save(g3);
			gameRepository.save(g4);
			gameRepository.save(g5);

			GamePlayer gp1 = new GamePlayer(ObiWan,g1);
			GamePlayer gp2 = new GamePlayer(Luke,g1);

			GamePlayer gp3 = new GamePlayer(Luke,g2);
			GamePlayer gp4 = new GamePlayer(Leia,g2);

			GamePlayer gp5 = new GamePlayer(Han,g3);

			GamePlayer gp6 = new GamePlayer(Chewie,g3);
			GamePlayer gp7 = new GamePlayer(Leia,g3);

			GamePlayer gp8 = new GamePlayer(Han,g4);

			GamePlayer gp9 = new GamePlayer(ObiWan,g5);
			GamePlayer gp10 = new GamePlayer(Luke,g5);


			gpRepository.save(gp1);
			gpRepository.save(gp2);
			gpRepository.save(gp3);
			gpRepository.save(gp4);
			gpRepository.save(gp5);
			gpRepository.save(gp6);
			gpRepository.save(gp7);
			gpRepository.save(gp8);
			gpRepository.save(gp9);
			gpRepository.save(gp10);

			List<String> location1 = Arrays.asList("H1","H2");
			List<String> location2 = Arrays.asList("E1","F1","G1");
			List<String> location3 = Arrays.asList("A3","A4","A5","A6");
			List<String> location4 = Arrays.asList("J3","J4");
			List<String> location5 = Arrays.asList("C5","C26");


			List<String> location6 = Arrays.asList("F1","F2");
			List<String> location7 = Arrays.asList("F1","F2","F3");
			List<String> location8 = Arrays.asList("H2","H3","H4","H5","H6");
			List<String> location9 = Arrays.asList("A1","B2","C3");
			List<String> location10 = Arrays.asList("E7","F7","G7","H7");
			List<String> location11 = Arrays.asList("C6","C7");
			List<String> location12 = Arrays.asList("H2", "H3", "H4");
			List<String> location13 = Arrays.asList("E1", "F1", "G1");
			List<String> location14 = Arrays.asList("B4", "B5");
			List<String> location15 = Arrays.asList("B5","C5","D5");

			Ship ship1 = new Ship("Cruiser",gp1,location1);
			gp1.addShip(ship1);
			shipRepository.save(ship1);
			Ship ship2 = new Ship("Patrol Boat",gp1,location2);
			gp1.addShip(ship2);
			shipRepository.save(ship2);
			Ship ship3 = new Ship("Patrol Boat",gp1,location4);
			gp1.addShip(ship3);
			shipRepository.save(ship3);
			Ship ship4 = new Ship("Destroyer",gp2,location5);
			gp2.addShip(ship4);
			shipRepository.save(ship4);
			Ship ship5 = new Ship("Patrol Boat",gp2,location11);
			gp2.addShip(ship5);
			shipRepository.save(ship5);
			Ship ship6 = new Ship("Destroyer",gp3,location1);
			gp3.addShip(ship6);
			shipRepository.save(ship6);
			Ship ship7 = new Ship("Submarine",gp1,location2);
			gp4.addShip(ship7);
			shipRepository.save(ship7);


		};
	}

}

