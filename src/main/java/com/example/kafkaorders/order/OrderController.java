package com.example.kafkaorders.order;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderProducer orderProducer;
    private final OrderEventStore eventStore;

    public OrderController(OrderProducer orderProducer, OrderEventStore eventStore) {
        this.orderProducer = orderProducer;
        this.eventStore = eventStore;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public OrderEvent publish(@Valid @RequestBody OrderRequest request) {
        return orderProducer.publish(request);
    }

    @GetMapping("/events")
    public List<OrderEvent> events() {
        return eventStore.findAll();
    }
}
