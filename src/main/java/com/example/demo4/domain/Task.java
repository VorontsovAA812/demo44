package com.example.demo4.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "state_id")
    private State state;
    @CreationTimestamp
    @Temporal(TemporalType.TIME)
    private java.util.Date startTime;

    @Temporal(TemporalType.TIME)
    private java.util.Date endTime;



    @ManyToOne(fetch = FetchType.EAGER)
    private User usr;




    @OneToMany(fetch = FetchType.EAGER, mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Filter> filters;
}
