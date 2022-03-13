package com.example.L15springsecurity;

import com.example.L15springsecurity.model.MyDBUser;
import com.example.L15springsecurity.repositories.MyDbUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class L15SpringSecurityApplication implements CommandLineRunner {

	@Autowired
	private MyDbUserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;


	public static void main(String[] args) {
		SpringApplication.run(L15SpringSecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		long count = userRepository.count();
		if(count==0){
			MyDBUser myDBUser = new MyDBUser();
			myDBUser.setUsername("admin");
			myDBUser.setPassword(passwordEncoder.encode("123"));
			myDBUser.setRoles("admin");
			userRepository.save(myDBUser);
		}
	}
}
