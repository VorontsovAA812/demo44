package com.example.demo4.rest.dto.FilterDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewFilterRequest
{

    private Long taskId;
    private List<FilterDto> filters;

}
