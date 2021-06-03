package com.jlganfornina.prices.infrastructure.persistence;

import com.jlganfornina.prices.domain.PriceSearcherRepository;
import com.jlganfornina.prices.domain.dto.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PriceSearcherRepositoryAdapter implements PriceSearcherRepository {
    private final PriceSearcherJpaRepository priceSearcherJpaRepository;

    @Override
    public Optional<Price> searchPrice(Long brandId, Long productId, LocalDateTime applicationDate) {
        return priceSearcherJpaRepository.searchPrice(brandId, productId, applicationDate, PageRequest.of(0, 1)).stream()
                .findFirst().map(PriceMapper::fromPriceEntityToPrice);
    }
}
