-- TODO Task 3
drop database if exists cart;

create database cart;

use cart;

create table purchase_order(
	
    orderId varchar(64),
    date date,
	name varchar(64) not null,
	address varchar(256) not null,
    priority boolean default false,
    comments varchar(256),

    primary key(orderId)
);

create table line_item(
	
    id int auto_increment,
    orderId varchar(64),
    product_id varchar(64),
    name varchar(256),
    quantity int,
    price float,

    primary key(id),
    constraint fk_po_id foreign key(orderId) references purchase_order(orderId)
);


grant all privileges on cart.* to fred@'%';
flush privileges;