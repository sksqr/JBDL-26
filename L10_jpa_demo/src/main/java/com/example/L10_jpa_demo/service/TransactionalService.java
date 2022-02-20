package com.example.L10_jpa_demo.service;

import com.example.L10_jpa_demo.dbmodel.Address;
import com.example.L10_jpa_demo.dbmodel.Branch;
import com.example.L10_jpa_demo.dbmodel.Person;
import com.example.L10_jpa_demo.repository.BranchRepository;
import com.example.L10_jpa_demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLDataException;
import java.sql.SQLException;

@Service
public class TransactionalService {


    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private PersonService personService;

//

    @Transactional(dontRollbackOn={RuntimeException.class})
    public void transactionalMethodJPA(){
        Person person  = new Person();
        person.setName("RAvi kumar");
        person.setEmail("ravikumar@gmail.com");
        person = personRepository.save(person);
        System.out.println(person);
        throw new RuntimeException();
    }
    /*
    public void proxyOftransactionalMethodJPA{
     // start transaction
        try {
            transactionalMethodJPA()
            //commit
        }
        catch (Exception exception){
            //roll back transaction
        }
    }

     */



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
}
