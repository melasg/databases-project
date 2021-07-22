/* Table 'Car' */
CREATE TABLE "Car" (
vin integer(17) NOT NULL,
"year" timestamp without time zone(4) NOT NULL,
"Make" varchar(20) NOT NULL,
"Model" varchar(20) NOT NULL,
PRIMARY KEY(vin));

/* Table 'Customer' */
CREATE TABLE "Customer" (
cid integer NOT NULL,
address text(250) NOT NULL,
fname varchar(30) NOT NULL,
lname varchar(30) NOT NULL,
phone numrange(10) NOT NULL,
PRIMARY KEY(cid));
CREATE INDEX "" ON "Customer" (cid);


/* Table 'Close Request' */
CREATE TABLE "Close Request" (
close_id integer NOT NULL,
close_date timestamp(8) NOT NULL,
"comment" text(250) NOT NULL,
bill money(9) NOT NULL,
PRIMARY KEY(close_id));
CREATE INDEX "Close Request_ix_1" ON "Close Request" ();


/* Table 'Mechanic' */
CREATE TABLE "Mechanic" (
empid integer(20) NOT NULL,
lname varchar(30) NOT NULL,
fname varchar(30) NOT NULL,
exp_year numrange(3) NOT NULL,
PRIMARY KEY(empid));

/* Table 'service request' */
CREATE TABLE "service request" (
sid integer NOT NULL,
complain text(250) NOT NULL,
date date(8) NOT NULL,
odometer integer(12) NOT NULL,
PRIMARY KEY(sid));
CREATE INDEX "service request_ix_1" ON "service request" ();


/* Table 'brought' */
CREATE TABLE brought (
"Car_vin" integer(17) NOT NULL)
INHERITS(service request);
CREATE INDEX brought_ix_2 ON brought (Car_vin);


/* Table 'own' */
CREATE TABLE own (
"Car_vin" integer(17) NOT NULL);
CREATE INDEX own_ix_1 ON own (Car_vin);


/* Table 'closes' */
CREATE TABLE closes (
"Close Request_close_id" integer NOT NULL,
"Mechanic_empid" integer(20) NOT NULL);
CREATE INDEX closes_ix_1 ON closes (Close Request_close_id,Mechanic_empid);


/* Table 'creates' */
CREATE TABLE creates (
"Customer_cid" integer NOT NULL,
"Car_vin" integer(20) NOT NULL);
CREATE INDEX creates_ix_1 ON creates (Customer_cid,Car_vin);


/* Table 'service_close' */
CREATE TABLE service_close (
);
CREATE INDEX "" ON service_close ();


/* Relation 'own-Customer' */
ALTER TABLE "Customer" ADD CONSTRAINT "own-Customer"
FOREIGN KEY ()
REFERENCES own()
ON DELETE Default
ON UPDATE Cascade;

/* Relation 'Car-brought' */
ALTER TABLE brought ADD CONSTRAINT "Car-brought"
FOREIGN KEY ("Car_vin")
REFERENCES "Car"(vin);

/* Relation 'service_close-service request' */
ALTER TABLE "service request" ADD CONSTRAINT "service_close-service request"
FOREIGN KEY ()
REFERENCES service_close()
ON DELETE Default
ON UPDATE Default;

/* Relation 'service request-creates' */
ALTER TABLE creates ADD CONSTRAINT "service request-creates"
FOREIGN KEY ("Customer_cid")
REFERENCES "service request"(sid);

/* Relation 'Car-own' */
ALTER TABLE own ADD CONSTRAINT "Car-own"
FOREIGN KEY ("Car_vin")
REFERENCES "Car"(vin);

/* Relation 'brought-service request' */
ALTER TABLE "service request" ADD CONSTRAINT "brought-service request"
FOREIGN KEY ()
REFERENCES brought();

/* Relation 'service_close-Close Request' */
ALTER TABLE "Close Request" ADD CONSTRAINT "service_close-Close Request"
FOREIGN KEY ()
REFERENCES service_close()
ON DELETE Default
ON UPDATE Default;

/* Relation 'Close Request-closes' */
ALTER TABLE closes ADD CONSTRAINT "Close Request-closes"
FOREIGN KEY ("Close Request_close_id")
REFERENCES "Close Request"(close_id);

/* Relation 'Mechanic-closes' */
ALTER TABLE closes ADD CONSTRAINT "Mechanic-closes"
FOREIGN KEY ("Mechanic_empid")
REFERENCES "Mechanic"(empid);

/* Relation 'Mechanic-creates' */
ALTER TABLE creates ADD CONSTRAINT "Mechanic-creates"
FOREIGN KEY ("Car_vin")
REFERENCES "Mechanic"(empid);

