package com.example.L10_jpa_demo;

import com.example.L10_jpa_demo.dbmodel.Address;
import com.example.L10_jpa_demo.dbmodel.Branch;
import com.example.L10_jpa_demo.dbmodel.Person;
import com.example.L10_jpa_demo.repository.AddressRepository;
import com.example.L10_jpa_demo.repository.BranchRepository;
import com.example.L10_jpa_demo.repository.PersonRepository;
import com.example.L10_jpa_demo.service.TransactionalService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.time.Period;

@SpringBootApplication
public class L10JpaDemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(L10JpaDemoApplication.class, args);

		// One branch can have many people/persons
		demoOneToManyMapping(applicationContext);

//		TransactionalService transactionalService = (TransactionalService) applicationContext.getBean("transactionalService");
//		transactionalService.transactionalMethodJPA();





//		demoManyToOne(args);

//		demoOneToOneMapping(applicationContext);
	}

	private static void demoOneToManyMapping(ConfigurableApplicationContext applicationContext) {
		BranchRepository branchRepository = (BranchRepository) applicationContext.getBean("branchRepository");
		Branch branch = branchRepository.findById(1).get();
		System.out.println(branch.getBranchName());
		for(Person person : branch.getPersonSet()){
			System.out.println(person.getName());
		}
	}

	private static void demoManyToOne(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(L10JpaDemoApplication.class, args);
		PersonRepository personRepository = (PersonRepository) applicationContext.getBean("personRepository");
		Person person  =personRepository.findById(9).get();

		BranchRepository branchRepository = (BranchRepository) applicationContext.getBean("branchRepository");
		Branch branch = branchRepository.findById(1).get();
		person.setBranch(branch);
		person = personRepository.save(person);


		Person person2  =personRepository.findById(10).get();
		person2.setBranch(branch);
		person2 = personRepository.save(person2);
	}

	private static void demoOneToOneMapping(ConfigurableApplicationContext applicationContext) {
		PersonRepository personRepository = (PersonRepository) applicationContext.getBean("personRepository");
		Person person  = new Person();
		person.setName("Shashi");
		person.setEmail("sksk@gmail.com");
		Address address = new Address();
		address.setLine1("Delhi");
		person.setAddress(address);
		person = personRepository.save(person);
		System.out.println(person);


//		PersonRepository personRepository = (PersonRepository) applicationContext.getBean("personRepository");
//		Person person  =personRepository.findById(9).get();
//		System.out.println(person);

//		AddressRepository addressRepository = (AddressRepository) applicationContext.getBean("addressRepository");
//		Address address = addressRepository.findById(7).get();
//
//		person.setAddress(address);
//		person = personRepository.save(person);
//
//		System.out.println(person);
	}


}
