package com.example.db_connect_prak.dao;

import com.example.db_connect_prak.entity.Tbcategory;
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

public class CategoryDaoImpl implements DaoService<Tbcategory> {

    @Override
    public List<Tbcategory> fetchAll() throws HibernateException {
//        String query = "SELECT categoryId, categoryName FROM tbcategory";
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Tbcategory> criteriaQuery = criteriaBuilder.createQuery(Tbcategory.class);
        criteriaQuery.from(Tbcategory.class);
        List<Tbcategory> categories = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return categories;
    }

    @Override
    public int addData(Tbcategory object) throws HibernateException {
//        String query = "INSERT INTO tbcategory(categoryId, categoryName) VALUES(?, ?)";
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
    public int updateData(Tbcategory object) throws SQLException, ClassNotFoundException {
        return 0;
    }

    @Override
    public int deleteData(Tbcategory object) throws SQLException, ClassNotFoundException {
        return 0;
    }
}
