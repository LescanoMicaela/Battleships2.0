package com.example.salvo.repositories;

import com.example.salvo.vo.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


//@RepositoryRestResource turns PersonRepository into a Rest Repository. Spring Boot looks for public getters
//and creates methods to generate JSON for the associated fields.

//Spring take care of storing instances of Java classes in a database with  interface that extends JpaRepository
@RepositoryRestResource
public interface PlayerRepository extends JpaRepository<Player,Long> {
    Player findByUserName(@Param("name") String name);

}
