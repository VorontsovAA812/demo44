package com.example.demo4.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usr")  // Изменено здесь
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String username;
    private String role;
    private String password;
    @OneToMany(fetch= FetchType.EAGER,mappedBy= "usr",cascade = CascadeType.ALL)
    private List<Task> tasks;

}
