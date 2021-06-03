package com.jlganfornina.prices.infrastructure.persistence;

import com.jlganfornina.prices.domain.dto.Price;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

class PriceMapperTest {
    @Test
    void fromPriceEntityToPrice_shouldReturnNull() {
        assertThat(PriceMapper.fromPriceEntityToPrice(null)).isNull();
    }

    @Test
    void fromPriceEntityToPrice() {
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
        final PriceEntity priceEntity = new PriceEntity(priceId,
                                                        brandId,
                                                        productId,
                                                        startDate,
                                                        endDate,
                                                        priority,
                                                        price,
                                                        currency,
                                                        lastUpdateDate,
                                                        lastUpdateBy);

        final Price priceMapped = PriceMapper.fromPriceEntityToPrice(priceEntity);

        assertThat(priceMapped).isNotNull();
        assertThat(priceMapped.getPriceId()).isEqualTo(priceEntity.getPriceId());
        assertThat(priceMapped.getBrandId()).isEqualTo(priceEntity.getBrandId());
        assertThat(priceMapped.getProductId()).isEqualTo(priceEntity.getProductId());
        assertThat(priceMapped.getStartDate()).isEqualTo(priceEntity.getStartDate());
        assertThat(priceMapped.getEndDate()).isEqualTo(priceEntity.getEndDate());
        assertThat(priceMapped.getPriority()).isEqualTo(priceEntity.getPriority());
        assertThat(priceMapped.getPrice()).isEqualTo(priceEntity.getPrice());
        assertThat(priceMapped.getCurrency()).isEqualTo(priceEntity.getCurrency());
        assertThat(priceMapped.getLastUpdateDate()).isEqualTo(priceEntity.getLastUpdateDate());
        assertThat(priceMapped.getLastUpdateBy()).isEqualTo(priceEntity.getLastUpdateBy());
    }
}