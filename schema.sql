-- Video Library Database Schema
-- Auto-initializes on Docker startup (data is seeded separately via seed.sh)

CREATE TABLE IF NOT EXISTS vcd_record (
  vcd_id VARCHAR(4) PRIMARY KEY,
  vcd_title VARCHAR(255) NOT NULL,
  vcd_genre VARCHAR(50),
  vcd_rating VARCHAR(10),
  vcd_price_in_rupees INT,
  NO_OF_UNITS_AVAILABLE INT
);

CREATE TABLE IF NOT EXISTS customer_record (
  cust_id VARCHAR(4) PRIMARY KEY,
  cust_name VARCHAR(255) NOT NULL,
  cust_ph_no VARCHAR(10),
  cust_address VARCHAR(500)
);

CREATE TABLE IF NOT EXISTS transactionrcvd (
  order_id INT PRIMARY KEY AUTO_INCREMENT,
  vcd_id VARCHAR(4),
  vcd_title VARCHAR(255),
  cust_id VARCHAR(4),
  cust_name VARCHAR(255),
  date_of_issue VARCHAR(10),
  date_of_return VARCHAR(10),
  date_returned_on VARCHAR(10),
  FOREIGN KEY (vcd_id) REFERENCES vcd_record(vcd_id),
  FOREIGN KEY (cust_id) REFERENCES customer_record(cust_id)
);

CREATE INDEX idx_vcd_id ON transactionrcvd(vcd_id);
CREATE INDEX idx_cust_id ON transactionrcvd(cust_id);
CREATE INDEX idx_returned ON transactionrcvd(date_returned_on);
