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
import sac.domain.Atendimento;
import sac.domain.TipoAtendimento;

/**
 *
 * @author geova
 */
public class TipoAtendimentoDAO implements DAO<TipoAtendimento>{
private static final String QUERY_GET = "select tipoatendimento_id, nome from tipoAtendimento";
    private static final String QUERY_INSERT = "INSERT INTO TipoAtendimento (nome) VALUES (?)";
    private static final String QUERY_UPDATE = "UPDATE TipoAtendimento SET nome=? where tipoatendimento_id=?";
    private static final String QUERY_DELETE = "DELETE FROM TipoAtendimento WHERE tipoatendimento_id = ?";
    private final Connection conn;

    public TipoAtendimentoDAO(Connection conn) throws DAOException {
        this.conn = conn;
    }
    @Override
    public TipoAtendimento getById(int id) throws DAOException, SQLException {
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = conn.prepareStatement(QUERY_GET + "WHERE usuario_id = ?");
            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                return new TipoAtendimento(rs.getInt("tipoatendimento_id"), rs.getString("nome"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(AtendimentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public TipoAtendimento getSingle(String email) throws DAOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TipoAtendimento> getList() throws DAOException, SQLException {
        List<TipoAtendimento> lista = null;
        Statement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.createStatement();
            rs = ps.executeQuery(QUERY_GET);
            lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(new TipoAtendimento(rs.getInt("tipoatendimento_id"), rs.getString("nome")));
            }
        } catch (SQLException ex) {
        } catch (Exception ex) {

        }
        return lista;
    }

    @Override
    public Integer insert(TipoAtendimento obj) throws DAOException, SQLException {
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
    public void update(TipoAtendimento obj) throws DAOException, SQLException {
        try {
            PreparedStatement stmt = conn.prepareStatement(QUERY_UPDATE);
            stmt.setString(1, obj.getNome());
            stmt.setInt(2, obj.getTipoAtendimento_id());
            stmt.executeUpdate();
        } catch (SQLException sQLException) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, sQLException);
        }
    }

    @Override
    public void remove(int id) throws DAOException, SQLException {
        try {
            PreparedStatement stmt = conn.prepareStatement(QUERY_UPDATE);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException sQLException) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, sQLException);
        }
    }
    
}
