module com.jonasgestopa.ems {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.jonasgestopa.ems to javafx.fxml;
    opens com.jonasgestopa.ems.Models to javafx.base;

    exports com.jonasgestopa.ems;
    exports com.jonasgestopa.ems.Controllers;
    opens com.jonasgestopa.ems.Controllers to javafx.fxml;
}