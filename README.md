# Ecommerce
Proyecto coder

### Base de datos

create database ecommerce;

use ecommerce;

CREATE TABLE clients (
id int NOT NULL auto_increment,
name varchar(75),
lastname varchar(75),
docnumber varchar(11),
PRIMARY KEY (id)
);

CREATE TABLE invoices (
id int NOT NULL auto_increment,
created_at datetime,
total double,
client_id int not null,
PRIMARY KEY (id),
FOREIGN KEY (client_id) REFERENCES clients(id)
);

CREATE TABLE products (
id int NOT NULL auto_increment,
description varchar(150),
code varchar(50),
stock int,
price double,
PRIMARY KEY (id)
);

CREATE TABLE carts (
id int NOT NULL auto_increment,
amount int,
price double,
delivered boolean,
product_id int not null,
clientid int not null,
PRIMARY KEY (id),
FOREIGN KEY (product_id) REFERENCES products(id),
FOREIGN KEY (clientid) REFERENCES clients(id)
);

### Para clonar:

git clone https://github.com/Lucia2912/Ecommerce.git

### Servicios


