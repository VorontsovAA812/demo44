package com.example.demo4.repos;

import com.example.demo4.domain.Task;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepo  extends JpaRepository<Task,Long> {


}
