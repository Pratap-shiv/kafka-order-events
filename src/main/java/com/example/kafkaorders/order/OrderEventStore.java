package com.example.kafkaorders.order;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.stereotype.Component;

@Component
public class OrderEventStore {

    private final List<OrderEvent> events = new CopyOnWriteArrayList<>();

    public void save(OrderEvent event) {
        events.add(event);
    }

    public List<OrderEvent> findAll() {
        return events.stream()
                .sorted(Comparator.comparing(OrderEvent::createdAt).reversed())
                .toList();
    }

    public void clear() {
        events.clear();
    }
}
