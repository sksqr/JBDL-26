package com.example.L9_springjdbcdemo.dbmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {

    private Integer id;
    private String name;
    private String email;
    private String phone;

}
