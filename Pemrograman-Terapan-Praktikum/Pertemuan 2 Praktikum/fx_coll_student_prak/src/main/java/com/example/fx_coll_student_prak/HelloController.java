package com.example.fx_coll_student_prak;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class HelloController {
    @FXML
    private TextField txtNrp;
    @FXML
    private TextField txtNama;
    @FXML
    private TextField txtNilaiIpa;
    @FXML
    private TextField txtNilaiIps;
    @FXML
    private Button btnTambah;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnHapus;
    @FXML
    private TableView<Student> table1;
    @FXML
    private TableColumn<String, Student> columnNrp;
    @FXML
    private TableColumn<String, Student> columnNama;
    @FXML
    private TableColumn<String, Student> columnIpa;
    @FXML
    private TableColumn<String, Student> columnIps;

    // declare variable
    private ObservableList<Student> sList;
    public int indeks;

    public void initialize() {
        sList = FXCollections.observableArrayList(
                new Student(01, "dummy data nama1", 65, 80),
                new Student(02, "dummy data nama2", 75, 80)
        );
        // tampilin tabel
        table1.setItems(sList);
        columnNrp.setCellValueFactory(new PropertyValueFactory<String, Student>("nrp"));
        columnNama.setCellValueFactory(new PropertyValueFactory<String, Student>("nama"));
        columnIpa.setCellValueFactory(new PropertyValueFactory<String, Student>("ipa"));
        columnIps.setCellValueFactory(new PropertyValueFactory<String, Student>("ips"));
    }

    // fungsi getSelectedItem sesuai dengan fxid onMouseClicked="#getSelectedItem" pada tabel
    public void getSelectedItem(MouseEvent mouseEvent) {
        // ketika tabel yg diselectnya tidak kosong maka btnTambah akan didisable
        if (!table1.getSelectionModel().getSelectedCells().isEmpty()) {
            btnTambah.setDisable(true);
            btnUpdate.setDisable(false);
            btnHapus.setDisable(false);
            indeks = table1.getSelectionModel().getSelectedIndex(); // simpan indeks yang diseleksi
        }
        // dapetin value" di txt-nya
        txtNrp.setText(String.valueOf(table1.getSelectionModel().getSelectedItem().getNrp()));
        txtNama.setText(table1.getSelectionModel().getSelectedItem().getNama());
        txtNilaiIpa.setText(String.valueOf(table1.getSelectionModel().getSelectedItem().getIpa()));
        txtNilaiIps.setText(String.valueOf(table1.getSelectionModel().getSelectedItem().getIps()));
    }

    // untuk reset
    public void reset(){
        txtNrp.clear();
        txtNama.clear();
        txtNilaiIpa.clear();
        txtNilaiIps.clear();
        btnTambah.setDisable(false); // kembaliin lagi button tambahnya ke semula
        table1.getSelectionModel().clearSelection(); // deselect tabelnya
    }

    @FXML
    public void onActionTambah(ActionEvent e) {
        int sListnrp = Integer.parseInt(txtNrp.getText());
        String sListnama = txtNama.getText();
        float sListIpa = Float.parseFloat(txtNilaiIpa.getText());
        float sListIps = Float.parseFloat(txtNilaiIps.getText());
        sList.add(new Student(sListnrp, sListnama, sListIpa, sListIps));
    }

    @FXML
    public void onActionHapus(ActionEvent e) {
        sList.remove(table1.getSelectionModel().getSelectedItem());
        reset(); // dipanggil dari fungsi yang sudah dibuat yaitu fungsi reset
    }

    @FXML
    protected void onActionUpdate() {
        int sListnrp = Integer.parseInt(txtNrp.getText());
        String sListnama = txtNama.getText();
        float sListIpa = Float.parseFloat(txtNilaiIpa.getText());
        float sListIps = Float.parseFloat(txtNilaiIps.getText());
        sList.set(indeks, new Student(sListnrp, sListnama, sListIpa, sListIps));
        reset(); // dipanggil dari fungsi yang sudah dibuat yaitu fungsi reset
    }
}