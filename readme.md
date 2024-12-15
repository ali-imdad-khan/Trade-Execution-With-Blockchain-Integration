# Trade Execution and Settlement System with Blockchain Integration

## Overview

The **Trade Execution and Settlement System** is a backend application designed to simulate a trading platform. It provides essential functionality for user account management, order placement, trade matching, and settlement. The project integrates blockchain technology to ensure trade transparency and immutability while leveraging modern tools for scalability and reliability.

---

## Features

- **User Management**:
    - Create user accounts with associated balances.
    - Retrieve user details.
- **Order Management**:
    - Place BUY and SELL orders with price and quantity.
    - Retrieve all active orders.
- **Order Matching**:
    - Automatically match BUY and SELL orders based on price and type.
- **Trade Settlement**:
    - Record matched trades in a PostgreSQL database.
    - Persist trade data on the blockchain for transparency.
- **Blockchain Integration**:
    - Smart contract deployed on Ganache to store trade records immutably.
- **Asynchronous Processing**:
    - Kafka-based messaging system for scalable and efficient order handling.

---

## Technologies Used

### Backend
- **Java**: Core programming language for the backend.
- **Spring Boot**: Framework for building REST APIs and managing the application lifecycle.

### Database
- **PostgreSQL**: Relational database for storing user, order, and trade data.

### Messaging
- **Apache Kafka**: Distributed messaging system for handling asynchronous order processing.

### Blockchain
- **Ganache**: Local Ethereum blockchain for smart contract deployment and transaction testing.
- **Solidity**: Smart contract programming language used for blockchain integration.
- **Web3J**: Java library for interacting with the Ethereum blockchain and smart contracts.

### Development Tools
- **Truffle**: Framework for developing, deploying, and testing Ethereum smart contracts.
- **Docker**: Containerization for running PostgreSQL, Kafka, and Ganache.
- **Maven**: Dependency management and build tool for the Java application.

---

## APIs

### User Management
- **POST /api/users**: Create a new user.
- **GET /api/users/{id}**: Retrieve user details by ID.

### Order Management
- **POST /api/orders**: Place a new BUY or SELL order.
- **GET /api/orders**: Retrieve all active orders.

### Trade Management
- **GET /api/trades**: Retrieve all settled trades.

### Blockchain
- **POST /api/blockchain/recordTrade**: Manually record a trade on the blockchain.
- **GET /api/blockchain/getTradesCount**: Retrieve the total number of trades recorded on the blockchain.

---

## How It Works

1. **User and Order Management**:
    - Users place BUY and SELL orders through the provided REST APIs.
    - Orders are stored in the database and sent to a Kafka topic for asynchronous processing.

2. **Order Matching**:
    - A Kafka consumer processes orders, matches BUY and SELL orders based on price and type, and creates a trade record.

3. **Trade Settlement**:
    - Matched trades are stored in PostgreSQL for reporting and analytics.
    - Simultaneously, trades are recorded on the blockchain for immutability and transparency.

4. **Blockchain Integration**:
    - Smart contract functions like `recordTrade` and `getTradesCount` enable secure and immutable trade storage on Ganache.

---

## Setup Instructions

### Prerequisites
- Java 11 or higher
- Docker
- Ganache (local blockchain)

### Steps
1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd trade-execution-and-settlement
