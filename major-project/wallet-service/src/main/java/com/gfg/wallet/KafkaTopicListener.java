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
public class KafkaTopicListener {


    @Autowired
    private IWalletRepository walletRepository;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;


    private  static  Logger logger= LoggerFactory.getLogger(KafkaTopicListener.class);

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


    @KafkaListener(topics = "TRANSACTION_CREATE",groupId = "wallet-service-consumer")
    public void listenTransactionCreate(String message) throws JsonProcessingException, ExecutionException, InterruptedException {
        logger.info("Consuming data:{}",message);
        //JSONObject jsonObject = objectMapper.readValue(message,JSONObject.class);
        Map<String,Object> map = objectMapper.readValue(message, HashMap.class);
        String sender = (String) map.get("sender");
        String receiver = (String) map.get("receiver");
        String txnId = (String) map.get("txnId");
        Double amount = Double.valueOf(String.valueOf(map.get("amount")));

        Wallet senderWallet = walletRepository.findByEmail(sender);
        Wallet receiverWallet = walletRepository.findByEmail(receiver);

        JSONObject transactionStatusChange = new JSONObject();
        if(senderWallet==null || receiverWallet==null || senderWallet.getBalance() < amount){
            transactionStatusChange.put("status","FAILED");
            logger.info("Marking Transaction Fail");
        }
        else{
            senderWallet.setBalance(senderWallet.getBalance()-amount);
            receiverWallet.setBalance(receiverWallet.getBalance()+amount);

            walletRepository.save(senderWallet);
            walletRepository.save(receiverWallet);

            JSONObject walletUpdateEvent = new JSONObject();
            walletUpdateEvent.put("email",senderWallet.getEmail());
            walletUpdateEvent.put("balance",senderWallet.getBalance());
            ListenableFuture<SendResult<String, String>> sendResultFuture = kafkaTemplate.send("WALLET_UPDATE",walletUpdateEvent.toString());
            logger.info("Producing walletUpdateEvent to WALLET_UPDATE topic, kafka response: {}, for:{}  ",sendResultFuture.get(), senderWallet.getEmail());


            JSONObject walletUpdateEventReceiver = new JSONObject();
            walletUpdateEvent.put("email",receiverWallet.getEmail());
            walletUpdateEvent.put("balance",receiverWallet.getBalance());
            ListenableFuture<SendResult<String, String>> sendResultFutureReceiver = kafkaTemplate.send("WALLET_UPDATE",walletUpdateEventReceiver.toString());
            logger.info("Producing walletUpdateEvent to WALLET_UPDATE topic, kafka response: {} for {} ",sendResultFutureReceiver.get(),receiverWallet.getEmail() );
            transactionStatusChange.put("status","SUCCESS");

        }




        transactionStatusChange.put("sender",senderWallet.getEmail());
        transactionStatusChange.put("txnId",txnId);
        transactionStatusChange.put("amount",amount);
        ListenableFuture<SendResult<String, String>> sendResultTxnStatusChange = kafkaTemplate.send("TRANSACTION_STATUS_CHANGE",transactionStatusChange.toString());
        logger.info("Producing transactionStatusChange to TRANSACTION_STATUS_CHANGE topic, kafka response: {} ",sendResultTxnStatusChange.get());

    }
}
