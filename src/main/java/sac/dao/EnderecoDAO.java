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
import sac.domain.Endereco;

/**
 *
 * @author geova
 */
public class EnderecoDAO implements DAO<Endereco> {

    private static final String QUERY_INSERT = "INSERT INTO Endereco (cidade_id, rua, numero, complemento, bairro, cep) VALUES (?,?,?,?,?,?)";
    private static final String QUERY_UPDATE = "UPDATE Endereco SET cidade_id=?, rua=?, numero=?, complemento=?, bairro=?, cep=? WHERE endereco_id=?";
    private static final String QUERY_REMOVE = "DELETE FROM Endereco WHERE endereco_id=?";
    private static final String QUERY_GET = "SELECT endereco_id, cidade_id, rua, numero, complemento, bairro, cep FROM Endereco";

    private final Connection conn;

    public EnderecoDAO(Connection conn) throws DAOException {
        this.conn = conn;
    }

    @Override
    public Endereco getById(int id) throws DAOException, SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(QUERY_GET + "WHERE endereco_id = ?");
            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                return new Endereco(rs.getInt("endereco_id"), rs.getInt("cidade_id"), rs.getString("rua"),
                        rs.getString("numero"), rs.getString("complemento"), rs.getString("bairro"),
                        rs.getString("cep"));
            }
        } catch (SQLException ex) {
        } catch (Exception ex) {

        } finally {
            //conn.close();
        }
        return null;
    }

    @Override
    public Endereco getSingle(String email) throws DAOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Endereco> getList() throws DAOException, SQLException {
        List<Endereco> lista = null;
        Statement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.createStatement();
            rs = ps.executeQuery(QUERY_GET);
            lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(new Endereco(rs.getInt("endereco_id"), rs.getInt("cidade_id"), rs.getString("rua"),
                        rs.getString("numero"), rs.getString("complemento"), rs.getString("bairro"),
                        rs.getString("cep")));
            }
        } catch (SQLException ex) {
        } catch (Exception ex) {

        } finally {
            //conn.close();
        }
        return lista;
    }

    @Override
    public Integer insert(Endereco obj) throws DAOException, SQLException {
        int key = 0;

        PreparedStatement stmt = conn.prepareStatement(QUERY_INSERT,
                Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, obj.getCidade_id());
        stmt.setString(2, obj.getRua());
        stmt.setString(3, obj.getNumero());
        stmt.setString(4, obj.getComplemento());
        stmt.setString(5, obj.getBairro());
        stmt.setString(6, obj.getCep());
        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            // Retrieve the auto generated key(s).
            key = rs.getInt(1);
        }

        return key;
    }

    @Override
    public void update(Endereco obj) throws DAOException, SQLException {
        PreparedStatement stmt = conn.prepareStatement(QUERY_UPDATE);
        stmt.setInt(1, obj.getCidade_id());
        stmt.setString(2, obj.getRua());
        stmt.setString(3, obj.getNumero());
        stmt.setString(4, obj.getComplemento());
        stmt.setString(5, obj.getBairro());
        stmt.setString(6, obj.getCep());
        stmt.setInt(7, obj.getEndereco_id());
        stmt.executeUpdate();
    }

    @Override
    public void remove(Endereco obj) throws DAOException, SQLException {
        PreparedStatement stmt = conn.prepareStatement(QUERY_REMOVE);
        stmt.setInt(1, obj.getEndereco_id());
        stmt.executeUpdate();
    }

}
