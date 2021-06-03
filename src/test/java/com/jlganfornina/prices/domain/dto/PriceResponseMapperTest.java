package com.jlganfornina.prices.domain.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

class PriceResponseMapperTest {

    @Test
    void fromPriceToPriceResponse_shouldReturnNull() {
        assertThat(PriceResponseMapper.fromPriceToPriceResponse(null)).isNull();
    }

    @Test
    void fromPriceToPriceResponse() {
        final Long priceId = new Random().nextLong();
        final Long brandId = new Random().nextLong();
        final Long productId = new Random().nextLong();
        final LocalDateTime startDate = LocalDateTime.now();
        final LocalDateTime endDate = LocalDateTime.now();
        final int priority = 0;
        final BigDecimal price = new BigDecimal("19.95");
        final String currency = "EUR";
        final LocalDateTime lastUpdateDate = LocalDateTime.now();
        final String lastUpdateBy = "user1";

        final Price priceDto = new Price(priceId,
                                         brandId,
                                         productId,
                                         startDate,
                                         endDate,
                                         priority,
                                         price,
                                         currency,
                                         lastUpdateDate,
                                         lastUpdateBy);

        final PriceResponse priceResponse = PriceResponseMapper.fromPriceToPriceResponse(priceDto);
        
        assertThat(priceResponse.getPriceId()).isEqualTo(priceDto.getPriceId());
        assertThat(priceResponse.getBrandId()).isEqualTo(priceDto.getBrandId());
        assertThat(priceResponse.getProductId()).isEqualTo(priceDto.getProductId());
        assertThat(priceResponse.getStartDate()).isEqualTo(priceDto.getStartDate());
        assertThat(priceResponse.getEndDate()).isEqualTo(priceDto.getEndDate());
        assertThat(priceResponse.getPriority()).isEqualTo(priceDto.getPriority());
        assertThat(priceResponse.getPrice()).isEqualTo(priceDto.getPrice());
        assertThat(priceResponse.getCurrency()).isEqualTo(priceDto.getCurrency());
    }
}