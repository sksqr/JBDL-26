package com.geeksforgeeks.minor.l11_visitor_app.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("com.geeksforgeeks.minor.l11_visitor_app.domain")
@EnableJpaRepositories("com.geeksforgeeks.minor.l11_visitor_app.repos")
@EnableTransactionManagement
public class DomainConfig {
}
