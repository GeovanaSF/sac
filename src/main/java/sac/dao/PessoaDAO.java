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
import sac.domain.Pessoa;

/**
 *
 * @author geova
 */
public class PessoaDAO implements DAO<Pessoa> {

    private static final String QUERY_INSERT = "INSERT INTO Pessoa (perfil_id, usuario_id, endereco_id, nome, cpf, telefone) VALUES (?,?,?,?,?,?)";
    private final Connection conn;

    public PessoaDAO(Connection conn) throws DAOException {
        this.conn = conn;
    }

    @Override
    public Pessoa getById(int id) throws DAOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pessoa getSingle(String email) throws DAOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pessoa> getList() throws DAOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pessoa> getList(int top) throws DAOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer insert(Pessoa obj) throws DAOException, SQLException {
//        Connection conn = ConnectionFactory.getConnection();

        int key = 0;

        PreparedStatement stmt = conn.prepareStatement(QUERY_INSERT,
                Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, obj.getPerfil_Id());
        stmt.setInt(2, obj.getUsuario_Id());
        stmt.setInt(3, obj.getEndereco_Id());
        stmt.setString(4, obj.getNome());
        stmt.setString(5, obj.getCpf());
        stmt.setString(6, obj.getTelefone());
        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            // Retrieve the auto generated key(s).
            key = rs.getInt(1);
        }

        return key;
    }

    @Override
    public void update(Pessoa obj) throws DAOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Pessoa obj) throws DAOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
