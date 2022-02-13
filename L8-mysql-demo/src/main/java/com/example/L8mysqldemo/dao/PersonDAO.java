package com.example.L8mysqldemo.dao;

import com.example.L8mysqldemo.dbmodel.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PersonDAO {

    private AtomicInteger atomicInteger ;

    @Value("${dbUrl}")
    private String dbUrl;


    @Value("${db.username}")
    private String username;


    @Value("${db.password}")
    private String password;

    @PostConstruct
    void init(){
        atomicInteger = new AtomicInteger(getMaxId());
    }

    private Integer getMaxId(){
        Connection connection =null;
        Integer id = 0;
        try {
            connection = DriverManager.getConnection(dbUrl,username,password);
            Statement statement = connection.createStatement();
            String query = "select MAX(id) from person";
            System.out.println(query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                id = resultSet.getInt(1);
            }
//            boolean result = statement.execute("insert into person values (null,'Gaurav','gaurav@yopmail.com','9999999',null,null,null)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    public Integer insertPerson(Person person){
        Connection connection =null;
        Integer id =null;
        try {
            connection = DriverManager.getConnection(dbUrl,username,password);
            Statement statement = connection.createStatement();
            id = atomicInteger.incrementAndGet();
            String query = "insert into person values ("+id+",'"+person.getName()+"','"+person.getEmail()+"','"+person.getPhone()+"',null,null,null)";
            System.out.println(query);
            statement.execute(query);
//            boolean result = statement.execute("insert into person values (null,'Gaurav','gaurav@yopmail.com','9999999',null,null,null)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }


    public List<Person> selectPersons(){
        Connection connection =null;
        List<Person> list = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(dbUrl,username,password);
            Statement statement = connection.createStatement();
            String query = "select * from person";
            System.out.println(query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Person person = new Person(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("email"),resultSet.getString("phone"));
                list.add(person);
            }
//            boolean result = statement.execute("insert into person values (null,'Gaurav','gaurav@yopmail.com','9999999',null,null,null)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }


    public Person selectPersonById(Integer id){
        Connection connection =null;
        Person person = null;
        try {
            connection = DriverManager.getConnection(dbUrl,username,password);
            Statement statement = connection.createStatement();
            String query = "select * from person where id="+id;
            System.out.println(query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                person = new Person(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("email"),resultSet.getString("phone"));
            }
//            boolean result = statement.execute("insert into person values (null,'Gaurav','gaurav@yopmail.com','9999999',null,null,null)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return person;
    }




    public void insertPersonForDemo(Person person){
        Connection connection =null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_system","app1","jbdl26");
            Statement statement = connection.createStatement();
            String query = "insert into person values (null,'"+person.getName()+"','"+person.getEmail()+"','"+person.getPhone()+"',null,null,null)";
            System.out.println(query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.println(resultSet);
            }

//            boolean result = statement.execute("insert into person values (null,'Gaurav','gaurav@yopmail.com','9999999',null,null,null)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }




    public List<Person> selectPersonForDemo(){
        Connection connection =null;
        List<Person> list = new ArrayList<>();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_system","app1","jbdl26");
            Statement statement = connection.createStatement();
            String query = "select * from person";
            System.out.println(query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Person person = new Person(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("email"),resultSet.getString("phone"));
                list.add(person);
            }
//            boolean result = statement.execute("insert into person values (null,'Gaurav','gaurav@yopmail.com','9999999',null,null,null)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }


    public static void main(String[] args) {


        PersonDAO personDAO = new PersonDAO();
//        personDAO.selectPersonForDemo().forEach( person -> {
//            System.out.println(person);
//        });

        Person person = new Person(null,"Lakshya kuma","lakshyakumar@yopmail.com","8678686786");
        personDAO.insertPersonForDemo(person);
    }

}
