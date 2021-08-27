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
Generally the methods require a simple line:
{% highlight sql %}
 INSERT INTO Closed_Request (field name) VALUES (field values);
{% endhighlight %}
The ID auto incrementing is implemented by the methods explained under the #Extra-Credit part. Other values' fields depends on user input then pushed into a `string` variable before being pushed into the table.

###### `AddCustomer`
To check if the customer previously exists in the database, the following code returns the number of rows queried when printing the table.
**New Customer Created**:
The query returns `0` if user is not previously entered, thus is able to add a new customer. 
{% highlight sql %}
esql.executeQueryAndPrintResult(SELECT Customer.id, Customer.fname, Customer.lname FROM Customer WHERE Customer.lname ='"+ lastname+ "';");
{% endhighlight %}
**Previously Existing Customer Entered**:
If the query returns anything other than `0`, then that means the user will be prompted to enter the previously existing customer ID.
{% highlight sql %}
esql.executeQueryAndPrint
Result("SELECT * FROM Owns WHERE customer_id ="+ customerID + ";");
{% endhighlight %}

###### `AddMechanic`
Similar to the `AddCustomer` method, the `AddMechanic` returns a list of row(s) of the matching information entered for an existing mechanic.
{% highlight sql %}
esql.executeQuery("SELECT * FROM Mechanic WHERE Mechanic.id ="+ MehcanicID);
{% endhighlight %}
If not, the non-zero value is returned and is created/
{% highlight sql %}
 INSERT INTO Closed_Request (field name) VALUES (field values);
{% endhighlight %}

###### `CloseServiceRequest`
When asking the user to add a car via the `AddCar` method, the user is prompted to enter the car's vehicle identification number. This gives us the data to check whether the car has already been entered for service before or whether it is a new car in the database. Similar to the `AddCustomer` method's check, this returns a `0` or non-zero value to indicate whether the car with the specified VIN already exists within the database.
**Existing Service Request Entry Closed**:
{% highlight sql %}
esql.executeQuery("SELECT * FROM Service_Request WHERE Service_Request.rid ="+ serviceRequestID);
{% endhighlight %}

**Non-existent Service Request Error**:
{% highlight sql %}
esql.executeQuery("SELECT * FROM Mechanic WHERE Mechanic.id ="+ MehcanicID);
{% endhighlight %}

#### Customer Queries
##### Customers retaining a Bill less than $100
The total bill closing amount is contained within the `Closed_Request` table. The customer information needed from the `Closed_Request`, `Service_Request`, and `Customer` tables will be joined and queried to check that a uniform Customer ID is shared within each table. Where the bill is shown to be less tha $100, then the columns containing the customer information of who fits this criteria is returned with the rows of all customers specified.
{% highlight sql %}
SELECT Customer.id, Customer.fname, Customer.lname, Customer.phone, Customer.address FROM Closed_request, Service_Request, Customer WHERE Closed_request.rid = Service_Request.rid AND Service_Request.customer_id = Customer.id AND Closed_request.bill < 100";
{% endhighlight %}

##### Customers that own More Than 20 Unique Cars
In this query, we require the `Customer` and `Own` table to extract the required information to determine ownership of each customer and join by using `GROUP BY` and `HAVING`. This leads to our following implementation:
{% highlight sql %}
SELECT Customer.id, Customer.fname, Customer.lname, Customer.phone, Customer.address FROM Customer, Owns WHERE Customer.id = Owns.customer_id GROUP BY Customer.id HAVING count(*)>20;
{% endhighlight %}

##### Cars Manufactured Before 1995 with over 50,000 miles traveled
The car's year of manufacture can be found within the `Car` table, thus the information extracted is joined with the other specified `Odometer` attribute from the `Serice_request` table.
{% highlight sql %}
SELECT * FROM Car, Service_Request WHERE Car.vin = Service_Request.car_vin AND Car.year < 1995 AND Service_Request.odometer >= 50000";
{% endhighlight %}

##### Cars Listed by the highest count of services in descending order
In order to sort the data by a query searching for specified attributes, the query will be nested query, an inner query will use the `GROUP BY` to retrieve the cars that have had service requests which are then measured by the returned attribute of the `COUNT` value for service requests. The outer query will require an `ORDER BY` of the previous table which is put into a temporary table `temp_1.service_count Desc` therefore the services will be ordered by descending order based on numeric value of service requests. The user will then be prompted to display the `k` number of cars with the most service requests, thus returning the specified `k` rows.
{% highlight sql %}
SELECT * FROM (SELECT Car.vin, Car.make, Car.model, Car.year, COUNT(*) AS service_count FROM Service_Request, Car WHERE Service_Request.car_vin = Car.vin GROUP BY Car.vin) AS temp_1 ORDER BY temp_1.service_count Desc fetch first " + k + " rows only;";
{% endhighlight %}

##### Customers listed by the highest total sum of their bills in descending order
The table that holds the customer information will have multiple service requests and vary bills that might not be added in sum, therefore, to sum them, there will be a temporary table created `temp` that will include the total bill of the customer information by using the `SUM` and `GROUP BY` functions. To sort them in descending order of total bill, the `ORDER BY` method will be used.
{% highlight sql %}
SELECT * FROM (SELECT Customer.id, Customer.fname, Customer.lname, 
Customer.phone, Customer.address, SUM(Closed_Request.bill) AS sum_bill FROM Closed_request, Service_Request, Customer WHERE Closed_request.rid = Service_Request.rid AND Service_Request.customer_id = Customer.id GROUP BY Customer.id) AS temp ORDER BY temp.sum_bill Desc";
{% endhighlight %}

#### Extra Credit
##### Date Format Handling
Checking the format of the date requires the class implemented within the application, `SimpleDateFormat`, which parses the user-given date then throws an exception if the format does not match the specified format.
**EXample**:
Months will be checked by a 1-2 digit of numeric value from 1 to 12 at the most. 
The `substring` method of splitting the date and extracting `month`, `day`, `year` and so on will be checked within the specific intervals. Interval and format checking is completed within a `while` loop until the date is shown to match the correct format. Otherwise, it would continue to prompt the user with an error message until the date is changed.

##### Automatically Incrementing ID Value
ID Auto-increminging is used within all 5 methods of the main `.java` file:
1. `AddCustomer`
2. `AddMechanic`
3. `AddCar`
4. `InsertServiceRequest`
5. `CloseServiceRequest`

These checks require a modification of the given `PostgreSQL` database code:
{% highlight sql %}
ALTER SEQUENCE TableName_id_seq RESTART WITH 1 INCREMENT BY 1;
{% endhighlight %}

Since the tables are not empty, we first need to find the number of instances in the table and then increment the ID from where it is left off in the case of adding a new instance. To do so, the following code was added in the java file:
{% highlight sql %}
int rowCount = esql.executeQuery("SELECT * FROM TableName;");
rowCount++;
esql.executeUpdate("ALTER SEQUENCE tableName_id_seq RESTART WITH "+ rowCount +" INCREMENT BY 1;");
{% endhighlight %}
The first line of code counts the number of rows of a given table from the database. Then we increment it by one. And lastly, we used `ALTER SEQUENCE` to let the database know how to increment the ID.

---

### Contributors
- Kyle Garcia (@kgarc003) - GUI
- Najmeh Arani (@NaMoAr) - GUI
- Melody Asghari (@melasq) - main java 

#### Special Thanks
Our TA: Shihab
#### Designed + Built + Polished for UCR's (CS166) Introduction to Databases course
