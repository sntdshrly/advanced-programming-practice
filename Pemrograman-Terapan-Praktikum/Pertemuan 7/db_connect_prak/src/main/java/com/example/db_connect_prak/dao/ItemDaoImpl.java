package com.example.db_connect_prak.dao;

import com.example.db_connect_prak.entity.Tbitem;
import com.example.db_connect_prak.entity.Tbitem;
import com.example.db_connect_prak.util.DaoService;
import com.example.db_connect_prak.util.HibernateUtil;
import com.example.db_connect_prak.util.MySQLConnection;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements DaoService<Tbitem> {

    @Override
    public List<Tbitem> fetchAll() throws SQLException, ClassNotFoundException {
//    String query = "SELECT i.itemId,i.itemName,i.itemPrice,i.itemDescription,i.fk_tbCategory_tbItem,c.categoryName AS nama_kategori FROM tbitem i JOIN tbcategory c ON i.fk_tbCategory_tbItem = c.categoryId";
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Tbitem> criteriaQuery = criteriaBuilder.createQuery(Tbitem.class);
        criteriaQuery.from(Tbitem.class);
        List<Tbitem> items = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return items;
    }

    @Override
    public int addData(Tbitem object) throws SQLException, ClassNotFoundException {
//    String query = "INSERT INTO tbitem(itemId, itemName, itemPrice, itemDescription, fk_tbCategory_tbItem) VALUES(?, ?, ?, ?, ?)";
        int result = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(object);
            result = 1;
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
        }
        session.close();
        return result;
    }

    @Override
    public int updateData(Tbitem object) throws SQLException, ClassNotFoundException {
//    String query = "UPDATE tbitem SET itemName = ? , itemPrice = ? , itemDescription = ? , fk_tbCategory_tbItem = ? WHERE itemId = ?";
        int result = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.update(object);
            result = 1;
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
        }
        session.close();
        return result;
    }

    @Override
    public int deleteData(Tbitem object) throws SQLException, ClassNotFoundException {
//    String query = "DELETE FROM tbitem WHERE itemId = ?";
        int result = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.delete(object);
            result = 1;
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
        }
        session.close();
        return result;
    }
}
