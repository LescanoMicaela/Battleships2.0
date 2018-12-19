package com.example.salvo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


//@RepositoryRestResource turns PersonRepository into a Rest Repository. Spring Boot looks for public getters
//and creates methods to generate JSON for the associated fields.

//Spring take care of storing instances of Java classes in a database with  interface that extends JpaRepository
@RepositoryRestResource
public interface PlayerRepository extends JpaRepository<Player,Long> {
    List<Player> findByUserName(String userName);

}
