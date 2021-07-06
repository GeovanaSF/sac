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
import sac.domain.Cidade;

/**
 *
 * @author 
 */
public class CidadeDAO {
    
    private Connection conn;
    
    public CidadeDAO(Connection conn) throws DAOException {
        this.conn = conn;
    }

    public List<Cidade> getList(String estado_id) throws DAOException, SQLException {
        
        List<Cidade> lista = null;
        Statement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.createStatement();
            rs = ps.executeQuery("select cidade_id, nome from cidade where estado_id=" + estado_id);
            lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(new Cidade(rs.getInt("cidade_id"), rs.getString("nome")));
            }
        } catch (SQLException ex) {
        } catch (Exception ex) {

        } finally {
        }
        return lista;
    }
}
