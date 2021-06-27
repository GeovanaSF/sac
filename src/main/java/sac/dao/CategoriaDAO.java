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
import java.util.logging.Level;
import java.util.logging.Logger;
import sac.domain.Categoria;

/**
 *
 * @author geova
 */
public class CategoriaDAO implements DAO<Categoria> {

    private static final String QUERY_INSERT = "INSERT INTO Categoria (nome) VALUES (?)";

    private static final String QUERY_UPDATE = "UPDATE Categoria set nome=? WHERE categoria_id=?";

    private static final String QUERY_REMOVE = "DELETE FROM Categoria WHERE categoria_id=?";

    private static final String QUERY_LIST = "SELECT categoria_id, nome FROM categoria";

    private static final String QUERY_GET = "select categoria_id, nome FROM categoria where categoria_id = ?";

    private static final String QUERY_GETSINGLE = "select categoria_id, nome where nome like '%?%'";

    private Connection conn;

    public CategoriaDAO(Connection conn) throws DAOException {
        this.conn = conn;
    }

    @Override
    public Categoria getById(int id) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ps = conn.prepareStatement(QUERY_GET);
        ps.setInt(1, id);
        rs = ps.executeQuery();
        if (rs.next()) {
            return new Categoria(rs.getInt("categoria_id"), rs.getString("nome"));
        }
        return null;
    }

    @Override
    public Categoria getSingle(String email) throws DAOException, SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(QUERY_GETSINGLE);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Categoria(rs.getInt("categoria_id"), rs.getString("nome"));
            }
        } catch (SQLException ex) {
        } finally {
//            conn.close();
        }
        return null;
    }

    @Override
    public List<Categoria> getList() throws DAOException, SQLException {
        List<Categoria> lista = null;
        Statement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.createStatement();
            rs = ps.executeQuery(QUERY_LIST);
            lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(new Categoria(rs.getInt("categoria_id"), rs.getString("nome")));
            }
        } catch (SQLException ex) {
        } catch (Exception ex) {

        } finally {
            //conn.close();
        }
        return lista;
    }

    @Override
    public Integer insert(Categoria obj) throws DAOException, SQLException {
        int key = 0;

        try {
            PreparedStatement stmt = conn.prepareStatement(QUERY_INSERT,
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getNome());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                // Retrieve the auto generated key(s).
                key = rs.getInt(1);
            }
        } catch (SQLException sQLException) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, sQLException);
        }

        return key;
    }

    @Override
    public void update(Categoria obj) throws DAOException, SQLException {
        try {
            PreparedStatement stmt = conn.prepareStatement(QUERY_UPDATE);
            stmt.setString(1, obj.getNome());
            stmt.setInt(2, obj.getCategoria_id());
            stmt.executeUpdate();
        } catch (SQLException sQLException) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, sQLException);
        }

    }

    @Override
    public void remove(int id) throws DAOException, SQLException {
        PreparedStatement stmt = conn.prepareStatement(QUERY_REMOVE);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

}
