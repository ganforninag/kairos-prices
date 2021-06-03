package com.jlganfornina.prices.application;

import com.jlganfornina.prices.domain.PriceSearcherRepository;
import com.jlganfornina.prices.domain.dto.Price;
import com.jlganfornina.prices.domain.dto.PriceResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceSearcherServiceTest {
    @Mock
    private PriceSearcherRepository priceSearcherRepository;

    @InjectMocks
    private PriceSearcherService priceSearcherService;

    @Test
    void searchPrice() {
        final Long brandId = new Random().nextLong();
        final Long productId = new Random().nextLong();
        final LocalDateTime applicationDate = LocalDateTime.now();
        final Price expectedPrice = getPrice(brandId, productId);
        when(priceSearcherRepository.searchPrice(brandId, productId, applicationDate)).thenReturn(Optional.of(expectedPrice));

        final Optional<PriceResponse> optionalPriceResponse = priceSearcherService.searchPrice(brandId, productId, applicationDate);

        assertThat(optionalPriceResponse).isNotEmpty();
        assertThat(optionalPriceResponse.get().getPriceId()).isEqualTo(expectedPrice.getPriceId());
        assertThat(optionalPriceResponse.get().getBrandId()).isEqualTo(expectedPrice.getBrandId());
        assertThat(optionalPriceResponse.get().getProductId()).isEqualTo(expectedPrice.getProductId());
        assertThat(optionalPriceResponse.get().getStartDate()).isEqualTo(expectedPrice.getStartDate());
        assertThat(optionalPriceResponse.get().getEndDate()).isEqualTo(expectedPrice.getEndDate());
        assertThat(optionalPriceResponse.get().getPriority()).isEqualTo(expectedPrice.getPriority());
        assertThat(optionalPriceResponse.get().getPrice()).isEqualTo(expectedPrice.getPrice());
        assertThat(optionalPriceResponse.get().getCurrency()).isEqualTo(expectedPrice.getCurrency());
    }

    @Test
    void searchPrice_shouldThrowException_becauseBrandIdIsNull(){
        final Long brandId = null;
        final Long productId = new Random().nextLong();
        final LocalDateTime applicationDate = LocalDateTime.now();

        assertThatThrownBy(() -> priceSearcherService.searchPrice(brandId, productId, applicationDate))
                .isInstanceOf(PriceSearcherServiceException.class)
                .hasMessage("brandId cannot be null");

        verify(priceSearcherRepository, times(0)).searchPrice(brandId, productId, applicationDate);
    }

    @Test
    void searchPrice_shouldThrowException_becauseProductIdIsNull(){
        final Long brandId = new Random().nextLong();
        final Long productId = null;
        final LocalDateTime applicationDate = LocalDateTime.now();

        assertThatThrownBy(() -> priceSearcherService.searchPrice(brandId, productId, applicationDate))
                .isInstanceOf(PriceSearcherServiceException.class)
                .hasMessage("productId cannot be null");

        verify(priceSearcherRepository, times(0)).searchPrice(brandId, productId, applicationDate);
    }

    @Test
    void searchPrice_shouldThrowException_becauseApplicationDateIsNull(){
        final Long brandId = new Random().nextLong();
        final Long productId = new Random().nextLong();
        final LocalDateTime applicationDate = null;

        assertThatThrownBy(() -> priceSearcherService.searchPrice(brandId, productId, applicationDate))
                .isInstanceOf(PriceSearcherServiceException.class)
                .hasMessage("applicationDate cannot be null");

        verify(priceSearcherRepository, times(0)).searchPrice(brandId, productId, applicationDate);
    }

    private Price getPrice(final Long brandId, final Long productId) {
        final Long priceId = new Random().nextLong();
        final LocalDateTime startDate = LocalDateTime.now();
        final LocalDateTime endDate = LocalDateTime.now();
        final int priority = 0;
        final BigDecimal price = new BigDecimal("19.95");
        final String currency = "EUR";
        final LocalDateTime lastUpdateDate = LocalDateTime.now();
        final String lastUpdateBy = "user1";

        return new Price(priceId,
                         brandId,
                         productId,
                         startDate,
                         endDate,
                         priority,
                         price,
                         currency,
                         lastUpdateDate,
                         lastUpdateBy);
    }
}