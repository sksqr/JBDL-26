package com.gfg.anotations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringAnotationMainDemo {

    @Bean
    public SMSGateway smsGatewayBean(){
        return new SMSGateway();
    }

    @Bean("notificationService")
    public NotificationService notificationServiceBean(){
        return new NotificationService();
    }


    public static void main(String[] args) {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringAnotationMainDemo.class);
        NotificationService notificationService = (NotificationService) ctx.getBean("notificationService");
        notificationService.sendNotification("OTP is 1098");


    }
}
