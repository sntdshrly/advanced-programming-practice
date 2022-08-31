package com.example.db_connect_prak.controller;

import com.example.db_connect_prak.dao.CategoryDaoImpl;
import com.example.db_connect_prak.dao.ItemDaoImpl;
import com.example.db_connect_prak.entity.Category;
import com.example.db_connect_prak.entity.Item;
import com.example.db_connect_prak.util.DaoService;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CategoryController implements Initializable {
    @FXML
    private Button btnSaveCategory;
    @FXML
    private TextField txtIdCategory;
    @FXML
    private TextField txtNameCategory;
    @FXML
    private TableView<Category> tbCategory;
    @FXML
    private TableColumn<Category, Integer> colIdCategory;
    @FXML
    private TableColumn<Category, String> colNameCategory;
    private MainController mainController;
    private CategoryDaoImpl categoryDao;

    public void onActionSaveCategory(ActionEvent actionEvent) {
        if (txtIdCategory.getText().trim().isEmpty() || txtNameCategory.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in the blank!");
            alert.showAndWait();
        } else {
            Category category = new Category();

            category.setCategoryId(Integer.parseInt(txtIdCategory.getText().trim()));
            category.setCategoryName(txtNameCategory.getText().trim());
            // jika return add data satu maka akan kosongin list dan isi dg data baru
            try {
                if (categoryDao.addData(category) == 1) {
                    // yg ini bukan categories.clear() karna listnya ambil langsung dari maincontroller
                    mainController.getCategories().clear();
                    mainController.getCategories().addAll(categoryDao.fetchAll());
                    txtIdCategory.clear();
                    txtNameCategory.clear();
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        
    }

    public void setItemController(MainController mainController) {
        this.mainController = mainController;
        tbCategory.setItems(mainController.getCategories());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colIdCategory.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getCategoryId()).asObject());
        colNameCategory.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getCategoryName()));

        categoryDao = new CategoryDaoImpl();
    }
}
