package com.jlganfornina.prices.infrastructure.configuration;

import com.jlganfornina.prices.domain.PriceSearcherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class BeanConfigurationTest {
    @Mock
    private PriceSearcherRepository priceSearcherRepository;

    @InjectMocks
    private BeanConfiguration beanConfiguration;

    @Test
    void priceSearcherService() {
        assertThat(beanConfiguration.priceSearcherService(priceSearcherRepository)).isNotNull();
    }
}