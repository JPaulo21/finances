CREATE TYPE account_type AS ENUM ('CHECKING', 'CREDIT_CARD', 'CASH');

CREATE TABLE finance.accounts (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    type account_type NOT NULL,
    initial_balance DECIMAL(15, 2) NOT NULL DEFAULT 0.00,
    user_id INTEGER NOT NULL,
    created_by VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(50) NULL,
    updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL,
    CONSTRAINT FK_USER_ACCOUNT FOREIGN KEY (user_id) REFERENCES finance.users(id)
);