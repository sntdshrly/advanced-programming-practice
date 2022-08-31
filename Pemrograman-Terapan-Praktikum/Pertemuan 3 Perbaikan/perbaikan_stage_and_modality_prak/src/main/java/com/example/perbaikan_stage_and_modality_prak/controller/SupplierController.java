package com.example.perbaikan_stage_and_modality_prak.controller;

import com.example.perbaikan_stage_and_modality_prak.model.Supplier;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class SupplierController implements Initializable {
    @FXML
    private TextField txtIdSupplier;
    @FXML
    private TextField txtNamaSupplier;
    @FXML
    private TableView<Supplier> tableSupplier;
    @FXML
    private TableColumn<Supplier, String> columnIdSupplier;
    @FXML
    private TableColumn<Supplier, String> columnNamaSupplier;
    @FXML
    private TableColumn<Supplier, String> columnAlamatSupplier;
    @FXML
    private Button btnSaveSupplier;
    @FXML
    private Button btnResetSupplier;
    @FXML
    private Button btnUpdateSupplier;
    @FXML
    private TextField txtAlamatSupplier;

    public void onActionCloseSupplier(ActionEvent actionEvent) {
    }

    public void getSelectedItem(MouseEvent mouseEvent) {
    }

    public void onActionSaveSupplier(ActionEvent actionEvent) {
        Supplier supplier = new Supplier();
        supplier.setIdSupplier(txtIdSupplier.getText());
        supplier.setNamaSupplier(txtNamaSupplier.getText());
        supplier.setAlamatSupplier(txtAlamatSupplier.getText());
        barangController.getSupplierList().add(supplier);
        txtIdSupplier.clear();
        txtNamaSupplier.clear();
        txtAlamatSupplier.clear();

    }

    public void onActionResetSupplier(ActionEvent actionEvent) {
    }

    public void onActionUpdateSupplier(ActionEvent actionEvent) {
    }
    private BarangController barangController;

    public void setBarangController(BarangController barangController) {
        this.barangController = barangController;
        tableSupplier.setItems(barangController.getSupplierList());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnIdSupplier.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getIdSupplier()));
        columnNamaSupplier.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getNamaSupplier()));
        columnAlamatSupplier.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getAlamatSupplier()));
    }
}
