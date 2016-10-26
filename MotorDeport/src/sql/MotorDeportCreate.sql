CREATE DATABASE MotorDeport;
USE MotorDeport;
CREATE TABLE Aplications (
aplication_id INT(10) NOT NULL PRIMARY KEY,
voyage_id INT(10) NULL,
number_of_passengers INT (5) NULL,
cargo_weight INT(5) NULL);
CREATE TABLE Voyages (
voyage_id INT (10) NOT NULL PRIMARY KEY,
start_date VARCHAR (10) NULL,
duration INT (7) NOT NULL,
statement INT (1) NULL,
employee_id INT (10) NULL);
CREATE TABLE Empployees (
employee_id INT (10) NOT NULL PRIMARY KEY,
first_name VARCHAR (20) NULL,
last_name VARCHAR (20) NULL,
telephone INT (7) NULL,
id_post INT(1) NULL,
auto_id INT (10) NULL,
login VARCHAR (10) NULL,
pass VARCHAR (15) NULL);
CREATE TABLE CarPark(
auto_id INT(10) NOT NULL PRIMARY KEY,
label VARCHAR (25) NULL,
passangers INT (3) NULL,
cargo INT (5) NULL,
state_id INT (2) NULL);
CREATE TABLE Statements(
state_id INT(2) NOT NULL PRIMARY KEY,
state VARCHAR (10) NULL);
CREATE TABLE post(
id_post INT(1) NOT NULL PRIMARY KEY,
post VARCHAR(10) DEFAULT '');