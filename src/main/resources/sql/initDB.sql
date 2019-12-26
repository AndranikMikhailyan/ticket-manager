drop database if exists temp;
create database temp;

use temp;

drop table if exists tickets;
drop table if exists routes;
drop table if exists passengers;

create table routes (
	id int auto_increment,
  departure_city varchar(100) not null,
  arrival_city varchar(100) not null,
  primary key(id)
);

create table passengers (
  id int auto_increment,
  last_name varchar(100) not null,
  first_name varchar(100) not null,
  patronymic varchar(100) not null,
  birth_day date not null,
  primary key (id)
);

create table tickets (
	id int auto_increment,
  route_id int not null,
  departure_date date not null,
	ticket_class varchar(50) not null,
  seat_number int not null,
  ticket_status varchar(50) not null,
  price real not null,
  passenger_id int,
  primary key (id),
  foreign key (route_id) references routes(id) on delete restrict,
  foreign key (passenger_id) references passengers(id)
);

