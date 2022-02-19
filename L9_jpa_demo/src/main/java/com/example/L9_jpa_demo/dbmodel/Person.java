package com.example.L9_jpa_demo.dbmodel;

import lombok.*;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Person {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    private String phone;

}
