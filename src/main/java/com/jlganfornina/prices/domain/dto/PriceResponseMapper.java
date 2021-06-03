package com.jlganfornina.prices.domain.dto;

public final class PriceResponseMapper {
    private PriceResponseMapper() {
    }

    public static PriceResponse fromPriceToPriceResponse(final Price price) {
        if (price == null) {
            return null;
        }

        return new PriceResponse(
                price.getPriceId(),
                price.getBrandId(),
                price.getProductId(),
                price.getStartDate(),
                price.getEndDate(),
                price.getPriority(),
                price.getPrice(),
                price.getCurrency());
    }
}
