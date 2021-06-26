/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import sac.domain.Estado;
import sac.domain.Perfil;

/**
 *
 * @author geova
 */
public class PerfilDAO {
    private final Connection conn;

    public PerfilDAO(Connection conn) throws DAOException {
        this.conn = conn;
    }

    public List<Perfil> getList() throws DAOException, SQLException {
        List<Perfil> lista = null;
        Statement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.createStatement();
            rs = ps.executeQuery("select perfil_id, nome from perfil");
            lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(new Perfil(rs.getInt("perfil_id"), rs.getString("nome")));
            }
        } catch (SQLException ex) {
        } catch (Exception ex) {

        } 
        
        return lista;
    }
}
