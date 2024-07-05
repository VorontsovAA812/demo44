package com.example.demo4.service;

import com.example.demo4.domain.Filter;
import com.example.demo4.rest.dto.TaskDtos.NewTaskRequest;
import com.example.demo4.rest.dto.TaskDtos.TaskDto;

import com.example.demo4.service.support_files.EnrichmentStep;

import java.util.List;

public interface TaskService {
    List<TaskDto> GetAllTasks();
    Long addNewTask(NewTaskRequest request);
    void deleteById(Long id);
   String processString(String inputString, List<String> filterNames);
   EnrichmentStep createEnrichmentChain(List<String> filterNames);

}
