package com.example.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// A dispatcher is code that examines the URL sent by a client and decides what method of what class to pass the URL.
//Spring creates a dispatcher, using patterns collected from the classes you define and annotations you write.
// a RestRepository, such as Person, creates a large number of URL patterns, such as /persons, /persons/{id}, /persons/search, and so on
//@RequestMapping("/games") on a method adds that URL to the dispatcher ( to use distinct prefixes for the data URLs.)


//converting this java data into a string using JavaScript Object Notation (JSON)
@RestController
//when we do a request to /api we will get the method we determine
@RequestMapping("/api")
//Mapping to api prevent any accidental overlap with the REST repository URLs, because we've made them start with /rest.
public class SalvoController {

    //@Autowired tells Spring to automatically create an instance of GameRepository and store it in the instance variable gameRepository
    // This technique is called dependency injection.
    // We let the framework create and "inject" the objects we need, rather than writing a call to the new operator
    @Autowired GameRepository gameRepository;

    @RequestMapping("/games")
    public Map<String,Object> getGames(){
        Map<String,Object> gamesDTO = new HashMap<>();
        //repo.findAll() return a list of all instances in this repo
        //this method returns a list of games existing in the GameRepo
//        return gameRepository.findAll()
//                .stream()
//                .map(g -> g.getId()).collect(Collectors.toList());
        return gamesDTO;
    }

}
