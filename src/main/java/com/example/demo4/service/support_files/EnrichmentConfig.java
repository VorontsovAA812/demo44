package com.example.demo4.service.support_files;

import com.example.demo4.repos.FilterRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnrichmentConfig {

    @Bean
    public AtoBEnrichmentStep atoBEnrichmentStep() {
        return new AtoBEnrichmentStep();
    }

    @Bean
    public ConcatateEnrichmentStep concatateEnrichmentStep() {
        return new ConcatateEnrichmentStep();
    }

    @Bean
    public NoOpEnrichmentStep noOpEnrichmentStep() {
        return new NoOpEnrichmentStep();
    }
}
