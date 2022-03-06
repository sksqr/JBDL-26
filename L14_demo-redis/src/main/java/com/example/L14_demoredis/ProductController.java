package com.example.L14_demoredis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private static String PREFIX = "product_";

    private static String ID_KEY = "nextId";


    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping
    public ResponseEntity<Long> createProduct(@RequestBody Product product){
        Long id = redisTemplate.opsForValue().increment(ID_KEY);
        product.setId(id);
        String key = PREFIX+id;
        redisTemplate.opsForValue().set(key,product);
        return ResponseEntity.ok(product.getId());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> createProduct(@PathVariable Long id){
        String key = PREFIX+id;
        Product product = (Product) redisTemplate.opsForValue().get(key);
        return ResponseEntity.ok(product);
    }


    @PostMapping("/addToList")
    public ResponseEntity<Long> addProduct(@RequestBody Product product){
        String key ="product_list";
        redisTemplate.opsForList().leftPush(key,product);
        return ResponseEntity.ok(product.getId());
    }


    @GetMapping("/getFromList")
    public ResponseEntity<Product> addProduct(){
        String key ="product_list";
        Product product = (Product) redisTemplate.opsForList().leftPop(key);
        return ResponseEntity.ok(product);
    }

}
