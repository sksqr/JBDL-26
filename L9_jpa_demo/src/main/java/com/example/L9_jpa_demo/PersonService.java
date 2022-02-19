package com.example.L9_jpa_demo;


import com.example.L9_jpa_demo.dbmodel.Person;
import com.example.L9_jpa_demo.dtos.PersonDto;
import com.example.L9_jpa_demo.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

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
        return personRepository.getById(id);
    }


    public void deletePerson(Integer id){
        personRepository.deleteById(id);
    }
}
