package com.example.perbaikan_stage_and_modality_prak.controller;

import com.example.perbaikan_stage_and_modality_prak.Main;
import com.example.perbaikan_stage_and_modality_prak.model.Barang;
import com.example.perbaikan_stage_and_modality_prak.model.Supplier;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BarangController implements Initializable {
    @FXML
    private TextField txtId;
    @FXML
    private TableView<Barang> tableBarang;
    @FXML
    private TextField txtName;
    @FXML
    private TableColumn<Barang,String> columnId;
    @FXML
    private TableColumn<Barang,String> columnName;
    @FXML
    private TableColumn<Barang,String> columnSupplier;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnReset;
    @FXML
    private Button btnUpdate;
    @FXML
    private ComboBox<Supplier> comboSupplier;
    private ObservableList<Supplier> supplierList;
    private ObservableList<Barang> barangList;
    public ObservableList<Supplier> getSupplierList() {return supplierList;}

    public void onActionShowSupplier(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("supplier-view.fxml"));
        Parent root = loader.load();
        SupplierController suppController = loader.getController();
        suppController.setBarangController(this);
        Stage suppStage = new Stage();
        suppStage.setTitle("Data Supplier");
        suppStage.setScene(new Scene(root));
        suppStage.show();
    }
    public void getSelectedItem(MouseEvent mouseEvent) {
    }
    public void onActionSave(ActionEvent actionEvent) {
    }
    public void onActionReset(ActionEvent actionEvent) {
    }
    public void onActionUpdate(ActionEvent actionEvent) {
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        supplierList = FXCollections.observableArrayList(
                new Supplier("dummy data id supplier","dummy data nama supplier","dummy data alamat supplier")
        );
        barangList = FXCollections.observableArrayList();
        tableBarang.setItems(barangList);
        comboSupplier.setItems(supplierList);
        columnId.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getId()));
        columnName.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getId()));
        columnSupplier.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getSupplier().getNamaSupplier()));
    }
}
