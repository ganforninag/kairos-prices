package com.jlganfornina.prices.infrastructure.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "price")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PriceEntity {
    @Id
    private Long priceId;
    @Column(nullable = false)
    private Long brandId;
    @Column(nullable = false)
    private Long productId;
    @Column(nullable = false)
    private LocalDateTime startDate;
    @Column(nullable = false)
    private LocalDateTime endDate;
    @Column(nullable = false)
    private int priority;
    @Column(nullable = false, precision = 20, scale = 2, columnDefinition = "NUMERIC(20,2)")
    private BigDecimal price;
    @Column(nullable = false, length = 3)
    private String currency;
    @Column(nullable = false)
    private LocalDateTime lastUpdateDate;
    @Column(nullable = false)
    private String lastUpdateBy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceEntity that = (PriceEntity) o;
        return Objects.equals(priceId, that.priceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(priceId);
    }
}
