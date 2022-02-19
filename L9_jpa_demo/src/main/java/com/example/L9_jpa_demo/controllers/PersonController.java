package com.example.L9_jpa_demo.controllers;

import com.example.L9_jpa_demo.PersonService;
import com.example.L9_jpa_demo.dbmodel.Person;
import com.example.L9_jpa_demo.dtos.PersonDto;
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
    ResponseEntity createPerson(@RequestBody PersonDto personDto) throws URISyntaxException {
        Integer id =personService.createPerson(personDto);
        return ResponseEntity.created(new URI("http://localhost:8080/person/")).build();
    }


    @GetMapping("/persons")
    List<Person> getAllPersons(){
        return personService.getAll();
    }


    @GetMapping("/person/{id}")
    Person getAllPersons(@PathVariable("id") Integer id){
        return personService.getPersonById(id);
    }

    @DeleteMapping("/person/{id}")
    void deletePerson(@PathVariable("id") Integer id){
        personService.deletePerson(id);
    }


}
