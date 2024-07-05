package com.example.demo4.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "filter")
public class Filter {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id")
    private Task task;

    private String outputInfo;
    private String type;

    private Long nextFilter;

    public Filter copy() {
        Filter copy = new Filter();
        copy.setTask(this.task);
        copy.setOutputInfo(this.outputInfo);
        copy.setType(this.type);
        return copy;
    }
}
