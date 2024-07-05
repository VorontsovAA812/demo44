package com.example.demo4.service.support_files;

import com.example.demo4.domain.Filter;

import java.util.List;

public interface EnrichmentStep {
    Filter enrich(Filter filter, List<Filter> intermediateResults);
    void setNext(EnrichmentStep step);
}
