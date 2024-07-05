package com.example.demo4.service.impl;

import com.example.demo4.domain.Filter;
import com.example.demo4.domain.Task;
import com.example.demo4.domain.User;
import com.example.demo4.repos.FilterRepo;
import com.example.demo4.repos.TaskRepo;

import com.example.demo4.rest.dto.TaskDtos.NewTaskRequest;
import com.example.demo4.rest.dto.TaskDtos.TaskDto;
import com.example.demo4.service.TaskService;
import com.example.demo4.service.support_files.EnrichmentStep;
import com.example.demo4.service.support_files.NoOpEnrichmentStep;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import com.example.demo4.repos.UserRepo;
import com.example.demo4.service.support_files.EnrichmentStep;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{
    public final TaskRepo taskRepo;
    public final UserRepo userRepo;
    public final FilterRepo filterRepo;
    @Autowired
    private ApplicationContext applicationContext;
    @Override
    public List<TaskDto> GetAllTasks()
    {
        List<Task> all = taskRepo.findAll();
        return all.stream()
                .map(task -> TaskDto.builder()

                        .stateId(task.getState().getId()).build()).toList();
    }
    //GetAllFitlersInTask
    @Override
    public Long addNewTask(NewTaskRequest request){

        Task task = new Task();
        Optional<User> element=userRepo.findById(request.getUserId());
        User usr = element.get();
        task.setUsr(usr);
        taskRepo.saveAndFlush(task);
        return task.getId();
    }
    @Override
    public void deleteById(Long id) {
        taskRepo.deleteById(id);
    }
    @Override
    public String processString(String inputString, List<String> filterNames) {
        // Создаем начальный фильтр с входной строкой
        Filter initialFilter = new Filter();
        initialFilter.setOutputInfo(inputString);

        // Создаем цепочку шагов обогащения
        EnrichmentStep chainHead = createEnrichmentChain(filterNames);

        // Список для хранения промежуточных результатов
        List<Filter> intermediateResults = new ArrayList<>();
        intermediateResults.add(initialFilter);

        // Обрабатываем фильтр
        chainHead.enrich(initialFilter, intermediateResults);

        // Устанавливаем ссылки на следующий фильтр
        for (int i = 0; i < intermediateResults.size() - 1; i++) {
            Filter currentFilter = intermediateResults.get(i);
            Filter nextFilter = intermediateResults.get(i + 1);
            currentFilter.setNextFilter(nextFilter.getId());
        }

        // Сохраняем все промежуточные результаты в правильном порядке
        for (Filter filter : intermediateResults) {
            filterRepo.save(filter);
        }

        // Возвращаем обработанную строку
        return intermediateResults.get(intermediateResults.size() - 1).getOutputInfo();
    }

    @Override
    public EnrichmentStep createEnrichmentChain(List<String> filterNames) {
        EnrichmentStep chainHead = null;
        EnrichmentStep previousStep = null;

        for (String filterName : filterNames) {
            EnrichmentStep step = (EnrichmentStep) applicationContext.getBean(filterName);
            if (chainHead == null) {
                chainHead = step; // Первый шаг в цепочке
            } else {
                previousStep.setNext(step); // Устанавливаем следующий шаг для предыдущего
            }
            previousStep = step; // Обновляем previousStep
        }

        if (previousStep != null) {
            EnrichmentStep noOpStep = (EnrichmentStep) applicationContext.getBean("noOpEnrichmentStep");
            previousStep.setNext(noOpStep);
        }

        return chainHead;
    }}
