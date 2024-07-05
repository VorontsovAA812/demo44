package com.example.demo4.service.support_files;

import com.example.demo4.domain.Filter;
import com.example.demo4.repos.FilterRepo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class ConcatateEnrichmentStep implements EnrichmentStep {
    private EnrichmentStep next;

    @Autowired
    private FilterRepo filterRepository;

    @Override
    public Filter enrich(Filter filter, List<Filter> intermediateResults) {
        String outputInfo = filter.getOutputInfo();
        if (outputInfo != null) {
            outputInfo = outputInfo.replaceAll(" ", "");
        }

        // Создаем новый фильтр для промежуточного состояния
        Filter newFilter = filter.copy();
        newFilter.setOutputInfo(outputInfo);
        newFilter.setType("Concatate");

        // Добавляем промежуточный результат в список
        intermediateResults.add(newFilter);

        if (next != null) {
            return next.enrich(newFilter, intermediateResults);
        }

        return newFilter;
    }

    @Override
    public void setNext(EnrichmentStep step) {
        this.next = step;
    }
}
