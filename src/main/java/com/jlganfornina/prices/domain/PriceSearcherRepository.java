package com.jlganfornina.prices.domain;

import com.jlganfornina.prices.domain.dto.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceSearcherRepository {
    Optional<Price> searchPrice(Long brandId, Long productId, LocalDateTime applicationDate);
}
