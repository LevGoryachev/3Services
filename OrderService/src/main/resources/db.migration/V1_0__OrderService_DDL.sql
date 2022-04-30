-- OrderServiceDB, version 1.0, syntax: Postgres

CREATE TABLE "order"(
order_id BIGSERIAL PRIMARY KEY,
customer_name VARCHAR(100) NOT NULL CHECK(customer_name !=''),
address VARCHAR(100),
summ DECIMAL(25,2) NOT NULL CHECK(summ > 0),
created_date TIMESTAMP DEFAULT now()
);

CREATE TABLE "order_details"(
item_number BIGINT NOT NULL,
serial_number VARCHAR(100),
product_name VARCHAR(100) NOT NULL CHECK(product_name !=''),
qty BIGINT NOT NULL CHECK(qty > 0),
order_id BIGINT REFERENCES "order"(order_id) ON DELETE CASCADE,
PRIMARY KEY (item_number, order_id)
);






