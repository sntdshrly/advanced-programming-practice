module com.example.fx_coll_student_prak {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fx_coll_student_prak to javafx.fxml;
    exports com.example.fx_coll_student_prak;
}