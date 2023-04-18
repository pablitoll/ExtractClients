-- Create the database 'mydb' if it does not exist
CREATE DATABASE IF NOT EXISTS RPAPER;

-- Use the 'mydb' database
USE RPAPER;

-- Drop the 'users' table if it exists
DROP TABLE IF EXISTS users_plata;

-- Create the 'users' table
CREATE TABLE users_plata (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50),
  age INT,
  email VARCHAR(50)
);

-- Insert sample data into the 'users' table
INSERT INTO users_plata (name, age, email) VALUES ('John Doex', 25, 'john.doe@example.com');
INSERT INTO users_plata (name, age, email) VALUES ('Jane Smith', 30, 'jane.smith@example.com');
INSERT INTO users_plata (name, age, email) VALUES ('Bob Johnson', 35, 'bob.johnson@example.com');
