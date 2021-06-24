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
import sac.domain.Estado;
import sac.domain.Produto;

/**
 *
 * @author geova
 */
public class ProdutoDAO implements DAO<Produto>{

    private static final String QUERY_INSERT = "INSERT INTO Produto (categoria_id, nome, descricao, peso) VALUES (?, ?, ?, ?)";

    private static final String QUERY_LIST = "SELECT produto_id, nome, descricao, peso, categoria_id FROM produto";

    private static final String QUERY_GET = "select produto_id, nome, descricao, peso, categoria_id where produto_id = ?";

    private Connection conn;
    
    public ProdutoDAO(Connection conn) throws DAOException {
        this.conn = conn;
    }
    @Override
    public Produto getById(int id) throws DAOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Produto getSingle(String email) throws DAOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Produto> getList() throws DAOException, SQLException {
        return getList(0);
    }

    @Override
    public List<Produto> getList(int top) throws DAOException, SQLException {
        if (top < 0) {
            return null;
        }
        
        List<Produto> lista = null;
        Statement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.createStatement();
            rs = ps.executeQuery(QUERY_LIST);
            lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(new Produto(rs.getInt("produto_id"), rs.getInt("categoria_id"), rs.getString("nome"), rs.getString("descricao"), rs.getFloat("peso")));
            }
        } catch (SQLException ex) {
        } catch (Exception ex) {

        } finally {
            //conn.close();
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
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, sQLException);
        } finally {
//            conn.close();
        }

        return key;
    }

    @Override
    public void update(Produto obj) throws DAOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Produto obj) throws DAOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
