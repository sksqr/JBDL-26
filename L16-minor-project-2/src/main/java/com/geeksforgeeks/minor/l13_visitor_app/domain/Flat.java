package com.geeksforgeeks.minor.l13_visitor_app.domain;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Set;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Flat implements Serializable {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String number;

    @OneToMany(mappedBy = "flat", fetch = FetchType.LAZY)
    private Set<Visit> visits;

    @OneToMany(mappedBy = "flat", fetch = FetchType.LAZY)
    private Set<User> users;

    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    @PrePersist
    public void prePersist() {
        dateCreated = OffsetDateTime.now();
        lastUpdated = dateCreated;
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdated = OffsetDateTime.now();
    }

}
