package com.jlganfornina.prices.infrastructure.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PriceSearcherJpaRepository extends JpaRepository<PriceEntity, Long> {
    @Query("Select price " +
           "from PriceEntity price " +
           "where price.brandId = :brandId " +
           "and price.productId = :productId " +
           "and :applicationDate between price.startDate and price.endDate " +
           "order by price.priority desc ")
    Page<PriceEntity> searchPrice(Long brandId, Long productId, LocalDateTime applicationDate, Pageable pageable);
}
