package com.gfg.user.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfg.user.UserCreateRequest;
import com.gfg.user.dbentities.User;
import com.gfg.user.repo.IUserRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.websocket.SendResult;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private IUserRepository userRepository;


    @Autowired
    private KafkaTemplate kafkaTemplate;


    public Long createUser(UserCreateRequest userCreateRequest) throws ExecutionException, InterruptedException {
        User user = userCreateRequest.toUser();
        user = userRepository.save(user);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email",user.getEmail());
        jsonObject.put("name",user.getName());
        Future<SendResult> resultFuture = kafkaTemplate.send("USER_CREATE",jsonObject.toString());
        logger.info("Pushed event to kafka topic USER_CREATE, response :{}", resultFuture.get());
        logger.info("User Created : {}",user.getEmail());

        return user.getId();
    }
}
