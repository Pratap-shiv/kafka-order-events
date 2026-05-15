package com.example.kafkaorders.order;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record OrderEvent(
        UUID eventId,
        UUID orderId,
        String customerName,
        String itemName,
        BigDecimal amount,
        OrderStatus status,
        Instant createdAt
) {
    static OrderEvent from(OrderRequest request) {
        return new OrderEvent(
                UUID.randomUUID(),
                UUID.randomUUID(),
                request.customerName(),
                request.itemName(),
                request.amount(),
                request.status() == null ? OrderStatus.CREATED : request.status(),
                Instant.now()
        );
    }
}
