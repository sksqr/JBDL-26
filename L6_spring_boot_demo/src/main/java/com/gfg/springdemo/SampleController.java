package com.gfg.springdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
public class SampleController {

    static Logger logger = LoggerFactory.getLogger(SampleController.class);
    Set<Product> productList = new HashSet<>();

    @GetMapping("/test")
    public String getdata(){
     return "Hello !!"  ;
    }

    @GetMapping("/get/{keyword}")
    public List<Product> getByKeyword(@PathVariable("keyword") String keyword){
        logger.info("Request for {}",keyword);
        List<Product> response = new ArrayList<>();
        for(Product product : productList){
            if(product.getName().equalsIgnoreCase(keyword)){
                response.add(product);
            }
        }
        return response;
    }

    @GetMapping("/search")
    public List<Product> search(@RequestParam("keyword") String keyword){
        logger.info("Request for {}",keyword);
        List<Product> response = new ArrayList<>();
        for(Product product : productList){
            if(product.getName().equalsIgnoreCase(keyword)){
                response.add(product);
            }
        }
        return response;
    }

    @GetMapping("/product")
    public Product getProduct(){
        return new Product(1,"Laptop HP")  ;
    }


    @GetMapping("/products")
    public Set<Product> getAllProducts(){
        return productList  ;
    }

    @PostMapping("/product")
    public void addProduct(@RequestBody Product product){
        productList.add(product);

    }



}
