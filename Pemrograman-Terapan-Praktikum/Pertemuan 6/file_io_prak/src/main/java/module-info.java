module com.example.file_io_prak {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.file_io_prak to javafx.fxml;
    exports com.example.file_io_prak;
    exports com.example.file_io_prak.controller;
    opens com.example.file_io_prak.controller to javafx.fxml;
}