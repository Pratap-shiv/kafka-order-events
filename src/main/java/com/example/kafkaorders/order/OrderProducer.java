package com.example.kafkaorders.order;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;
    private final String topic;

    public OrderProducer(KafkaTemplate<String, OrderEvent> kafkaTemplate,
                         @Value("${app.kafka.topic}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public OrderEvent publish(OrderRequest request) {
        OrderEvent event = OrderEvent.from(request);
        kafkaTemplate.send(topic, event.orderId().toString(), event);
        return event;
    }
}
