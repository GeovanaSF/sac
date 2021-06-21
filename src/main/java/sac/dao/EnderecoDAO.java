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
import java.util.List;
import sac.domain.Endereco;

/**
 *
 * @author geova
 */
public class EnderecoDAO implements DAO<Endereco> {

    private static final String QUERY_INSERT = "INSERT INTO Endereco (cidade_id, rua, numero, complemento, bairro, cep) VALUES (?,?,?,?,?,?)";

    private final Connection conn;

    public EnderecoDAO(Connection conn) throws DAOException {
        this.conn = conn;
    }

    @Override
    public Endereco getById(int id) throws DAOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Endereco getSingle(String email) throws DAOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Endereco> getList() throws DAOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Endereco> getList(int top) throws DAOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer insert(Endereco obj) throws DAOException, SQLException {
//        Connection conn = ConnectionFactory.getConnection();

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Endereco obj) throws DAOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
