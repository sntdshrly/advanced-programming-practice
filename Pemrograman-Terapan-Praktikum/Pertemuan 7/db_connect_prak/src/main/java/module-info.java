module com.example.db_connect_prak {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql; // ini buat sql
    requires jasperreports; // ini buat jasperreports
    requires java.persistence; // persistance di model
    requires org.hibernate.orm.core; // di util/HibernateUtil
    requires java.naming; // konversi name dari class mapping ke tabel di db

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
    opens com.example.db_connect_prak.entity;

    // exports dao
    exports com.example.db_connect_prak.dao;
    opens com.example.db_connect_prak.dao to javafx.fxml;

}