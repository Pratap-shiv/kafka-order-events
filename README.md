# Kafka Order Events

A Spring Boot 4 + Java 17 project that demonstrates Apache Kafka with a clean REST producer, Kafka consumer, Docker Compose, and Kafka UI.

## Tech Stack

- Java 17
- Spring Boot 4.0.6
- Spring Web, Validation, Actuator
- Spring for Apache Kafka
- Apache Kafka in KRaft mode
- Docker multi-stage builds
- Docker Compose
- Kafka UI
- JUnit 5

## What This Project Shows

- Publishing JSON events to Kafka from a REST API
- Consuming Kafka messages with `@KafkaListener`
- Using Kafka message keys for order-based partitioning
- Externalizing topic and broker configuration
- Running Kafka without ZooKeeper using KRaft mode
- Inspecting topics and messages through Kafka UI
- Health-checking a containerized Spring Boot service

## Run With Docker Compose

```bash
docker compose up --build
```

Services:

| Service | URL |
| --- | --- |
| API | `http://localhost:8081` |
| Kafka broker inside Compose | `kafka:9092` |
| Kafka broker from host tools | `localhost:29092` |
| Kafka UI | `http://localhost:8088` |

## Try The API

Publish an order event:

```bash
curl -X POST http://localhost:8081/api/orders \
  -H "Content-Type: application/json" \
  -d '{"customerName":"Asha Sharma","itemName":"Mechanical Keyboard","amount":2499,"status":"CREATED"}'
```

Read events consumed by the service:

```bash
curl http://localhost:8081/api/orders/events
```

Health check:

```bash
curl http://localhost:8081/actuator/health
```

## Kafka Practice Checklist

- Explain producer, topic, consumer, and consumer group.
- Publish several orders and inspect them in Kafka UI.
- Change `ORDER_TOPIC` and restart the stack.
- Check app logs to see consumed events.
- Exec into the Kafka container and list topics.
- Stop the app, publish events, restart the app, and observe offset behavior.

## Useful Commands

```bash
docker compose ps
docker compose logs -f app
docker compose logs -f kafka
docker exec -it kafka-order-events-broker kafka-topics.sh --bootstrap-server kafka:9092 --list
docker exec -it kafka-order-events-broker kafka-console-consumer.sh --bootstrap-server kafka:9092 --topic orders.v1 --from-beginning
docker compose down
```

## Test

```bash
mvn test
```

If Maven is not installed locally:

```bash
docker run --rm -v "$PWD:/workspace" -w /workspace -v maven_cache:/root/.m2 \
  maven:3.9.9-eclipse-temurin-17 mvn test
```
