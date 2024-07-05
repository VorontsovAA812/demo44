package com.example.demo4.rest.dto.TaskDtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskDto {

    private Integer start_time;
    private Integer end_time;
    private Long stateId;
}
