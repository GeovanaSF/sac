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

/**
 *
 * @author 
 */
public class EstadoDAO {

    private static final String QUERY_LIST = "SELECT id_pessoa, nm_pessoa, dt_pessoa FROM tb_pessoa";

    private Connection conn;
    
    public EstadoDAO(Connection conn) throws DAOException {
        this.conn = conn;
    }

    public List<Estado> getList(int pais_id) throws DAOException, SQLException {        
        List<Estado> lista = null;
        Statement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.createStatement();
            rs = ps.executeQuery("select estado_id, nome from estado where pais_id=" + pais_id);
            lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(new Estado(rs.getInt("estado_id"), rs.getString("nome")));
            }
        } catch (SQLException ex) {
        } catch (Exception ex) {
        } 
        
        return lista;
    }
}
