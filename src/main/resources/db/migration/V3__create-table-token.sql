CREATE TABLE finance.refresh_tokens (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    refresh_token VARCHAR(255) NULL,
    expires_at TIMESTAMP NULL,
    FOREIGN KEY (user_id) REFERENCES finance.users(id) ON DELETE CASCADE
);
