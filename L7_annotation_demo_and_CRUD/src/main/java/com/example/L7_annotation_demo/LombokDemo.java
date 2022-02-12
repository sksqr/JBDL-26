package com.example.L7_annotation_demo;

import lombok.extern.slf4j.Slf4j;

public class LombokDemo {
    public static void main(String[] args) {
        Product product = new Product();
        product.setId(1);
        product.setName("Mobile");
        Product product2 = new Product(1,"Laptop");

        System.out.println(product);

    }

    private void  methodCall(){
    }

}
