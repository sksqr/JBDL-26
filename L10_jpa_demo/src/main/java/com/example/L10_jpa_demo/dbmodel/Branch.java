package com.example.L10_jpa_demo.dbmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@ToString
@Entity
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String branchName;

    @OneToMany(mappedBy = "branch", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("branch")
    private Set<Person> personSet;


}
