package com.example.demo4.service.support_files;

import com.example.demo4.domain.Filter;
import com.example.demo4.repos.FilterRepo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class NoOpEnrichmentStep implements EnrichmentStep {

    @Autowired
    private FilterRepo filterRepository;

    @Override
    public Filter enrich(Filter filter, List<Filter> intermediateResults) {
        // Создаем новый фильтр для конечного состояния
        Filter newFilter = filter.copy();
        newFilter.setType("NoOp");

        // Добавляем конечный результат в список
        intermediateResults.add(newFilter);

        return newFilter;
    }

    @Override
    public void setNext(EnrichmentStep step) {
        // No next step for NoOpEnrichmentStep
    }
}
