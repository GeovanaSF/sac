/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sac.dao;

import sac.domain.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author geova
 */
//public class UsuarioDAO implements DAO<Usuario> {
//
//    private static final String QUERY_INSERT = "INSERT INTO Usuario (email, senha, salt, perfil_id) VALUES (?,?,?,?)";
//    private static final String QUERY_UPDATE = "UPDATE Usuario SET email=?, senha=?, salt=?, perfil_id=? WHERE usuario_id=?";
//    private static final String QUERY_DELETE = "DELETE FROM Usuario WHERE usuario_id=?";
//
//    private static final String QUERY_LIST = "SELECT usuario_id, email, senha, salt, perfil_id from usuario";
//
//    private static final String QUERY_GETSINGLE = "select usuario_id, email, senha, salt, perfil_id from usuario where email = ?";
//
//    private final Connection conn;
//
//    public UsuarioDAO(Connection connection) {
//        this.conn = connection;
//    }
//
//    @Override
//    public Usuario getSingle(String login) throws DAOException, SQLException {
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            ps = conn.prepareStatement(QUERY_GETSINGLE);
//            ps.setString(1, login);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                return new Usuario(rs.getInt("usuario_id"),
//                        rs.getString("email"), rs.getString("senha"), rs.getString("salt"), rs.getInt("perfil_id"));
//            }
//        } catch (SQLException ex) {
//        }
//
//        return null;
//    }
//
//    @Override
//    public List<Usuario> getList() throws DAOException, SQLException {
//        List<Usuario> lista = null;
//        Statement ps = null;
//        ResultSet rs = null;
//        try {
//            ps = conn.createStatement();
//            rs = ps.executeQuery("select usuario_id, email, senha, salt, perfil_id from usuario");
//            lista = new ArrayList<>();
//            while (rs.next()) {
//                lista.add(new Usuario(rs.getInt("usuario_id"), rs.getString("email"), rs.getString("senha"), rs.getString("salt"), rs.getInt("perfil_id")));
//            }
//        } catch (SQLException ex) {
//        } finally {
//        }
//        return lista;
//    }
//
//    @Override
//    public Usuario getById(int id) throws DAOException, SQLException {
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            ps = conn.prepareStatement(QUERY_LIST + "WHERE usuario_id = ?");
//            ps.setInt(1, id);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                return new Usuario(rs.getInt("usuario_id"),
//                        rs.getString("email"), rs.getString("senha"), rs.getString("salt"), rs.getInt("perfil_id"));
//            }
//        } catch (SQLException ex) {
//        }
//
//        return null;
//    }
//
//    @Override
//    public Integer insert(Usuario obj) throws DAOException, SQLException {
//        int key = 0;
//
//        try {
//            PreparedStatement stmt = conn.prepareStatement(QUERY_INSERT,
//                    Statement.RETURN_GENERATED_KEYS);
//            stmt.setString(1, obj.getEmail());
//            stmt.setString(2, obj.getSenha());
//            stmt.setString(3, obj.getKey());
//            stmt.setInt(4, obj.getPerfil_Id());
//            stmt.executeUpdate();
//
//            ResultSet rs = stmt.getGeneratedKeys();
//            if (rs.next()) {
//                // Retrieve the auto generated key(s).
//                key = rs.getInt(1);
//            }
//        } catch (SQLException sQLException) {
//            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, sQLException);
//        }
//
//        return key;
//    }
//
//    @Override
//    public void update(Usuario obj) throws DAOException, SQLException {
//        try {
//            PreparedStatement stmt = conn.prepareStatement(QUERY_UPDATE);
//            stmt.setString(1, obj.getEmail());
//            stmt.setString(2, obj.getSenha());
//            stmt.setString(3, obj.getKey());
//            stmt.setInt(4, obj.getPerfil_Id());
//            stmt.setInt(5, obj.getUsuario_Id());
//            stmt.executeUpdate();
//
//        } catch (SQLException sQLException) {
//            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, sQLException);
//        }
//    }
//
//    @Override
//    public void remove(int id) throws DAOException, SQLException {
//        try {
//            PreparedStatement stmt = conn.prepareStatement(QUERY_DELETE);
//            stmt.setInt(1, id);
//            stmt.executeUpdate();
//
//        } catch (SQLException sQLException) {
//            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, sQLException);
//        }
//    }
//
//}
