# Implementation
* Open Service Request
* Close Service Request
* List
    1. date
    2. comment
    3. bill
    ...for all closed requests with bill lower than `100`
* List
    1. first
    2. last name
    ...of customers having more than 20 different cars
* List 
    1. Make 
    2. Model
    3. Year
    ...of all cars build before 1995 having less than 50,000 miles
* List the 
    1. make
    2. model
    3. and number
    ...of service requests for the first k cars with the highest number of service orders.
* List the 
    1. first name
    2. last name
    3. and total bill
    ...of customers in descending order of their total bill for all cars brought to the mechanic.
### Customer
* The customer has:
    * first name
    * last name
    * phone number
    * address
* Customers can own many different cars.
* Customers may bring any of their car(s) for service.
* Customers bring their car for service at a certain date indicating to the mechanic if there is any problem with the car.
### Cars
* Cars have a unique VIN, a year, a make and a model
* A car has exactly one owner.
* A car may have many outstanding service requests.
* For each outstanding service request, the car has an odometer reading and the date
the car was brought in by the customer.
* The service request will be closed when the car is fixed a mechanic
### Mechanic
* Mechanics have:
    * first name
    * a last name
    * an employee id
    * years of experience
* Mechanics work on a _single car at a time_.
* **When a mechanic fixes a car**, they close the service request **indicating**:
    * when it was closed
    * any comments they have
    * the final bill.
* Exactly **one** mechanic can create a service request.
* **Both** open and closed service requests need to be available at any time.