package com.example.L18kafkademo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.SendResult;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
    private static Logger LOGGER = LoggerFactory.getLogger(KafkaController.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;


    @RequestMapping("/publish")
    ResponseEntity<String> publish(@RequestParam String msg) throws ExecutionException, InterruptedException {
//        Future<SendResult> future = kafkaTemplate.send("jbdl-kafka-demo-part2","shashi",msg);
        Future<SendResult> future = kafkaTemplate.send("demo-topic",msg);

        LOGGER.info("Data published to kafka : {}"+future.get());

        return ResponseEntity.ok("Pushed");
    }
}
