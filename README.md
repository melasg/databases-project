![mvnjdk8](https://img.shields.io/badge/Java_8-42.2.16-blue.svg)
# databases-project
cs166 work

| [Required Drivers](/requirements.txt) | Versions | minimum |
| --- | --- | --- |
| JDK | 8 | 6 |
| JDBC | 4.2 | 4.0 |
| [PostgreSQL JDBC 4.2 Driver](https://jdbc.postgresql.org/download.html) | 42.2.23 | 42.1.4 |
| PostgreSQL | 11.2 | 11 |

## Code Directory Outline
```tree
.
├── pom.xml
└── src
    ├── data
    │   ├── car.csv
    │   ├── closed_request.csv
    │   ├── customer.csv
    │   ├── mechanic.csv
    │   ├── owns.csv
    │   └── service_request.csv
    ├── lib
    │   └── postgresql-42.1.4.jar
    ├── main
    │   ├── java
    │   │   ├── CS166
    │   │   │   └── Team32
    │   │   │       └── MechanicShopGUI
    │   │   │           ├── AddCarController.java
    │   │   │           ├── AddCustomerController.java
    │   │   │           ├── AddMechanicController.java
    │   │   │           ├── App.java
    │   │   │           ├── CarsData.java
    │   │   │           ├── ClientPortalController.java
    │   │   │           ├── CloseServiceRequestController.java
    │   │   │           ├── ConnectionController.java
    │   │   │           ├── CustomerData.java
    │   │   │           ├── CustomerQuery1Data.java
    │   │   │           ├── CustomerQuery2Data.java
    │   │   │           ├── CustomerQuery3Data.java
    │   │   │           ├── CustomerQuery4Data.java
    │   │   │           ├── CustomerQuery5Data.java
    │   │   │           ├── InsertServiceRequestController.java
    │   │   │           ├── ListCustomerOp1Controller.java
    │   │   │           ├── ListCustomerOp2Controller.java
    │   │   │           ├── ListCustomerOp3Controller.java
    │   │   │           ├── ListCustomerOp4Controller.java
    │   │   │           ├── ListCustomerOp5Controller.java
    │   │   │           └── ResScreen.java
    │   │   └── module-info.java
    │   └── resources
    │       └── CS166
    │           └── Team32
    │               └── MechanicShopGUI
    │                   ├── AddCar.fxml
    │                   ├── AddCustomer.fxml
    │                   ├── AddMechanic.fxml
    │                   ├── App.fxml
    │                   ├── ClientPortal.fxml
    │                   ├── CloseServiceRequest.fxml
    │                   ├── InsertServiceRequest.fxml
    │                   ├── ListCustomerOp1.fxml
    │                   ├── ListCustomerOp2.fxml
    │                   ├── ListCustomerOp3.fxml
    │                   ├── ListCustomerOp4.fxml
    │                   ├── ListCustomerOp5.fxml
    │                   └── ResScreen.fxml
    └── sql
        └── create.sql
```
