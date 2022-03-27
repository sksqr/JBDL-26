package com.gfg.notification;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class EmailService {

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    public JavaMailSender javaMailSender;

    @KafkaListener(topics = "USER_CREATE",groupId = "notification-service-consumer1")
    public void listenKafkaTopic(String message) throws JsonProcessingException, ExecutionException, InterruptedException {
        Map<String,Object> map = objectMapper.readValue(message, HashMap.class);

        String email = (String) map.get("email");
        String name = (String) map.get("name");
        String subject = "Welcome "+name+" in JBDL-Wallet";
        String body = "Hi "+name+" Your account is created.";

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        mailMessage.setFrom("jbdl.ewallet@gmail.com");
        logger.info("Sending Welcome email to: {}",email);
        javaMailSender.send(mailMessage);


    }

    @KafkaListener(topics = "WALLET_UPDATE",groupId = "notification-service-consumer1")
    public void listenKafkaTopicWalletUpdate(String message) throws JsonProcessingException, ExecutionException, InterruptedException {
        try {


            Map<String, Object> map = objectMapper.readValue(message, HashMap.class);

            String email = (String) map.get("email");
            Double balance = Double.valueOf(String.valueOf(map.get("balance")));
            String subject = "Wallet Update";
            String body = "Your wallet updated to balance " + balance;

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(email);
            mailMessage.setSubject(subject);
            mailMessage.setText(body);
            mailMessage.setFrom("jbdl.ewallet@gmail.com");

            logger.info("Sending Wallet Update email to: {}", email);

            javaMailSender.send(mailMessage);

        }
        catch (Exception exception){
            logger.error("Exception in notification while reading from WALLET_UPDATE msg :{}",message);
        }
    }


    @KafkaListener(topics = "TRANSACTION_COMPLETE",groupId = "notification-service-consumer1")
    public void listenKafkaTopicTransactionComplete(String message) throws JsonProcessingException, ExecutionException, InterruptedException {
        Map<String,Object> map = objectMapper.readValue(message, HashMap.class);

        String email = (String) map.get("email");
        String text =  (String) map.get("text");
        String subject = (String) map.get("subject");


        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        mailMessage.setFrom("jbdl.ewallet@gmail.com");

        logger.info("Sending Transaction Complete email to: {}",email);

        javaMailSender.send(mailMessage);


    }
}
