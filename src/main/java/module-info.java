module com.aptech.mavenproject2 {
     requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.base;
    requires java.desktop;
    requires pdfbox;

    opens com.aptech.mavenproject2 to javafx.fxml;
    opens Models to javafx.fxml;
    exports com.aptech.mavenproject2;
    exports Models;
}
