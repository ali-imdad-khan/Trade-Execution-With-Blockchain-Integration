-- Create Users Table
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       balance NUMERIC(19, 4) NOT NULL DEFAULT 0.00
);

-- Create Orders Table
CREATE TABLE orders (
                        id SERIAL PRIMARY KEY,
                        user_id BIGINT NOT NULL,
                        order_type VARCHAR(10) NOT NULL, -- BUY or SELL
                        quantity NUMERIC(19, 4) NOT NULL,
                        price NUMERIC(19, 4) NOT NULL,
                        status VARCHAR(20) NOT NULL, -- NEW, MATCHED, COMPLETED
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        FOREIGN KEY (user_id) REFERENCES users (id)
);

-- Create Trades Table
CREATE TABLE trades (
                        id SERIAL PRIMARY KEY,
                        buyer_id BIGINT NOT NULL,
                        seller_id BIGINT NOT NULL,
                        trade_price NUMERIC(19, 4) NOT NULL,
                        trade_quantity NUMERIC(19, 4) NOT NULL,
                        timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        FOREIGN KEY (buyer_id) REFERENCES users (id),
                        FOREIGN KEY (seller_id) REFERENCES users (id)
);

-- Create Ledger Table
CREATE TABLE ledger (
                        id SERIAL PRIMARY KEY,
                        user_id BIGINT NOT NULL,
                        transaction_type VARCHAR(20) NOT NULL, -- DEPOSIT, WITHDRAWAL, TRADE
                        amount NUMERIC(19, 4) NOT NULL,
                        timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        FOREIGN KEY (user_id) REFERENCES users (id)
);
