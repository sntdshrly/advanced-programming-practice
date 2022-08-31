package com.example.stage_and_modality_prak.controller;

import com.example.stage_and_modality_prak.Main;
import com.example.stage_and_modality_prak.model.Barang;

import com.example.stage_and_modality_prak.model.Supplier;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;

public class BarangController {
    @FXML
    private TableView<Barang> tableBarang;
    @FXML
    private TableColumn<String, Barang> columnId;
    @FXML
    private TableColumn<String, Barang> columnNama;
    @FXML
    private TableColumn<String, Barang> columnSupplier;
    @FXML
    public TextField txtId;
    @FXML
    public TextField txtNama;
    @FXML
    public Button btnSave;
    @FXML
    public Button btnReset;
    @FXML
    public Button btnUpdate;

    // declare variable
    private ObservableList<Barang> barangList;
    private ObservableList<Supplier> supplierList;
    private ObservableList<String> namaSupplier;
    public ComboBox<String> comboSupplier;
    public int indeks;
    Stage new_stage;

    public void initialize() {
        new_stage = new Stage(); // jangan lupa inisialisasi stage
        barangList = FXCollections.observableArrayList(
                new Barang("dummy data id","dummy data nama","dummy data supplier")
        );
        // tampilin table
        tableBarang.setItems(barangList);
        columnId.setCellValueFactory(new PropertyValueFactory<String, Barang>("id"));
        columnNama.setCellValueFactory(new PropertyValueFactory<String, Barang>("nama"));
        columnSupplier.setCellValueFactory(new PropertyValueFactory<String, Barang>("supplier"));

    }
    // open window baru buat supplier management
    @FXML
    public void onActionShowSupplier() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("supplier-view.fxml"));
        Parent root = loader.load(); // bisa jadi error makanya pake try-catch atau throws exception
        Scene new_scene = new Scene(root);
        new_stage.setScene(new_scene);
        new_stage.setTitle("Data Supplier");
        SupplierController suppCont = loader.getController();

        new_stage.showAndWait();

        supplierList = suppCont.getSupplier();
        namaSupplier = FXCollections.observableArrayList();
        // Pakai for supaya bentuknya tidak lokasi objek
        for(Supplier suppList: supplierList) {
            namaSupplier.add(suppList.getNama_supplier());
        }
        System.out.println(namaSupplier);
        comboSupplier.setItems(namaSupplier);
        comboSupplier.getSelectionModel().select(0);
    }

    @FXML
    protected void onActionSave(ActionEvent e) throws IOException {
        barangList.add(new Barang(txtId.getText(),txtNama.getText(),comboSupplier.getValue()));
    }
    @FXML
    protected void onActionReset() throws IOException {
        txtId.clear();
        txtNama.clear();
        comboSupplier.getSelectionModel().select(0);
        btnSave.setDisable(false);
        btnReset.setDisable(false);
        btnUpdate.setDisable(false);
        tableBarang.getSelectionModel().clearSelection(); // deselect tabelnya
    }
    
    @FXML
    protected void onActionUpdate(ActionEvent e) throws IOException {
        barangList.set(indeks, new Barang(txtId.getText(), txtNama.getText(), comboSupplier.getValue()));
        onActionReset();
    }
    // fungsi getSelectedItem sesuai dengan fxid onMouseClicked="#getSelectedItem" pada tabel
    public void getSelectedItem(MouseEvent mouseEvent) {
        // ketika tabel yg diselectnya tidak kosong maka btnTambah akan didisable
        if (!tableBarang.getSelectionModel().getSelectedCells().isEmpty()) {
            btnSave.setDisable(true);
            btnReset.setDisable(false);
            btnUpdate.setDisable(false);
            indeks = tableBarang.getSelectionModel().getSelectedIndex();
        }
        txtId.setText(tableBarang.getSelectionModel().getSelectedItem().getId());
        txtNama.setText(tableBarang.getSelectionModel().getSelectedItem().getNama());
        comboSupplier.setValue(tableBarang.getSelectionModel().getSelectedItem().getSupplier());
    }
}