package com.example.L9_springjdbcdemo.dao;

import com.example.L9_springjdbcdemo.dbmodel.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PersonSpringJDBCDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PersonRowMapper personRowMapper;

    @Value("${person.query.insert}")
    private String insertSql;

    @Value("${person.query.fetchPersons}")
    private String fetchPersons;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Person> getAll(){
        return jdbcTemplate.query(fetchPersons,personRowMapper);
    }

    public Person getById(Integer id){
        return null;
    }

    public void insertPerson(Person person){
        Map<String,Object> valueMap = new HashMap<>();
        valueMap.put("id",person.getId());
        valueMap.put("name",person.getName());
        valueMap.put("email",person.getEmail());
        valueMap.put("phone",person.getPhone());
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(valueMap);
        namedParameterJdbcTemplate.update(insertSql,sqlParameterSource);
    }


    public void updatePerson(Person person){

    }

}
