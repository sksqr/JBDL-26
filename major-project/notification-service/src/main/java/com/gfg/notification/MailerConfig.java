package com.gfg.notification;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailerConfig {

    @Bean
    public JavaMailSender getJavaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");;
        javaMailSender.setUsername("jbdl.ewallet@gmail.com");
        javaMailSender.setPassword("jbdl@026");
        javaMailSender.setPort(587);
        javaMailSender.getJavaMailProperties().put("mail.smtp.starttls.enable", true);
        javaMailSender.getJavaMailProperties().put("mail.debug", true);

        return javaMailSender;
    }
}
