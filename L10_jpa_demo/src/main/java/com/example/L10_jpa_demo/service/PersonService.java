package com.example.L10_jpa_demo.service;


import com.example.L10_jpa_demo.dbmodel.Branch;
import com.example.L10_jpa_demo.dbmodel.Person;
import com.example.L10_jpa_demo.dtos.PersonDto;
import com.example.L10_jpa_demo.repository.BranchRepository;
import com.example.L10_jpa_demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BranchRepository branchRepository;

    public Integer createPerson(PersonDto personDto){

        Person person = new Person();
        person.setEmail(personDto.getEmail());
        person.setName(personDto.getName());
        person.setPhone(personDto.getPhone());

        Person person1 =personRepository.save(person);
        return person.getId();
    }

    public List<Person> getAll(){
        return personRepository.findAll();
    }

    public Person getPersonById(Integer id){

        return personRepository.findById(id).get();
    }


    public void deletePerson(Integer id){
        personRepository.deleteById(id);

    }


    @Transactional
    public void transactionalMethod(){
        // start transaction
        try {
            personRepository.findById(4).get();
            personRepository.save(new Person());
            branchRepository.save(new Branch());
            //commit
        }
        catch (Exception exception){
            //roll back transaction
        }
    }

    public  Person getPersonByName(String name){
//        personRepository.g
        return null;
    }
}
