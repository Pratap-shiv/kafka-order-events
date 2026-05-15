package com.example.kafkaorders.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    private static final Logger log = LoggerFactory.getLogger(OrderConsumer.class);

    private final OrderEventStore eventStore;

    public OrderConsumer(OrderEventStore eventStore) {
        this.eventStore = eventStore;
    }

    @KafkaListener(topics = "${app.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(@Payload OrderEvent event) {
        log.info("Consumed order event {} for order {}", event.eventId(), event.orderId());
        eventStore.save(event);
    }
}
