package com.example.L10_jpa_demo.dbmodel;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@ToString
@Entity
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String branchName;

    @OneToMany(mappedBy = "branch", fetch = FetchType.EAGER)
    private Set<Person> personSet;


}
