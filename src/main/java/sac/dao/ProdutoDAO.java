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
import sac.domain.Produto;

/**
 *
 * @author 
 */
public class ProdutoDAO implements DAO<Produto> {

    private static final String QUERY_INSERT = "INSERT INTO Produto (categoria_id, nome, descricao, peso) VALUES (?, ?, ?, ?)";

    private static final String QUERY_UPDATE = "UPDATE Produto SET categoria_id=?, nome=?, descricao=?, peso=? WHERE produto_id=?";

    private static final String QUERY_REMOVE = "DELETE FROM Produto WHERE produto_id=?";

    private static final String QUERY_GET = "SELECT produto_id, p.nome, descricao, peso, p.categoria_id, c.nome as categoria FROM Produto p join categoria c on p.categoria_id = c.categoria_id ";

    private Connection conn;

    public ProdutoDAO(Connection conn) throws DAOException {
        this.conn = conn;
    }

    @Override
    public Produto getById(int id) throws DAOException, SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ps = conn.prepareStatement(QUERY_GET + " WHERE produto_id = ?");
        ps.setInt(1, id);

        rs = ps.executeQuery();

        if (rs.next()) {
            return new Produto(rs.getInt("produto_id"), rs.getInt("categoria_id"),
                    rs.getString("nome"), rs.getString("descricao"),
                    rs.getFloat("peso"), rs.getString("categoria"));
        }

        return null;
    }

    @Override
    public Produto getSingle(String email) throws DAOException, SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(QUERY_GET + "WHERE nome like '%?%'");
            ps.setString(1, email);

            rs = ps.executeQuery();

            if (rs.next()) {
                return new Produto(rs.getInt("produto_id"), rs.getInt("categoria_id"),
                        rs.getString("nome"), rs.getString("descricao"),
                        rs.getFloat("peso"), rs.getString("categoria"));
            }
        } catch (SQLException ex) {
        } catch (Exception ex) {

        }

        return null;
    }

    @Override
    public List<Produto> getList() throws DAOException, SQLException {
        List<Produto> lista = null;
        Statement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.createStatement();
            rs = ps.executeQuery(QUERY_GET);
            lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(new Produto(rs.getInt("produto_id"), rs.getInt("categoria_id"), rs.getString("nome"), rs.getString("descricao"), rs.getFloat("peso"), rs.getString("categoria")));
            }
        } catch (SQLException ex) {
        } catch (Exception ex) {

        }

        return lista;
    }

    @Override
    public Integer insert(Produto obj) throws DAOException, SQLException {
        int key = 0;

        try {
            PreparedStatement stmt = conn.prepareStatement(QUERY_INSERT,
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, obj.getCategoria_id());
            stmt.setString(2, obj.getNome());
            stmt.setString(3, obj.getDescricao());
            stmt.setFloat(4, obj.getPeso());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                // Retrieve the auto generated key(s).
                key = rs.getInt(1);
            }
        } catch (SQLException sQLException) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, sQLException);
        }

        return key;
    }

    @Override
    public void update(Produto obj) throws DAOException, SQLException {
        try {
            PreparedStatement stmt = conn.prepareStatement(QUERY_UPDATE);
            stmt.setInt(1, obj.getCategoria_id());
            stmt.setString(2, obj.getNome());
            stmt.setString(3, obj.getDescricao());
            stmt.setFloat(4, obj.getPeso());
            stmt.setInt(5, obj.getProduto_id());
            stmt.executeUpdate();
        } catch (SQLException sQLException) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, sQLException);
        }
    }

    @Override
    public void remove(int id) throws DAOException, SQLException {
        try {
            PreparedStatement stmt = conn.prepareStatement(QUERY_REMOVE);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException sQLException) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, sQLException);
        }
    }

}
