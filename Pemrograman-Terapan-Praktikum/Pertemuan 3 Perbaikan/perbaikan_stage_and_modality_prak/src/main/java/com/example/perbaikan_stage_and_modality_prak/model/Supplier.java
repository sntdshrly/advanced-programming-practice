package com.example.perbaikan_stage_and_modality_prak.model;

import com.example.perbaikan_stage_and_modality_prak.controller.BarangController;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class Supplier {
    private String idSupplier;
    private String namaSupplier;
    private String alamatSupplier;
    @FXML
    private TableView<Supplier> tableSupplier;

    public Supplier(){

    }
    public Supplier(String idSupplier, String namaSupplier, String alamatSupplier) {
        this.idSupplier = idSupplier;
        this.namaSupplier = namaSupplier;
        this.alamatSupplier = alamatSupplier;
    }


    public String getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(String idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getNamaSupplier() {
        return namaSupplier;
    }

    public void setNamaSupplier(String namaSupplier) {
        this.namaSupplier = namaSupplier;
    }

    public String getAlamatSupplier() {
        return alamatSupplier;
    }

    public void setAlamatSupplier(String alamatSupplier) {
        this.alamatSupplier = alamatSupplier;
    }

    @Override
    public String toString() {
        return namaSupplier;
    }

}
