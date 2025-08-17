# Cold Storage Microservice

This microservice is part of a distributed system and demonstrates a simple implementation of **CQRS (Command Query Responsibility Segregation)** principles. It focuses on simulating the **migration of data from a hot (fast-access) database to a cold (long-term storage) database**.

## Features

- Implements CQRS-based data handling
- Communicates with other services via **Kafka**
- Periodically **migrates data** from hot storage to cold storage using a scheduled task

## Architecture Overview

This microservice works alongside other services in a Kafka-based event-driven architecture. It subscribes to Kafka topics to receive events and stores them accordingly, simulating hot-to-cold storage transitions.

- **Hot database:** Temporary storage for recent data
- **Cold database:** Long-term storage
- **Archive database:** the storage for the archive policy
- **Kafka:** Used for communication between services
- **Scheduler:** Triggers the data migration process at regular intervals

## Use Case

This project demonstrates how time-sensitive data can be processed in a hot storage layer and periodically moved to a more permanent cold storage, as often used in systems requiring data retention optimization or analytics.

## Requirements

- Java 17+ (or your runtime version)
- Kafka (locally or remotely available)
- A configured hot and cold database (e.g., PostgreSQL, MongoDB)

## Running the Service

1. Make sure Kafka is running and accessible.
2. Start the hot and cold databases.
3. Run the microservice.
4. Observe logs to see scheduled migrations in action.

## License

MIT or your preferred license.
