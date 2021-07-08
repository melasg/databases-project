## Database Design:
[![](https://mermaid.ink/img/eyJjb2RlIjoiZ3JhcGggVERcbiAgICBBW1JlcXVpcmVtZW50IEFuYWx5c2lzXS0tPkJbQ29uY2VwdHVhbCBEZXNpZ24gRVJdXG4gICAgLS0-Q1tMb2dpY2FsIERCIERlc2lnbl0tLT5EW1NjaGVtYSBEZXNpZ25dLS0-RVtQaHlzaWNhbCBERF0iLCJtZXJtYWlkIjp7InRoZW1lIjoiZGVmYXVsdCJ9LCJ1cGRhdGVFZGl0b3IiOmZhbHNlLCJhdXRvU3luYyI6dHJ1ZSwidXBkYXRlRGlhZ3JhbSI6ZmFsc2V9)](https://mermaid-js.github.io/mermaid-live-editor/edit##eyJjb2RlIjoiZ3JhcGggVERcbiAgICBBW1JlcXVpcmVtZW50IEFuYWx5c2lzXS0tPkJbQ29uY2VwdHVhbCBEZXNpZ24gRVJdXG4gICAgLS0-Q1tMb2dpY2FsIERCIERlc2lnblxcbl0tLT5EW1NjaGVtYSBEZXNpZ25dLS0-RVtQaHlzaWNhbCBERF0iLCJtZXJtYWlkIjoie1xuICBcInRoZW1lXCI6IFwiZGVmYXVsdFwiXG59IiwidXBkYXRlRWRpdG9yIjpmYWxzZSwiYXV0b1N5bmMiOnRydWUsInVwZGF0ZURpYWdyYW0iOmZhbHNlfQ)
* Requirement Analysis:
    – What data should be stored in Database?
    – What applications can be build on top of it?
    – Which operations are subject to performance requirements?
* Conceptual Database Design
    – High level description of Analysis + constrains over these data are modeled with the ER model (_semantic model_ used in database design)
    – The design must be precise in order to allow straightforward translation into the relational model (tables, attributes,..), which is used by the Database
    – **Goal: Generate description of data which is understandable both by developers and users**
* Logical Database Design (_ER schema → relational database schema_)
    – Generally this step involves the conversion of the conceptual schema to Database-specific schema.
* Schema Refinement
    – Analyze Relational Database Schema and identify problems
    – Normalizing relations
    – **Ex:** Student(ssn, name, numgrade, lettergrade) → _functionally-dependent attributes_
* Physical Database Design
    – Make sure that the database meets the performance needs / workloads that are expected by the Analysis. (Indexes, Denormalize Relations)
* Application & Security Design
## Entity (Relation)
* Entity (Relation)
    – An entity is a real world object that can be distinguished from another object given some attributes
    > e.g. Employee == Manager
    > Employee ≠ Projects
* Attribute
    – Several attribute characterize an entity. If an attribute is multi-value (address zip,address, aptno) create an entity
    – Domain → Possible values
    – Key → Set of attributes that uniquely identifies an entity (primary, secondary, can- didate)
* Entity-Set
    – Toy, Appliance Department Employees under same set
* Relationship
    – Relates 2 or more entities
    – Descriptive Attributes
    – Ternary Relationship → Involves 3 relations
* Relationship Set
    – A set of similar relationships
## Entity-Relationship (ER) Diagram
* The ER model allows us to describe the data involved in a real world enterprise in terms of objects (entities) and their relationships
* Provides the initial framework for developing an initial DB design
* There are other variations of the ER model, mainly different on the way entities and
their relationships are graphically represented
* **You should follow book notation for ER Data Structure Diagram**
## ER Notation Explanation
|                                                       Entity |               ![Entity](https://d2slcw3kip6qmk.cloudfront.net/marketing/pages/chart/seo/ERD/discovery/erd-chens-01.svg)              |
|-------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------------------------:|
|                                                Weak Entities |            ![WeakEntities](https://d2slcw3kip6qmk.cloudfront.net/marketing/pages/chart/seo/ERD/discovery/erd-chens-02.svg)           |
|                                                    Attribute |            ![Attribute](https://d2slcw3kip6qmk.cloudfront.net/marketing/pages/chart/seo/ERD/discovery/erd-symbols-04.svg)            |
|                                                Key Attribute |            ![AttributeKey](https://d2slcw3kip6qmk.cloudfront.net/marketing/pages/chart/seo/ERD/discovery/erd-chens-05.svg)           |
|                          Relationships:<br>1:1<br>1:M<br>M:1 | ![Relationship 1toMany,Manyto1,1to1](https://d2slcw3kip6qmk.cloudfront.net/marketing/pages/chart/seo/ERD/discovery/erd-chens-09.svg) |
|                                   Entity-Relationship<br>M:N |    ![Entity-Relationship,ManytoN](https://d2slcw3kip6qmk.cloudfront.net/marketing/pages/chart/seo/ERD/discovery/erd-chens-03.svg)    |
|                                       Relationship Connector |       ![RelationshipConnector](https://d2slcw3kip6qmk.cloudfront.net/marketing/pages/chart/seo/ERD/discovery/erd-chens-12.svg)       |
|                       Relationship Connector<br>At-Least-One |             ![AtLeastOne](https://d2slcw3kip6qmk.cloudfront.net/marketing/pages/chart/seo/ERD/discovery/erd-chens-11.svg)            |
|                      Relationship Connector <br> At-Most-One |            ![AtMostOne](https://d2slcw3kip6qmk.cloudfront.net/marketing/pages/chart/seo/ERD/discovery/erd-bachman-02.svg)            |
| Relationship Connector <br> At-Least-One **AND** At-Most-One |     ![AtLeastOne AND AtMostOne](https://d2slcw3kip6qmk.cloudfront.net/marketing/pages/chart/seo/ERD/discovery/erd-bachman-03.svg)    |

<!--
 ![Entity](https://d2slcw3kip6qmk.cloudfront.net/marketing/pages/chart/seo/ERD/discovery/erd-chens-01.svg)
![WeakEntities](https://d2slcw3kip6qmk.cloudfront.net/marketing/pages/chart/seo/ERD/discovery/erd-chens-02.svg)
![Attribute](https://d2slcw3kip6qmk.cloudfront.net/marketing/pages/chart/seo/ERD/discovery/erd-symbols-04.svg)
![AttributeKey](https://d2slcw3kip6qmk.cloudfront.net/marketing/pages/chart/seo/ERD/discovery/erd-chens-05.svg)
![Relationship 1toMany,Manyto1,1to1](https://d2slcw3kip6qmk.cloudfront.net/marketing/pages/chart/seo/ERD/discovery/erd-chens-09.svg)
![Entity-Relationship,ManytoN](https://d2slcw3kip6qmk.cloudfront.net/marketing/pages/chart/seo/ERD/discovery/erd-chens-03.svg)
![RelationshipConnector](https://d2slcw3kip6qmk.cloudfront.net/marketing/pages/chart/seo/ERD/discovery/erd-chens-12.svg)
![AtLeastOne](https://d2slcw3kip6qmk.cloudfront.net/marketing/pages/chart/seo/ERD/discovery/erd-chens-11.svg)
![AtMostOne](https://d2slcw3kip6qmk.cloudfront.net/marketing/pages/chart/seo/ERD/discovery/erd-bachman-02.svg)
![AtLeastOne AND AtMostOne](https://d2slcw3kip6qmk.cloudfront.net/marketing/pages/chart/seo/ERD/discovery/erd-bachman-03.svg) 
-->
<!-- ![]() -->
## Additional Feature of ER Model
* Key Constraints (_At least vs. At most_)
    – **1:1 :**
        - Each professor works in _at most 1_ department
        - _At most 1_ professor work works in each department.
    – **1:N :**
        - Each professor works in _at most N_ departments.
        - In each department _at most 1_ professor work.
    – **N:M :**
        - Each professor works in _at most N_ departments.
        - In each department _at most M_ professor work.
* Participation Constraints (_Partial vs. Total_)
    - Employee works in _at least 1_, _at most N_ Departments (_TOTAL_ participation in relationship)
    – Employee works in _at least 0_, _at most N_ Departments (_PARTIAL_ participation in relationship)
* Weak Entities
    - A weak entity can be identified uniquely _ONLY_ by considering the primary of another Relation
* Class Hierarchies

* Aggregation