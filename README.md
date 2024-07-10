# Ecommerce
Proyecto coder

### Base de datos

create database comercio;

use comercio;

CREATE TABLE clients (
id int NOT NULL,
name varchar(75),
lastname varchar(75),
docnumber varchar(11),
PRIMARY KEY (id)
);

CREATE TABLE invoice (
id int NOT NULL,
created_at datetime,
total double,
client_id int not null,
PRIMARY KEY (id),
FOREIGN KEY (client_id) REFERENCES clients(id)
);

CREATE TABLE products (
id int NOT NULL,
description varchar(150),
code varchar(50),
stock int,
price double,
PRIMARY KEY (id)
);

CREATE TABLE invoice_detail (
id int NOT NULL,
amount int,
price double,
product_id int not null,
invoice_id int not null,
PRIMARY KEY (id),
FOREIGN KEY (product_id) REFERENCES products(id),
FOREIGN KEY (invoice_id) REFERENCES invoice(id)
);

### Para clonar:

git clone https://github.com/Lucia2912/Ecommerce.git

### Servicios


