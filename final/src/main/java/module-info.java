module iteratie1.main {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens service to javafx.fxml;

    exports service;
    opens controller to javafx.fxml;
    exports controller;

    opens domain to javafx.fxml;
    exports domain;

}