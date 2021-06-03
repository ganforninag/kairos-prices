package com.jlganfornina.prices.infrastructure.persistence;

import com.jlganfornina.prices.domain.dto.Price;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PriceSearcherRepositoryAdapterIntegrationTest {
    @Autowired
    private PriceSearcherRepositoryAdapter priceSearcherRepositoryAdapter;

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(priceSearcherRepositoryAdapter).isNotNull();
    }

    @ParameterizedTest
    @MethodSource("dataCollection")
    void searchPrice(final Long priceId, final Long brandId, final Long productId, final LocalDateTime applicationDate, final BigDecimal price) {
        final Optional<Price> optionalPrice = priceSearcherRepositoryAdapter.searchPrice(brandId, productId, applicationDate);

        assertThat(optionalPrice).isNotEmpty();
        assertThat(optionalPrice.get().getPriceId()).isEqualTo(priceId);
        assertThat(optionalPrice.get().getPrice()).isEqualTo(price);
        assertThat(optionalPrice.get().getBrandId()).isEqualTo(brandId);
        assertThat(optionalPrice.get().getProductId()).isEqualTo(productId);
    }

    private static Stream<Arguments> dataCollection() {
        return Stream.of(
                Arguments.of(1L, 1L, 35455L, LocalDateTime.of(2020, 6, 14, 10, 0, 0), BigDecimal.valueOf(35.50).setScale(2)),
                Arguments.of(2L, 1L, 35455L, LocalDateTime.of(2020, 6, 14, 16, 0, 0), BigDecimal.valueOf(25.45).setScale(2)),
                Arguments.of(1L, 1L, 35455L, LocalDateTime.of(2020, 6, 14, 21, 0, 0), BigDecimal.valueOf(35.50).setScale(2)),
                Arguments.of(3L, 1L, 35455L, LocalDateTime.of(2020, 6, 15, 10, 0, 0), BigDecimal.valueOf(30.50).setScale(2)),
                Arguments.of(4L, 1L, 35455L, LocalDateTime.of(2020, 6, 16, 21, 0, 0), BigDecimal.valueOf(38.95).setScale(2)));
    }
}