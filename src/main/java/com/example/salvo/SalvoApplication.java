package com.example.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

///---------- '1.5.10.RELEASE'---------------- ////
//import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;

////----------'2.1.1.RELEASE' ---------------////
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.*;




@SpringBootApplication
public class SalvoApplication {



	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}

	//@Bean return an instance of a bean. When Spring sees such methods,
	// it will call them at startup up time, and save the instance for autowired injection.
	// This is a way to get beans without annotating them as components.

	//encrypt the passwords before storing them so hackers can have acces to them via REST



	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository, GameRepository gameRepository,
									  GamePlayerRepository gpRepository,ShipRepository shipRepository,
									  SalvoRepository salvoRepository,ScoreRepository scoreRepository, PasswordEncoder passwordEncoder) {
		return (args) -> {
			Player ObiWan = new Player("obi_wan@gmail.com",passwordEncoder.encode("24"));
			Player Leia = new Player("leia@gmail.com",passwordEncoder.encode("42"));
			Player Luke = new Player("luke_skywalker@gmail.com",passwordEncoder.encode("90"));
			Player Han = new Player("Solo@gmail.com",passwordEncoder.encode("60"));
			Player Chewie = new Player("chewie@gmail.com",passwordEncoder.encode("90"));

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
			List<String> location5 = Arrays.asList("C5","C6");


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


			Salvo salvo0 = new Salvo(gp1, 1,location4);
			Salvo salvo1 = new Salvo(gp1, 2,location1);
			Salvo salvo6 = new Salvo(gp1, 3,location9);
			Salvo salvo2 = new Salvo(gp2, 1,location6);
			Salvo salvo3 = new Salvo(gp2, 2,location9);
			Salvo salvo4 = new Salvo(gp3, 1,location5);
			Salvo salvo5 = new Salvo(gp4, 1,location9);

			salvoRepository.save(salvo0);
			salvoRepository.save(salvo1);
			salvoRepository.save(salvo2);
			salvoRepository.save(salvo3);
			salvoRepository.save(salvo4);
			salvoRepository.save(salvo5);
			salvoRepository.save(salvo6);

			Score score1 = new Score(1.0,g1,ObiWan);
			Score score2 = new Score(0.0,g1,Luke);
			scoreRepository.save(score1);
			scoreRepository.save(score2);
			Score score3 = new Score(0.5,g2,Leia);
			Score score4 = new Score(0.5,g2,Luke);
			scoreRepository.save(score3);
			scoreRepository.save(score4);


		};
	}

}

//The job of this new class is to take the name someone has entered for log in,
//search the database with that name, and return a UserDetails object with name, password, and role information for that user, if any.

//@Configuration : creates a configuration bean. provides libraries for both authentication and authorization.
//In this method  we create and return a org.springframework.security.core.userdetails.
// //User object, with the stored user name, the stored password for that user, and the role or roles that user has.
@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {
// this is authentification who and roles
	@Autowired
	PlayerRepository playerRepository;
	//encrypt the passwords before storing them so hackers can have acces to them via REST
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//				.withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER");
		auth.userDetailsService(inputName-> {
			Player player = playerRepository.findByUserName(inputName);
			if (player != null) {
//				///---- REALEASE 1.5.10 WITH NO PASSWORD ENCODER--------
				return new User(player.getUserName(), player.getPassword(),
						AuthorityUtils.createAuthorityList("USER"));
//				///---realse 2.1.1 WITH PASSWORD ENCODER-----
//				if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
//					throw new BadCredentialsException("Bad password");
//////				}
//				return User.withUsername(player.getUserName()).password(passwordEncoder().encode(player.getPassword())).roles("USER").build();
//				return new User(player.getUserName(), passwordEncoder().encode(player.getPassword()),
//						AuthorityUtils.createAuthorityList("USER"));

			} else {
				throw new UsernameNotFoundException("Unknown user: " + inputName);
			}
		});
	}
}


//this is authorization
//what can users do
@Configuration
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/web/games.html").permitAll()
				.antMatchers("/web/scripts/**").permitAll()
				.antMatchers("/web/style/**").permitAll()
				.antMatchers("/web/images/**").permitAll()
				.antMatchers("/api/games").permitAll()
				.antMatchers("/api/players").permitAll()
				.antMatchers("/rest/**").permitAll()
//				.antMatchers("/**").hasAuthority("USER")
				 .antMatchers("/**").hasAuthority("ROLE_USER")
				.and()
				.formLogin()
				.usernameParameter("userName")
				.passwordParameter("password")
				.loginPage("/api/login");

		http.logout().logoutUrl("/api/logout");


		// turn off checking for CSRF tokens
		http.csrf().disable();

		//form-based login configured to avoid sending any HTML to the browser.
		// settings that need to change are:

		//Only send an HTTP "unauthorized" response when an unauthenticated user tries to access a protected URL.
		//Only send an HTTP "success" response after a successful POST to the login URL.
		//Only send an HTTP "unauthorized" response when login is not successful.
		//Only send an HTTP "success" response  when the user logs out.

		// if user is not authenticated, just send an authentication failure response
		http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

		// if login is successful, just clear the flags asking for authentication
		http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

		// if login fails, just send an authentication failure response
		http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

		// if logout is successful, just send a success response
		http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
	}


	private void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		}
	}
}






