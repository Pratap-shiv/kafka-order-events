package com.example.kafkaorders.order;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class OrderEventTest {

    @Test
    void createsEventWithDefaultStatus() {
        OrderRequest request = new OrderRequest(
                "Asha Sharma",
                "Mechanical Keyboard",
                BigDecimal.valueOf(2499),
                null
        );

        OrderEvent event = OrderEvent.from(request);

        assertThat(event.eventId()).isNotNull();
        assertThat(event.orderId()).isNotNull();
        assertThat(event.status()).isEqualTo(OrderStatus.CREATED);
        assertThat(event.customerName()).isEqualTo("Asha Sharma");
    }
}
