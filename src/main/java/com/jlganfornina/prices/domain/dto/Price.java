package com.jlganfornina.prices.domain.dto;

import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
public class Price {
    Long priceId;
    Long brandId;
    Long productId;
    LocalDateTime startDate;
    LocalDateTime endDate;
    int priority;
    BigDecimal price;
    String currency;
    LocalDateTime lastUpdateDate;
    String lastUpdateBy;
}
