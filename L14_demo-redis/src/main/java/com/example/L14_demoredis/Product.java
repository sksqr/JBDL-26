package com.example.L14_demoredis;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class Product implements Serializable {

    private Long id;

    private String name;

    private Double price;

}
