package com.example.demo4.rest.dto.FilterDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilterDto {
    private Long filterId; // Явный идентификатор фильтра

}
