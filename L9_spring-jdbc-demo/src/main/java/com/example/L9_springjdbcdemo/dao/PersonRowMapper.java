package com.example.L9_springjdbcdemo.dao;

import com.example.L9_springjdbcdemo.dbmodel.Person;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PersonRowMapper implements RowMapper {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setId(rs.getInt("id"));
        person.setName(rs.getString("name"));
        person.setEmail(rs.getString("email"));
        person.setPhone(rs.getString("phone"));
        return person;
    }
}
