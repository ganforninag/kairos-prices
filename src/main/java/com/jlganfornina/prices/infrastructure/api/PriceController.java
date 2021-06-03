package com.jlganfornina.prices.infrastructure.api;

import com.jlganfornina.prices.application.PriceSearcherService;
import com.jlganfornina.prices.domain.dto.PriceResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/api/prices")
@RequiredArgsConstructor
@Api(tags = "Price services")
public class PriceController {
    private final PriceSearcherService priceSearcherService;

    @GetMapping()
    public ResponseEntity<PriceResponse> searchPrice(
            @ApiParam(name = "brandId", value = "Brand identifier", required = true)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            @RequestParam Long brandId,
            @ApiParam(name = "productId", value = "Product identifier", required = true)
            @RequestParam Long productId,
            @ApiParam(name = "applicationDate", value = "Application date", required = true)
            @RequestParam LocalDateTime applicationDate) {
        return priceSearcherService.searchPrice(brandId, productId, applicationDate)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
