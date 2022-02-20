package com.example.L10_jpa_demo.dbmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
    private Branch branch;


}
