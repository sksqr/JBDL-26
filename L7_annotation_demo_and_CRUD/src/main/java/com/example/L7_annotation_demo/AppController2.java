package com.example.L7_annotation_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AppController2 {

    @Autowired
    private ProductService productService;

    @GetMapping("getproducts2")
    public List<Product> getAllProducts(){
        System.out.println(productService);
        return productService.getAllProducts();
    }

}
