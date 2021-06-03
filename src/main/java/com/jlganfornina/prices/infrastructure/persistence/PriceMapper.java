package com.jlganfornina.prices.infrastructure.persistence;

import com.jlganfornina.prices.domain.dto.Price;

public final class PriceMapper {
    private PriceMapper() {
    }

    public static Price fromPriceEntityToPrice(final PriceEntity priceEntity) {
        if (priceEntity == null) {
            return null;
        }

        return new Price(priceEntity.getPriceId(),
                         priceEntity.getBrandId(),
                         priceEntity.getProductId(),
                         priceEntity.getStartDate(),
                         priceEntity.getEndDate(),
                         priceEntity.getPriority(),
                         priceEntity.getPrice(),
                         priceEntity.getCurrency(),
                         priceEntity.getLastUpdateDate(),
                         priceEntity.getLastUpdateBy());
    }
}
