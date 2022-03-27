package com.gfg.transaction.services;


import com.gfg.transaction.TransactionRequest;
import com.gfg.transaction.entities.Transaction;
import com.gfg.transaction.repo.TransactionRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
public class TransactionService {

    private static Logger logger = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public String doTransaction(TransactionRequest transactionRequest){
        Transaction transaction = transactionRequest.toTransaction();
        transaction =transactionRepository.save(transaction);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sender",transaction.getSender());
        jsonObject.put("receiver",transaction.getReceiver());
        jsonObject.put("amount",transaction.getAmount());
        jsonObject.put("txnId",transaction.getTxnId());

        ListenableFuture<SendResult<String, String>> sendResultFuture =  kafkaTemplate.send("TRANSACTION_CREATE",jsonObject.toString());

        logger.info("Published data in topic TRANSACTION_CREATE , Data: {}",jsonObject.toString());


        return transaction.getTxnId();
    }
}
