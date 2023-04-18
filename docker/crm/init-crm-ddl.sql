-- Create the database 'mydb' if it does not exist
CREATE DATABASE IF NOT EXISTS suitecrm;

-- Use the 'mydb' database
USE suitecrm;

-- Drop the 'users' table if it exists
DROP TABLE IF EXISTS users;

-- Create the 'users' table
CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50),
  age INT,
  email VARCHAR(50)
);

-- Insert sample data into the 'users' table
INSERT INTO users (name, age, email) VALUES ('John Doex', 25, 'john.doe@example.com');
INSERT INTO users (name, age, email) VALUES ('Jane Smith', 30, 'jane.smith@example.com');
INSERT INTO users (name, age, email) VALUES ('Bob Johnson', 35, 'bob.johnson@example.com');
