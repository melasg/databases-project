module com.mycompany.mavenproject6 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    opens com.mycompany.mavenproject6 to javafx.fxml;
    exports com.mycompany.mavenproject6;
}
