package com.example.L9_springjdbcdemo;


import com.example.L9_springjdbcdemo.dao.PersonDAO;
import com.example.L9_springjdbcdemo.dbmodel.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonDAO personDAO;

    public Integer createPerson(Person person){
        return personDAO.insertPerson(person);
    }

    public List<Person> getAll(){
        return personDAO.selectPersons();
    }

    public Person getPersonById(Integer id){
        return personDAO.selectPersonById(id);
    }
}
