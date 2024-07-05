package com.example.demo4.rest.dto.TaskDtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class AddFiltersDto {

        private String inputString;
        private List<String> filterNames;
}
