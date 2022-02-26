package com.example.L10_jpa_demo.dbmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    private String phone;

    @OneToOne(  cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId")
    private Address address;

    @ManyToOne(  cascade = CascadeType.ALL)
    @JoinColumn(name = "branchId")
    @JsonIgnoreProperties("personSet")
    private Branch branch;


}
