package com.jlganfornina.prices.application;

import com.jlganfornina.prices.domain.PriceSearcherRepository;
import com.jlganfornina.prices.domain.dto.PriceResponse;
import com.jlganfornina.prices.domain.dto.PriceResponseMapper;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class PriceSearcherService {
    private final PriceSearcherRepository priceSearcherRepository;

    public Optional<PriceResponse> searchPrice(final Long brandId, final Long productId, final LocalDateTime applicationDate) {
        ensureBrandIdIsValid(brandId);
        ensureProductIdIsValid(productId);
        ensureApplicationDateIsValid(applicationDate);
        return priceSearcherRepository.searchPrice(brandId, productId, applicationDate)
                .map(PriceResponseMapper::fromPriceToPriceResponse);
    }

    private void ensureBrandIdIsValid(final Long brandId) {
        if (Objects.isNull(brandId)) {
            throw new PriceSearcherServiceException("brandId cannot be null");
        }
    }

    private void ensureProductIdIsValid(final Long productId) {
        if (Objects.isNull(productId)) {
            throw new PriceSearcherServiceException("productId cannot be null");
        }
    }

    private void ensureApplicationDateIsValid(final LocalDateTime applicationDate) {
        if (Objects.isNull(applicationDate)) {
            throw new PriceSearcherServiceException("applicationDate cannot be null");
        }
    }
}
