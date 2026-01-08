CREATE TYPE transaction_type AS ENUM('INCOME', 'EXPENSE');

CREATE TABLE finance.transactions (
    id SERIAL PRIMARY KEY,
    amount DECIMAL(15, 2) NOT NULL,
    type transaction_type NOT NULL,
    description VARCHAR(255) NULL,
    date TIMESTAMP NOT NULL,
    account_id INTEGER NOT NULL,
    category_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    created_by VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(50) NULL,
    updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL,
    CONSTRAINT FK_ACCOUNT_TRANSACTION FOREIGN KEY (account_id) REFERENCES finance.accounts(id),
    CONSTRAINT FK_CATEGORY_TRANSACTION FOREIGN KEY (category_id) REFERENCES finance.categories(id),
    CONSTRAINT FK_USER_TRANSACTION FOREIGN KEY (user_id) REFERENCES finance.users(id)
);