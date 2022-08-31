package com.example.db_connect_prak.dao;

import com.example.db_connect_prak.entity.Category;
import com.example.db_connect_prak.util.DaoService;
import com.example.db_connect_prak.util.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements DaoService<Category> {

    @Override
    public List<Category> fetchAll() throws SQLException, ClassNotFoundException {
        List<Category> categories = new ArrayList<>();
        Connection connection = MySQLConnection.createConnection();
        String query = "SELECT categoryId, categoryName FROM tbcategory";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        // gunain looping utk masukin data dr result set ke dalam list yg udah dibuat
        while(rs.next()){
            Category category = new Category();
            category.setCategoryId(rs.getInt("categoryId"));
            category.setCategoryName(rs.getString("categoryName"));
            categories.add(category);
        }
        rs.close();
        ps.close();
        connection.close();
        return categories;
    }

    @Override
    public int addData(Category object) throws SQLException, ClassNotFoundException {
        int result = 0;
        Connection connection = MySQLConnection.createConnection();
        String query = "INSERT INTO tbcategory(categoryId, categoryName) VALUES(?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1,object.getCategoryId());
        ps.setString(2,object.getCategoryName());
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
    public int updateData(Category object) throws SQLException, ClassNotFoundException {
        return 0;
    }

    @Override
    public int deleteData(Category object) throws SQLException, ClassNotFoundException {
        return 0;
    }
}
