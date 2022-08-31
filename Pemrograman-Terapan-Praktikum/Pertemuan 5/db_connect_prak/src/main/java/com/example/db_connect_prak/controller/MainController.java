package com.example.db_connect_prak.controller;

import com.example.db_connect_prak.Main;
import com.example.db_connect_prak.dao.CategoryDaoImpl;
import com.example.db_connect_prak.dao.ItemDaoImpl;
import com.example.db_connect_prak.entity.Category;
import com.example.db_connect_prak.entity.Item;
import com.example.db_connect_prak.util.MySQLConnection;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainController implements Initializable {
    @FXML
    private Button btnSave;
    @FXML
    private Button btnReset;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextArea txtAreaDescription;
    @FXML
    private ComboBox<Category> comboCategory;
    @FXML
    private TableView<Item> tbView1;
    @FXML
    private TableColumn<Item, Integer> tbColID;
    @FXML
    private TableColumn<Item, String> tbColName;
    @FXML
    private TableColumn<Item, Float> tbColPrice;
    @FXML
    private TableColumn<Item, Category> tbColCategory;
    //declare var
    private CategoryDaoImpl categoryDao;
    private ItemDaoImpl itemDao;
    private ObservableList<Item> items;
    private ObservableList<Category> categories;

    // ini nanti dipake juga buat di categorycontroller
    public ObservableList<Category> getCategories() {
        return categories;
    }

    private Item selectedItem; // utk mendapatkan data dari tb category

    public void onActionSave(ActionEvent actionEvent) {
        if (txtID.getText().trim().isEmpty() || txtName.getText().trim().isEmpty() || txtPrice.getText().trim().isEmpty() || txtAreaDescription.getText().trim().isEmpty() || comboCategory.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in the blank!");
            alert.showAndWait();
        } else {
            Item item = new Item();

            item.setItemId(Integer.parseInt(txtID.getText().trim()));
            item.setItemName(txtName.getText().trim());
            item.setItemPrice(Float.parseFloat(txtPrice.getText().trim()));
            item.setItemDescription(txtAreaDescription.getText().trim());
            item.setFkCategoryItem(comboCategory.getValue());
            // jika return add data satu maka akan kosongin list dan isi dg data baru
            try {
                if (itemDao.addData(item) == 1) {
                    items.clear();
                    items.addAll(itemDao.fetchAll());
                    txtID.clear();
                    txtName.clear();
                    txtPrice.clear();
                    txtAreaDescription.clear();
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void onActionReset(ActionEvent actionEvent) {
        reset();
    }

    private void reset() {
        txtID.clear();
        txtName.clear();
        txtPrice.clear();
        txtAreaDescription.clear();
        selectedItem = null;
        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

    }

    public void onActionUpdate(ActionEvent actionEvent) {
        if (txtID.getText().trim().isEmpty() || txtName.getText().trim().isEmpty() || txtPrice.getText().trim().isEmpty() || txtAreaDescription.getText().trim().isEmpty() || comboCategory == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in the blank!");
            alert.showAndWait();
        } else {
            selectedItem.setItemId(Integer.parseInt(txtID.getText().trim()));
            selectedItem.setItemName(txtName.getText().trim());
            selectedItem.setItemPrice(Float.parseFloat(txtPrice.getText().trim()));
            selectedItem.setItemDescription(txtAreaDescription.getText().trim());
            selectedItem.setFkCategoryItem(comboCategory.getValue());
            try {
                if (itemDao.updateData(selectedItem) == 1) {
                    items.clear();
                    items.addAll(itemDao.fetchAll());
                    reset();
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void onActionDelete(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure want to delete?");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            try {
                if (itemDao.deleteData(selectedItem) == 1) {
                    // jika result = 1 maka kita akan mengosongkan observable list
                    items.clear();
                    items.addAll(itemDao.fetchAll());
                    reset();
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoryDao = new CategoryDaoImpl();
        itemDao = new ItemDaoImpl();
        categories = FXCollections.observableArrayList();
        items = FXCollections.observableArrayList();

        try {
            items.addAll(itemDao.fetchAll());
            categories.addAll(categoryDao.fetchAll());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        comboCategory.setItems(categories);
        tbView1.setItems(items);
        tbColID.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getItemId()).asObject());
        tbColName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getItemName()));
        tbColPrice.setCellValueFactory(data -> new SimpleFloatProperty(data.getValue().getItemPrice()).asObject());
        tbColCategory.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getFkCategoryItem()));
    }

    public void tableClicked(MouseEvent mouseEvent) {
        selectedItem = tbView1.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            txtID.setText(String.valueOf(selectedItem.getItemId()));
            txtName.setText(selectedItem.getItemName());
            txtPrice.setText(String.valueOf(selectedItem.getItemPrice()));
            txtAreaDescription.setText(selectedItem.getItemDescription());
            comboCategory.setValue(selectedItem.getFkCategoryItem());
            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }

    public void onActionShowCategory(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("Categoryform.fxml"));
        Parent root = loader.load();
        CategoryController catController = loader.getController();
        catController.setItemController(this);
        Stage catStage = new Stage();
        catStage.setTitle("Data Category");
        catStage.setScene(new Scene(root));
        catStage.show();
    }

    public void onActionClose(ActionEvent actionEvent) {
        txtID.getScene().getWindow().hide();
    }

    public void onActionSimpleReport(ActionEvent actionEvent) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                JasperPrint jasperPrint;
                Connection connection = MySQLConnection.createConnection();
                Map param = new HashMap();
                jasperPrint = JasperFillManager.fillReport("project/LaporanAllData.jasper", param, connection);
                JasperViewer viewer = new JasperViewer(jasperPrint, false); // Kalau jaspernya diclose apakah aplikasinya diclose? set=false=tidak.
                viewer.setTitle("Report All Data");
                viewer.setVisible(true);
                return null;
            }
        };
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(task);
        service.shutdown();
    }

    public void onActionGroupReport(ActionEvent actionEvent) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                JasperPrint jasperPrint;
                Connection connection = MySQLConnection.createConnection();
                Map param = new HashMap();
                jasperPrint = JasperFillManager.fillReport("project/LaporanGroupBy.jasper", param, connection);
                JasperViewer viewer = new JasperViewer(jasperPrint, false); // Kalau jaspernya diclose apakah aplikasinya diclose? set=false=tidak.
                viewer.setTitle("Report All Data");
                viewer.setVisible(true);
                return null;
            }
        };
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(task);
        service.shutdown();
    }
}