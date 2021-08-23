## Mechanic Shop Service Application w/ GUI (JDBC/PSQL Backend)

### Code Implementations
#### Assumptions
- There are no customers who does not own a car. 
- Each service request is exactly for one car. 
- A service request cannot exist without being linked to  a car. 
- Each service request is taken care by only one mechanic. 
- 
#### Methods
##### `AddCustomer`, `AddMechanic`, `AddCar`, `InsertServiceRequest`, `CloseServiceRequest`
```SQL
INSERT INTO tableName (feilds name) VALUES (fields’ values);
```

The ID auto increment is implememnted and you can find its explanations in the Extra Credit Implementation part. The other fields values are coming from the user input and adds to a string variable and then got added to the table.

#### Customer Queries
##### Customers retaining a Bill of <_ $100
```SQL
SELECT Customer.id, Customer.fname, Customer.lname, Customer.phone, Customer.address FROM Closed_request, Service_Request, Customer WHERE Closed_request.rid = Service_Request.rid AND Service_Request.customer_id = Customer.id AND Closed_request.bill < 100";
```

Bill amoounts can be found in the CLosed_Request table. Since we are looking for the costumer information we need to join Closed_Request, Service_Request and Costumer tables and check the bill where Closed_Request and Service_Request have the same rid and the customer id is same in Service_Request and costumer tables. So where the bill is less than 100 we return the columns containngn the customer inforamtion. 

##### Customers that own >_ 20 Cars
```SQL
SELECT Customer.id, Customer.fname, Customer.lname, Customer.phone, Customer.address FROM Customer, Owns WHERE Customer.id = Owns.customer_id GROUP BY Customer.id HAVING count(*)>20;
```

We need Customer and owns tables to retrieve information about ownership of the costumers. Since we need to count the cars of each customer, we used GROUP BY and HAVING. 

##### Cars Manufactured Before 1995 with over 50,000 miles traveled
```SQL
SELECT * FROM Car, Service_Request WHERE Car.vin = Service_Request.car_vin AND Car.year < 1995 AND Service_Request.odometer >= 50000";
```

The car’s year can be found in the Car table and Odometer in the Service_Request table. So we join the two tables.

##### Cars Listed by the highest count of services in descending order
```SQL
ListCarsWithTheMostServices:
SELECT  * FROM (SELECT Car.vin, Car.make, Car.model, Car.year, COUNT(*) AS service_count FROM Service_Request, Car WHERE Service_Request.car_vin = Car.vin GROUP BY Car.vin) AS temp_1 WHERE temp_1.service_count = (SELECT MAX(temp_2.service_count) FROM (SELECT Service_Request.car_vin, COUNT(*) AS service_count FROM Service_Request GROUP BY Service_Request.car_vin) AS temp_2)";
```

Here we used nested query. In the inner query we used GROUP BY to retrive cars that have had service request and count the number of servie requests for each car and we put the result in a new table named temp_1. Then to find the car with maximum number of service requests, we MAX function on the a table same as temp_1 names temp_2. 

##### Customers listed by the highest total sum of their bills in descending order
```SQL
SELECT * FROM (SELECT Customer.id, Customer.fname, Customer.lname, Customer.phone, Customer.address, SUM(Closed_Request.bill) AS sum_bill FROM Closed_request, Service_Request, Customer WHERE Closed_request.rid = Service_Request.rid AND Service_Request.customer_id = Customer.id GROUP BY Customer.id) AS temp ORDER BY temp.sum_bill Desc";
```

Since each costumer may have multiple bills, we created a table named temp that have the costumer inforamtion and the total bill for each costume by using SUM function and GRUOP BY. Then we used ORDER BY on total bill to list costumers in decsenting order of their total bill.

#### Extra Credit
##### Date Format Handling
To check the date format, we used a ready java class named `SimpleDateFormat`, which parses the given date and throws an exception in the case of a wrong format. After checking the format, we also checked the numbers to be in the correct intervals. For example, the month can be between 1-12. To do that, we used the substring method to split the date into the month, day, year, and so on and then checked each of them to be in the proper intervals. The format and interval checking is done inside a while loop, which stops when the date is correct. Otherwise, it would continuously show an error message and ask to enter the date correctly. 

##### Automatically Incrementing ID Value
Handling automatic ID increment: Done By Najmeh
ID auto increment is implemented for all the 5 methods to add customer, mechanic, car, service request, and closing a service request. In order to do this, we mades changes inside of both database and the java code. In the database, we added the following code in to the postgres code:
```SQL
ALTER SEQUENCE TableName_id_seq RESTART WITH 1 INCREMENT BY 1;
```
Since the tables are not empty, we first need to find the number of instances in the table and then increment the ID from where it is left off in the case of adding a new instance. To do so, the following code was added in the java file:
```SQL
int rowCount = esql.executeQuery("SELECT * FROM TableName;");
rowCount++;
esql.executeUpdate("ALTER SEQUENCE tableName_id_seq RESTART WITH "+ rowCount +" INCREMENT BY 1;");
```
The first line of code count the nimer of rows of a given table from the database. Then we increment it by one. And lastly, we used ALTER SEQUESNCE to let the database know how to increment the ID. 

---
### Contributors
- Kyle Garcia (@kgarc003)
- Najmeh Arani (@NaMoAr)
- Melody Asghari (@melasq)

#### Special Thanks
Our TA: Shihab
#### Designed + Built + Polished for UCR's (CS166) Introduction to Databases course
