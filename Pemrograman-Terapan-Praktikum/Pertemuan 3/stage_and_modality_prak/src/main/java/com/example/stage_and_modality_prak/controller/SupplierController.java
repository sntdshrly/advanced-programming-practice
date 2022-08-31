package com.example.stage_and_modality_prak.controller;

import com.example.stage_and_modality_prak.model.Barang;
import com.example.stage_and_modality_prak.model.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SupplierController {
    @FXML
    private TableView<Supplier> tableSupplier;
    @FXML
    private TableColumn<String, Supplier> columnIdSupplier;
    @FXML
    private TableColumn<String, Supplier> columnNamaSupplier;
    @FXML
    private TableColumn<String, Supplier> columnAlamatSupplier;
    @FXML
    public TextField txtIdSupplier;
    @FXML
    public TextField txtNamaSupplier;
    @FXML
    public TextField txtAlamatSupplier;
    @FXML
    public Button btnSaveSupplier;
    @FXML
    public Button btnResetSupplier;
    @FXML
    public Button btnUpdateSupplier;


    // declare variable
    private ObservableList<Supplier> supplierList;
    public int indeks;

    public void initialize(){
        supplierList = FXCollections.observableArrayList(
                new Supplier("dummy data id supplier","dummy data nama supplier","dummy data alamat supplier")
        );
        // tampilin table
        tableSupplier.setItems(supplierList);
        columnIdSupplier.setCellValueFactory(new PropertyValueFactory<String, Supplier>("id_supplier"));
        columnNamaSupplier.setCellValueFactory(new PropertyValueFactory<String, Supplier>("nama_supplier"));
        columnAlamatSupplier.setCellValueFactory(new PropertyValueFactory<String, Supplier>("alamat_supplier"));
    }
    @FXML
    protected void onActionSaveSupplier(ActionEvent e) throws IOException {
        supplierList.add(new Supplier(txtIdSupplier.getText(), txtNamaSupplier.getText(), txtAlamatSupplier.getText()));
    }
    @FXML
    protected void onActionResetSupplier() throws IOException {
        txtIdSupplier.clear();
        txtNamaSupplier.clear();
        txtAlamatSupplier.clear();
        btnSaveSupplier.setDisable(false);
        btnResetSupplier.setDisable(false);
        btnUpdateSupplier.setDisable(false);
    }
    @FXML
    protected void onActionUpdateSupplier(ActionEvent e) throws IOException {
        supplierList.set(indeks, new Supplier(txtIdSupplier.getText(), txtNamaSupplier.getText(), txtAlamatSupplier.getText()));
        onActionResetSupplier();
    }
    // fungsi getSelectedItem sesuai dengan fxid onMouseClicked="#getSelectedItem" pada tabel
    public void getSelectedItem(MouseEvent mouseEvent) {
        // ketika tabel yg diselectnya tidak kosong maka btnTambah akan didisable
        if (!tableSupplier.getSelectionModel().getSelectedCells().isEmpty()) {
            btnSaveSupplier.setDisable(true);
            btnResetSupplier.setDisable(false);
            btnUpdateSupplier.setDisable(false);
            indeks = tableSupplier.getSelectionModel().getSelectedIndex();
        }
        txtIdSupplier.setText(tableSupplier.getSelectionModel().getSelectedItem().getId_supplier());
        txtNamaSupplier.setText(tableSupplier.getSelectionModel().getSelectedItem().getNama_supplier());
        txtAlamatSupplier.setText(tableSupplier.getSelectionModel().getSelectedItem().getAlamat_supplier());
    }

    public void onActionCloseSupplier(ActionEvent actionEvent) {
        setSupplier(supplierList);
        ((Node)actionEvent.getSource()).getScene().getWindow().hide();
    }

    public ObservableList<Supplier> getSupplier() {
        return supplierList;
    }
    public void setSupplier(ObservableList<Supplier> supplierList) {
        this.supplierList = supplierList;
    }
}
