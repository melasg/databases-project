# Relational Model

## Outline
1. Logical Database Design
  * ER Model → Relational Model
2. Views
3. PostgreSQL example

### Logical Database Design
#### From ER Schema → Relational Database schema
* ER schema is conveneient for initial high-level description of the database design.
* There is a standardized procedure to translate the ER Diagram to Relational Schema.
#### Relational Schema
Relational Schema can be thought of as the basic information describing a table/relation. This includes a set of column names, data types associated with each column, and name associated with the entire table.
Formally: ![equation1](documentation/img/eq1.png)
Example: A relation schema for the relation called `Students` could be expressed using the following representation.
> Students(sid: _string_, name: _string_, login: _string_, age: integer, gpa: _real_)

There are five fields or columns, with names and types as shown above.
* Domain is synonymous with data type.
* Attributes can be thought of as columns in a table.
* Therefore, an attribute domain refers to the data type associated with a column.
#### Relation Instances
* A relation instance is a set of tuples (rows or records) that each conform to the schema of the relation.
    * **Cardinality:** number of tuples in the relation.
    * **Degree:** number of fields (or columns) in the relation.
* Example:
    * Relational schema:
    > Product(Name, Price, Category, Manufacturer)
    * Instance:
| **Name** | **Price** | **Category** | **Manufacturer** |
| Gizmo | 19.99 | gadgets | Gizmo Works |
| Power Gizmo | 29.99 | gadgets | Gizmo Works |
| Single Touch | 149.99 | photography | Canon |
| Multi Touch | 203.99 | household | Hitachi |
* Entity Example:
> Employee(ssn, name, age)
* Employee:
| ssn | name | age |
### Data Definition Language
We use the Data Definition Language of a DBMS to describe the generated Tables:
#### Creating a Relation:
```SQL
CREATE TABLE Employee (ssn CHAR(11) NOT NULL,
                        name CHAR(30) NOT NULL,
                        age INTEGER,
                        Primary KEY(ssn));
```
#### Altering a relation to add a constraint
```SQL
ALTER TABLE Employee ADD Column surname CHAR(40) NOT NULL;
```
#### Adding to a relation:
```SQL
INSERT INTO Employee(ssn, name, surname, age)
VALUES("34342", "Smith", "Heiss", 18);
```
#### Update tuple
```SQL
UPDATE Employee SET name="John" WHERE name="Smith"
```
#### Delete tuples
```SQL
DELETE Employee WHERE name="John";
```
#### Drop Table
```SQL
DROP TABLE Employees;
```
### PostgreSQL Example (Relationships)
* Constraints found in a relationship
  * **Key Constraint:** determined by the "_At-Most_" question
  * **Participation Constraint:** determined by the "_At-Least_" question
#### WITHOUT PARTICIPATION Constraints
  * Relationship becomes a relation itself:
  * `Works(ssn,did)`
  * Also called "_Entity-Relationship_"
```PostgreSQL
CREATE TABLE Employee (ssn CHAR(11) NOT NULL,
                            PRIMARY KEY(ssn));

CREATE TABLE Department (did INTEGER NOT NULL,
                            PRIMARY KEY(did));

CREATE TABLE Works (ssn CHAR(11) NOT NULL,
                        did INTEGER NOT NULL,
                        PRIMARY KEY(ssn, did),
    FOREIGN KEY(ssn) REFERENCES Employee(ssn),
    FOREIGN KEY(did) REFERENCES Department(did));
```
**Review 1→M Relationships:**
* Suppose that an `Employee` manages `N` `Departments` but ANY `Department` is managed only by at-most 1 person.
> `Employee(ssn)`, `Department(did, ssn)` where `did` → `primary key, ssn` → `foreign key`
```Postgre
CREATE TABLE Employee (ssn CHAR(11) NOT NULL,
                            PRIMARY KEY(ssn));
CREATE TABLE Department (did INTEGER NOT NULL,
                                ssn CHAR(11)
                            PRIMARY KEY(did),
    FOREIGN KEY(ssn) REFERENCES Employee(ssn));
```
**Review 1→1 Relationships:**
* Suppose an employee works on at-most 1 single-person project.
* We would have to move the key to either direction (avoiding `NULL`s)
* Example:
> `Employee(ssn, pid)` `Project(pid)`
* May create too many `NULL`s because each employee is not said to be working on At-Least ` project.
* The participation constraint that will be described in a while will make it clear to which direction we should move the key:
> `Employee(ssn)` `Project(pid, ssn)` on the other hand has no `NULL`s since every project belongs to some `Employee`.
##### WITH PARTICPATION Constraints
* Participation Constraint (the "_At-Least_" question)
* This will give you the complete picture since any ER should capture key and participation constraints.
    * First, of all the participation constraints makes it clear in which direction to move the key in the case of a `1:1` relationship.
    * Suppose an employee works on `0:1` projects and each project is worked on by `1:1` employees.
    * Now we automatically know that the key moves to the direction of the bold line. (to avoid `NULL`s)
    * What would happen if the participation constraint was from both sides?
        * The key goes to either direction.
##### Enforcing referential integrity
* Suppose you are given the following example. (`Department` gets the `ssn`)
```Postgre
ON [DELETE | UPDATE] "of the referred tuple the DBMS should" {CASCADE, SET NULL, RESTRICT=NO ACTION=default, SET DEFAULT} "with the current relation"
```
* `Restrict=No` `Action=default`
```Postgre
CREATE TABLE Department (did INTEGER,
                ssn CHAR(11) NOT NULL,
                      PRIMARY KEY(did),
FOREIGN KEY(ssn) REFERENCES Employee(ssn),
                    ON DELETE NO ACTION);
```
What this piece of code means:
> `ON DELETE` of the referred tuple in relation `EMPLOYEE` the DBMS should prohibit the delete of the given employee.
**Employee table**
| ssn |
| 1 |
| 2 |
| 3 |
**Department table**
| did | ssn|
| 1 | 1 |
| 2 | 2 |
| 3 | 3 |

1) What would happen if we want to delete employee with `ssn=1`:
```Postgre
Delete FROM Employee where ssn="1";
```
2) We would obtain the error:
> System gives "`Illegal Action - Violates Referential Integrity Constraint`"
3) Then we would have to change the manager `did=1` → `did=2` then try again:
```Postgre
UPDATE Department D SET D.ssn=2 WHERE D.ssn=1;
```
## Other Important PostgreSQL interfaces
### `CASCADE`
```Postgre
CREATE TABLE Department (did INTEGER,
                    ssn CHAR(11) NOT NULL,
                        PRIMARY KEY(ssnv),
FOREIGN KEY(ssn) REFERENCES Employee(ssn),
                        ON DELETE CASCADE);
```
What this means:
* `ON DELETE` of the refereed tuple in relation to the `Employee` the DBMS should `CASCADE` the `DELETE`.
* That means that the `Department` record should be deleted as well.
### `SET NULL`
```Postgre
CREATE TABLE Department (did INTEGER,
                    ssn CHAR(11) NOT NULL,
                        PRIMARY KEY(ssnv),
FOREIGN KEY(ssn) REFERENCES Employee(ssn),
                        ON DELETE SET NULL;
```
What this means:
* **ILLEGAL code:** `ssn` cannot be `NULL`.
* violates Participation Constraint
### `SET DEFAULT`
```Postgre
CREATE TABLE Department (did INTEGER,
                    ssn CHAR(11) NOT NULL,
                        PRIMARY KEY(ssnv),
FOREIGN KEY(ssn) REFERENCES Employee(ssn),
                        ON DELETE SET DEFAULT);
```
What this means:
* `ON DELETE` of the refereed tuple in relation `Employee` the DBMS should set `ssn` in `Department` to the default value (`0`).
#### Weak Entities
```Postgre
CREATE TABLE Dependent (dname CHAR(11) NOT NULL,
                        ssn CHAR(11) NOT NULL,
                        age INTEGER,
                        PRIMARY KEY(ssn, dname),
        FOREIGN KEY(ssn) REFERENCES Employee(ssn),
                            ON DELETE CASCADE); //deletes Dependent
```
#### ISA Hierarchies ("is a")
* Inherits the key and creates 3 Relations
* In order to find name of `Employee` we need to combine `EMPLOYEE`, `HOURLY_EMPS`
* refer to ISA diagram in Book (Chapter 2, page 40).
