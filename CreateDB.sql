create schema "sales";
create table "sales"."clients"
(
	"id" integer,
	"FIO" varchar(255),
	"office" varchar(255),
	primary key ("id")
);
create table "sales"."managers"
(
	"id" integer,
	"FIO" varchar(255),
	"office" varchar(255),
	primary key ("id")
);
create table "sales"."products"
(
	"id" integer,
	"supplyPrice" integer,
	"vandorCode" varchar(255),
	"office" varchar(255),
	primary key ("id")
);
create table "sales"."orders"
(
	"id" integer,
	"managerId" integer REFERENCES "sales"."managers"("id"),
	"date" varchar(26),
	"office" varchar(255),
	primary key ("id")
);
create table "sales"."orderItems"
(
	"orderId" integer REFERENCES "sales"."orders"("id"),
	"productId" integer REFERENCES "sales"."products"("id"),
	"count" integer,
	"price" integer
);