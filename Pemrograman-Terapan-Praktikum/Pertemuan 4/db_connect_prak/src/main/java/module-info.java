module com.example.db_connect_prak {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.db_connect_prak to javafx.fxml;
    exports com.example.db_connect_prak;

    // exports controller
    exports com.example.db_connect_prak.controller;
    opens com.example.db_connect_prak.controller to javafx.fxml;

    // exports util
    exports com.example.db_connect_prak.util;
    opens com.example.db_connect_prak.util to javafx.fxml;

    // exports entity
    exports com.example.db_connect_prak.entity;
    opens com.example.db_connect_prak.entity to javafx.fxml;

    // exports dao
    exports com.example.db_connect_prak.dao;
    opens com.example.db_connect_prak.dao to javafx.fxml;

}