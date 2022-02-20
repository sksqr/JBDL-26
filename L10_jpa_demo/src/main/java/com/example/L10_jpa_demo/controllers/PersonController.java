package com.example.L10_jpa_demo.controllers;

import com.example.L10_jpa_demo.dbmodel.Address;
import com.example.L10_jpa_demo.dbmodel.Person;
import com.example.L10_jpa_demo.dbmodel.SalarySlip;
import com.example.L10_jpa_demo.dtos.PersonDto;
import com.example.L10_jpa_demo.repository.AddressRepository;
import com.example.L10_jpa_demo.repository.SalarySlipRepository;
import com.example.L10_jpa_demo.service.PersonService;
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
    private AddressRepository addressRepository;

    @Autowired
    private SalarySlipRepository salarySlipRepository;


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


    @PostMapping("/address")
    ResponseEntity createAddress(@RequestBody Address address) throws URISyntaxException {
       address = addressRepository.save(address);
        return ResponseEntity.created(new URI("http://localhost:8080/person/"+address.getId())).build();
    }

    @PostMapping("/slip")
    ResponseEntity createSalarySlip(@RequestBody SalarySlip salarySlip) throws URISyntaxException {
        salarySlip = salarySlipRepository.save(salarySlip);
        return ResponseEntity.created(new URI("http://localhost:8080/person/"+salarySlip.getId())).build();
    }
}
