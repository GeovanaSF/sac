/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sac.dao;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author geova
 * @param <T>
 */
public interface DAO<T> {
    public T getById(int id) throws DAOException, SQLException;
    public T getSingle(String email) throws DAOException, SQLException;
    public List<T> getList() throws DAOException, SQLException;
    public List<T> getList(int top) throws DAOException, SQLException;
    public Integer insert(T obj) throws DAOException, SQLException;
    public void update(T obj) throws DAOException, SQLException;
    public void remove(T obj) throws DAOException, SQLException;
}