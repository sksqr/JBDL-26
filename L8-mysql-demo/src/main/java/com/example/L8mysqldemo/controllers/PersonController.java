package com.example.L8mysqldemo.controllers;

import com.example.L8mysqldemo.dbmodel.Person;
import com.example.L8mysqldemo.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/person")
    ResponseEntity createPerson(@RequestBody Person person) throws URISyntaxException {
        Integer id =personService.createPerson(person);
        return ResponseEntity.created(new URI("http://localhost:8080/person/"+id)).build();
    }


    @GetMapping("/persons")
    List<Person> getAllPersons(){
        return personService.getAll();
    }


    @GetMapping("/person/{id}")
    Person getAllPersons(@PathVariable("id") Integer id){
        return personService.getPersonById(id);
    }


}
