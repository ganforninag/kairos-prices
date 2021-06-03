package com.jlganfornina.prices.infrastructure.api;

import com.jlganfornina.prices.KairosPricesApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = KairosPricesApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration
@AutoConfigureTestDatabase
class PriceControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @MethodSource("dataCollection")
    void searchPrice(final Long priceId, final Long brandId, final Long productId, final LocalDateTime applicationDate,
                     final BigDecimal price, final String currency) throws Exception {
        final ResultMatcher expectedStatus = status().isOk();
        final ResultMatcher expectedContentType = content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON);

        final MockHttpServletRequestBuilder additionRequest = MockMvcRequestBuilders.get("/api/prices")
                .param("brandId", brandId.toString())
                .param("productId", productId.toString())
                .param("applicationDate", applicationDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(additionRequest)
                .andExpect(expectedStatus)
                .andExpect(expectedContentType)
                .andExpect(jsonPath("$.priceId", is(priceId), Long.class))
                .andExpect(jsonPath("$.brandId", is(brandId), Long.class))
                .andExpect(jsonPath("$.productId", is(productId), Long.class))
                .andExpect(jsonPath("$.price", is(price), BigDecimal.class))
                .andExpect(jsonPath("$.currency", is(currency)));
    }

    @Test
    void searchPrice_shouldReturnBadRequest_becauseBrandIdIsEmpty() throws Exception {
        final String brandId = "";
        final String productId = "35455";
        final LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        final ResultMatcher expectedStatus = status().isBadRequest();

        final MockHttpServletRequestBuilder additionRequest = MockMvcRequestBuilders.get("/api/prices")
                .param("brandId", brandId)
                .param("productId", productId)
                .param("applicationDate", applicationDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(additionRequest)
                .andExpect(expectedStatus);
    }

    @Test
    void searchPrice_shouldReturnBadRequest_becauseProductIdIsEmpty() throws Exception {
        final String brandId = "1";
        final String productId = "";
        final LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        final ResultMatcher expectedStatus = status().isBadRequest();

        final MockHttpServletRequestBuilder additionRequest = MockMvcRequestBuilders.get("/api/prices")
                .param("brandId", brandId)
                .param("productId", productId)
                .param("applicationDate", applicationDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(additionRequest)
                .andExpect(expectedStatus);
    }

    @Test
    void searchPrice_shouldReturnBadRequest_becauseApplicationDateIsEmpty() throws Exception {
        final String brandId = "1";
        final String productId = "35455";
        final String applicationDate = "";
        final ResultMatcher expectedStatus = status().isBadRequest();

        final MockHttpServletRequestBuilder additionRequest = MockMvcRequestBuilders.get("/api/prices")
                .param("brandId", brandId)
                .param("productId", productId)
                .param("applicationDate", applicationDate)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(additionRequest)
                .andExpect(expectedStatus);
    }

    private static Stream<Arguments> dataCollection() {
        return Stream.of(
                Arguments.of(1L, 1L, 35455L, LocalDateTime.of(2020, 6, 14, 10, 0, 0), BigDecimal.valueOf(35.50), "EUR"),
                Arguments.of(2L, 1L, 35455L, LocalDateTime.of(2020, 6, 14, 16, 0, 0), BigDecimal.valueOf(25.45), "EUR"),
                Arguments.of(1L, 1L, 35455L, LocalDateTime.of(2020, 6, 14, 21, 0, 0), BigDecimal.valueOf(35.50), "EUR"),
                Arguments.of(3L, 1L, 35455L, LocalDateTime.of(2020, 6, 15, 10, 0, 0), BigDecimal.valueOf(30.50), "EUR"),
                Arguments.of(4L, 1L, 35455L, LocalDateTime.of(2020, 6, 16, 21, 0, 0), BigDecimal.valueOf(38.95), "EUR"));
    }
}