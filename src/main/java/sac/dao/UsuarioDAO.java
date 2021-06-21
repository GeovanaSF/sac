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
public class UsuarioDAO implements DAO<Usuario> {

    private static final String QUERY_INSERT = "INSERT INTO Usuario (email, senha, perfil_id) VALUES (?,?,?)";

    private static final String QUERY_LIST = "SELECT id_pessoa, nm_pessoa, dt_pessoa FROM tb_pessoa";

    private static final String QUERY_GETSINGLE = "select usuario_id, email, senha, perfil_id from usuario where email = ?";

    private final Connection conn;

    public UsuarioDAO(Connection connection) {
        this.conn = connection;
    }

    @Override
    public Usuario getSingle(String login) throws DAOException, SQLException {
//        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(QUERY_GETSINGLE);
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Usuario(rs.getInt("usuario_id"),
                        rs.getString("email"), rs.getString("senha"),rs.getInt("perfil_id"));
            }
        } catch (SQLException ex) {
        } finally {
//            conn.close();
        }
        return null;
    }

    @Override
    public List<Usuario> getList() throws DAOException, SQLException {
        return getList(0);
    }

    @Override
    public List<Usuario> getList(int top) throws DAOException, SQLException {
        if (top < 0) {
            return null;
        }
//        Connection conn = ConnectionFactory.getConnection();

        List<Usuario> lista = null;
        Statement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.createStatement();
            rs = ps.executeQuery("select " + (top > 0
                    ? "top " + top : "")
                    + "usuario_id, email, senha, perfil_id from usuario");
            lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(new Usuario(rs.getInt("usuario_id"), rs.getString("email"), rs.getString("senha"),rs.getInt("perfil_id")));
            }
        } catch (SQLException ex) {
        } finally {
//            conn.close();
        }
        return lista;
    }

    @Override
    public Usuario getById(int id) throws DAOException, SQLException {
//        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(QUERY_LIST + "WHERE Id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Usuario(rs.getInt("usuario_id"),
                        rs.getString("email"), rs.getString("senha"),rs.getInt("perfil_id"));
            }
        } catch (SQLException ex) {
        } finally {
//            conn.close();
        }
        return null;
    }

    @Override
    public Integer insert(Usuario obj) throws DAOException, SQLException {
//        Connection conn = ConnectionFactory.getConnection();

        int key = 0;

        try {
            PreparedStatement stmt = conn.prepareStatement(QUERY_INSERT,
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getEmail());
            stmt.setString(2, obj.getSenha());
            stmt.setInt(3, obj.getPerfil_Id());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                // Retrieve the auto generated key(s).
                key = rs.getInt(1);
            }
        } catch (SQLException sQLException) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, sQLException);
        } finally {
//            conn.close();
        }

        return key;
    }

    @Override
    public void update(Usuario obj) throws DAOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Usuario obj) throws DAOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
