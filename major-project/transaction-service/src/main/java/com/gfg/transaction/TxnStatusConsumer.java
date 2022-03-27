package com.gfg.transaction;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfg.transaction.entities.Transaction;
import com.gfg.transaction.repo.TransactionRepository;
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
public class TxnStatusConsumer {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    private TransactionRepository transactionRepository;


    private  static Logger logger= LoggerFactory.getLogger(TxnStatusConsumer.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "TRANSACTION_STATUS_CHANGE",groupId = "transaction-service-consumer")
    public void listenTransactionCreate(String message) throws JsonProcessingException, ExecutionException, InterruptedException {
        logger.info("Consuming data:{}",message);
        //JSONObject jsonObject = objectMapper.readValue(message,JSONObject.class);
        Map<String,Object> map = objectMapper.readValue(message, HashMap.class);
        String sender = (String) map.get("sender");
        String txnId = (String) map.get("txnId");
        String status = (String) map.get("status");
        Double amount = Double.valueOf(String.valueOf(map.get("amount")));

        Transaction transaction = transactionRepository.findByTxnId(txnId);
        TransactionStatus transactionStatus = TransactionStatus.valueOf(status);
        transaction.setStatus(transactionStatus);
        transactionRepository.save(transaction);

        JSONObject transactionCompleteEvent = new JSONObject();
        transactionCompleteEvent.put("email",sender);
        transactionCompleteEvent.put("subject","Transaction "+transactionStatus.name());
        if(transactionStatus.equals(TransactionStatus.SUCCESS)){
            transactionCompleteEvent.put("text","Your Transaction with txnId "+txnId+" Failed");
        }
        else{
            transactionCompleteEvent.put("text","Your Transaction with txnId "+txnId+" Success");
        }


        ListenableFuture<SendResult<String, String>> sendResultFuture = kafkaTemplate.send("TRANSACTION_COMPLETE",transactionCompleteEvent.toString());
        logger.info("Producing transactionCompleteEvent to TRANSACTION_COMPLETE topic, kafka response: {}  ",sendResultFuture.get());


    }

}


