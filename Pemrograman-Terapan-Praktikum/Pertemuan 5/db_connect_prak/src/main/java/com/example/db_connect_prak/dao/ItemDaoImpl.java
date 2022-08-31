package com.example.db_connect_prak.dao;

import com.example.db_connect_prak.entity.Category;
import com.example.db_connect_prak.entity.Item;
import com.example.db_connect_prak.util.DaoService;
import com.example.db_connect_prak.util.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements DaoService<Item> {
    @Override
    public List<Item> fetchAll() throws SQLException, ClassNotFoundException {
        List<Item> items = new ArrayList<>();
        Connection connection = MySQLConnection.createConnection();
        String query = "SELECT i.itemId,i.itemName,i.itemPrice,i.itemDescription,i.fk_tbCategory_tbItem,c.categoryName AS nama_kategori FROM tbitem i JOIN tbcategory c ON i.fk_tbCategory_tbItem = c.categoryId";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        // gunain looping utk masukin data dr result set ke dalam list yg udah dibuat
        while(rs.next()){
            Category category = new Category();
            category.setCategoryName(rs.getString("nama_kategori"));

            Item item = new Item();
            item.setItemId(rs.getInt("itemId"));
            item.setItemName(rs.getString("itemName"));
            item.setItemPrice(rs.getFloat("itemPrice"));
            item.setItemDescription(rs.getString("itemDescription"));
            item.setFkCategoryItem(category);

            items.add(item);
        }
        rs.close();
        ps.close();
        connection.close();
        return items;
    }

    @Override
    public int addData(Item object) throws SQLException, ClassNotFoundException {
        int result = 0;
        Connection connection = MySQLConnection.createConnection();
        String query = "INSERT INTO tbitem(itemId, itemName, itemPrice, itemDescription, fk_tbCategory_tbItem) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1,object.getItemId());
        ps.setString(2,object.getItemName());
        ps.setFloat(3,object.getItemPrice());
        ps.setString(4,object.getItemDescription());
        ps.setInt(5,object.getFkCategoryItem().getCategoryId());
        if (ps.executeUpdate() != 0){
            connection.commit();
            result = 1;
        }
        else {
            connection.rollback();
        }
        ps.close();
        connection.close();
        return result;
    }

    @Override
    public int updateData(Item object) throws SQLException, ClassNotFoundException {
        int result = 0;
        Connection connection = MySQLConnection.createConnection();
        String query = "UPDATE tbitem SET itemName = ? , itemPrice = ? , itemDescription = ? , fk_tbCategory_tbItem = ? WHERE itemId = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1,object.getItemName());
        ps.setFloat(2,object.getItemPrice());
        ps.setString(3,object.getItemDescription());
        ps.setInt(4,object.getFkCategoryItem().getCategoryId());
        ps.setInt(5,object.getItemId());
        if (ps.executeUpdate() != 0){
            connection.commit();
            result = 1;
        }
        else {
            connection.rollback();
        }
        ps.close();
        connection.close();
        return result;
    }

    @Override
    public int deleteData(Item object) throws SQLException, ClassNotFoundException {
        int result = 0;
        Connection connection = MySQLConnection.createConnection();
        String query = "DELETE FROM tbitem WHERE itemId = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1,object.getItemId());
        if (ps.executeUpdate() != 0){
            connection.commit();
            result = 1;
        }
        else {
            connection.rollback();
        }
        ps.close();
        connection.close();
        return result;
    }
}
