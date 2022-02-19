package com.example.L9_springjdbcdemo;

import com.example.L9_springjdbcdemo.dao.PersonRowMapper;
import com.example.L9_springjdbcdemo.dbmodel.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
public class L9SpringJdbcDemoApplication {

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/product_system");
		dataSource.setUsername("app1");
		dataSource.setPassword("jbdl26");
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource){
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(JdbcTemplate jdbcTemplate){
		return new NamedParameterJdbcTemplate(jdbcTemplate);
	}


	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(L9SpringJdbcDemoApplication.class, args);
		DataSource dataSource = (DataSource) applicationContext.getBean("dataSource");

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String fetchPersons ="select * from person";
		List<Person> personList = jdbcTemplate.query(fetchPersons,new PersonRowMapper());
		System.out.println(personList);



	}

}
