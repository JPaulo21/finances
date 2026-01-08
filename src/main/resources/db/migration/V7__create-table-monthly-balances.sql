CREATE TABLE finance.monthly_balances (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    account_id INTEGER NOT NULL,
    year INTEGER NOT NULL,
    month INTEGER NOT NULL,
    income_total NUMERIC(15, 2) NOT NULL,
    expense_total NUMERIC(15, 2) NOT NULL,
    balance NUMERIC(15, 2) NOT NULL,
    created_by VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(50) NULL,
    updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL,
    CONSTRAINT fk_monthly_balance_user FOREIGN KEY(user_id) REFERENCES finance.users(id),
    CONSTRAINT fk_monthly_balance_account FOREIGN KEY(account_id) REFERENCES finance.accounts(id),
    CONSTRAINT unique_account_month UNIQUE(account_id, month)
);)