module com.example.perbaikan_stage_and_modality_prak {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.perbaikan_stage_and_modality_prak to javafx.fxml;
    exports com.example.perbaikan_stage_and_modality_prak;
    // diexport controller
    exports com.example.perbaikan_stage_and_modality_prak.controller;
    opens com.example.perbaikan_stage_and_modality_prak.controller to javafx.fxml;
    // diexport modelnya
    opens com.example.perbaikan_stage_and_modality_prak.model to javafx.fxml;
    exports com.example.perbaikan_stage_and_modality_prak.model;
}