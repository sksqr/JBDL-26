package com.geeksforgeeks.minor.l11_visitor_app.domain;

import com.geeksforgeeks.minor.l11_visitor_app.model.VisitStatus;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Visit {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VisitStatus status;

    @Column
    private LocalDateTime inTime;

    @Column
    private LocalTime outTime;

    @Column(nullable = false)
    private String purpose;

    @Column(nullable = false)
    private Integer noOfPeople;

    @Column
    private String imageUrl;

    @OneToMany(mappedBy = "visitor")
    private Set<Visitor> visitorVisitors;

    @OneToOne
    @JoinColumn(name = "flat_id")
    private Flats flat;

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
