/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sac.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import sac.domain.Estado;
import sac.domain.Usuario;

/**
 *
 * @author geova
 */
public class EstadoDAO implements DAO<Estado> {

    private static final String QUERY_INSERT = "INSERT INTO Usuario (email, senha) VALUES ('?', '?')";

    private static final String QUERY_LIST = "SELECT id_pessoa, nm_pessoa, dt_pessoa FROM tb_pessoa";

    private static final String QUERY_GETSINGLE = "select usuario_id, email, senha from usuario where email = ?";

    private Connection conn;
    
    public EstadoDAO(Connection conn) throws DAOException {
        this.conn = conn;
    }

    @Override
    public Estado getById(int id) throws DAOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Estado getSingle(String email) throws DAOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Estado> getList() throws DAOException, SQLException {
        return getList(0);
    }

    @Override
    public List<Estado> getList(int top) throws DAOException, SQLException {
//        Connection conn = ConnectionFactory.getConnection();
        
        if (top < 0) {
            return null;
        }
        conn = ConnectionFactory.getConnection();
        List<Estado> lista = null;
        Statement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.createStatement();
            rs = ps.executeQuery("select estado_id, nome from estado where pais_id=1");
            lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(new Estado(rs.getInt("estado_id"), rs.getString("nome")));
            }
        } catch (SQLException ex) {
        } catch (Exception ex) {

        } finally {
            conn.close();
        }
        return lista;
    }

    @Override
    public Integer insert(Estado obj) throws DAOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Estado obj) throws DAOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Estado obj) throws DAOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
