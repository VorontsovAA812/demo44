package com.example.demo4.rest.controller;


import com.example.demo4.domain.Filter;
import com.example.demo4.rest.dto.TaskDtos.AddFiltersDto;
import com.example.demo4.rest.dto.TaskDtos.NewTaskRequest;
import com.example.demo4.rest.dto.TaskDtos.TaskDto;
import com.example.demo4.service.FilterService;
import com.example.demo4.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;


    @GetMapping
    public ResponseEntity<List<TaskDto>> getAll() //<List<UserDto>> - озвращаемый на запрос список пользователей в формате DTO
    {

        return ResponseEntity.ok(taskService.GetAllTasks());
    }


    @PostMapping("/filters")
    public String processString(@RequestBody AddFiltersDto request) {
        return taskService.processString(request.getInputString(), request.getFilterNames());
    }

    @PostMapping
    public ResponseEntity<Long> add(@RequestBody NewTaskRequest request) // Long - возвращаем индекс добавленного пользователя
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.addNewTask(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id)
    {
        taskService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
