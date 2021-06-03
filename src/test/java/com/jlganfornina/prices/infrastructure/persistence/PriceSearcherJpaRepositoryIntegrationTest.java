package com.jlganfornina.prices.infrastructure.persistence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PriceSearcherJpaRepositoryIntegrationTest {
    @Autowired
    private PriceSearcherJpaRepository priceSearcherJpaRepository;

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(priceSearcherJpaRepository).isNotNull();
    }

    @ParameterizedTest
    @MethodSource("dataCollection")
    void searchPrice(final Long priceId, final Long brandId, final Long productId, final LocalDateTime applicationDate, final BigDecimal price) {
        final Page<PriceEntity> priceEntityPage = priceSearcherJpaRepository
                .searchPrice(brandId, productId, applicationDate, PageRequest.of(0, 1));

        assertThat(priceEntityPage.getContent().size()).isEqualTo(1);
        assertThat(priceEntityPage.getContent().get(0).getPriceId()).isEqualTo(priceId);
        assertThat(priceEntityPage.getContent().get(0).getPrice()).isEqualTo(price);
        assertThat(priceEntityPage.getContent().get(0).getBrandId()).isEqualTo(brandId);
        assertThat(priceEntityPage.getContent().get(0).getProductId()).isEqualTo(productId);
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