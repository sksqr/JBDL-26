package com.example.L9_springjdbcdemo.controllers;

import com.example.L9_springjdbcdemo.PersonService;
import com.example.L9_springjdbcdemo.dao.PersonSpringJDBCDao;
import com.example.L9_springjdbcdemo.dbmodel.Person;
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

    @Autowired
    private PersonSpringJDBCDao personSpringJDBCDao;

    @PostMapping("/person")
    ResponseEntity createPerson(@RequestBody Person person) throws URISyntaxException {
        personSpringJDBCDao.insertPerson(person);
//        Integer id =personService.createPerson(person);
        return ResponseEntity.created(new URI("http://localhost:8080/person/")).build();
    }


    @GetMapping("/persons")
    List<Person> getAllPersons(){
        return personSpringJDBCDao.getAll();
    }


    @GetMapping("/person/{id}")
    Person getAllPersons(@PathVariable("id") Integer id){
        return personService.getPersonById(id);
    }


}
