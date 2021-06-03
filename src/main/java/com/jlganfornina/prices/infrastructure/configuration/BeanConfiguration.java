package com.jlganfornina.prices.infrastructure.configuration;

import com.jlganfornina.prices.application.PriceSearcherService;
import com.jlganfornina.prices.domain.PriceSearcherRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public PriceSearcherService priceSearcherService(final PriceSearcherRepository priceSearcherRepository) {
        return new PriceSearcherService(priceSearcherRepository);
    }
}
