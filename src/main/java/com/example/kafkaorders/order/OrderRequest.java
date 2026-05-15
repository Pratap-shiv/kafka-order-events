package com.example.kafkaorders.order;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record OrderRequest(
        @NotBlank String customerName,
        @NotBlank String itemName,
        @NotNull @DecimalMin("1.00") BigDecimal amount,
        OrderStatus status
) {
}
