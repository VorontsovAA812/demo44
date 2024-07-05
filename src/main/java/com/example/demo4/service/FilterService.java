package com.example.demo4.service;


import com.example.demo4.domain.Filter;
import com.example.demo4.domain.User;
import com.example.demo4.rest.dto.FilterDtos.NewFilterRequest;
import com.example.demo4.service.support_files.EnrichmentStep;

import java.util.List;

public interface FilterService
{
    Filter processFilter(Long filterId, List<String> filterNames);
    EnrichmentStep createEnrichmentChain(List<String> filterNames);
}
