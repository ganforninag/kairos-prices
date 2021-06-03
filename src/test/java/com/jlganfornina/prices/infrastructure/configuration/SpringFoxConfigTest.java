package com.jlganfornina.prices.infrastructure.configuration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class SpringFoxConfigTest {
    @InjectMocks
    private SpringFoxConfig springFoxConfig;

    @Test
    void api() {
        assertThat(springFoxConfig.api()).isNotNull();
    }
}