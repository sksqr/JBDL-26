package com.gfg.wallet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfg.wallet.dbentities.Wallet;
import com.gfg.wallet.dbentities.WalletStatus;
import com.gfg.wallet.repo.IWalletRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class CreateUserListener {


    @Autowired
    private IWalletRepository walletRepository;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;


    private  static  Logger logger= LoggerFactory.getLogger(CreateUserListener.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "USER_CREATE",groupId = "wallet-service-consumer")
    public void listenKafkaTopic(String message) throws JsonProcessingException, ExecutionException, InterruptedException {
        logger.info("Consuming data:{}",message);
        //JSONObject jsonObject = objectMapper.readValue(message,JSONObject.class);
        Map<String,Object> map = objectMapper.readValue(message, HashMap.class);
        String email = (String) map.get("email");
        Wallet wallet = Wallet.builder()
                .email(email)
                .balance(100.00)
                .walletStatus(WalletStatus.ACTIVE)
                .build();
        walletRepository.save(wallet);

        JSONObject walletUpdateEvent = new JSONObject();
        walletUpdateEvent.put("email",email);
        walletUpdateEvent.put("balance",wallet.getBalance());
        ListenableFuture<SendResult<String, String>> sendResultFuture = kafkaTemplate.send("WALLET_UPDATE",walletUpdateEvent.toString());
        logger.info("Producing walletUpdateEvent to WALLET_UPDATE topic, kafka response: {}  ",sendResultFuture.get());

    }
}
