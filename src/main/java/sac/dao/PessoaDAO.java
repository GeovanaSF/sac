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
import sac.domain.Pessoa;

/**
 *
 * @author geova
 */
public class PessoaDAO implements DAO<Pessoa> {

    private static final String QUERY_INSERT = "INSERT INTO Pessoa (perfil_id, usuario_id, endereco_id, nome, cpf, telefone) VALUES (?,?,?,?,?,?)";
    private static final String QUERY_UPDATE = "UPDATE Pessoa SET perfil_id=?, usuario_id=?, endereco_id=?, nome=?, cpf=?, telefone=? WHERE pessoa_id=?";
    private static final String QUERY_REMOVE = "DELETE FROM Pessoa WHERE pessoa_id=?";
    private static final String QUERY_GET = "SELECT pessoa_id,perfil_id, usuario_id, endereco_id, nome, cpf, telefone FROM Pessoa";
    private final Connection conn;

    public PessoaDAO(Connection conn) throws DAOException {
        this.conn = conn;
    }

    @Override
    public Pessoa getById(int id) throws DAOException, SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(QUERY_GET + "WHERE pessoa_id = ?");
            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                return new Pessoa(rs.getInt("pessoa_id"), rs.getInt("perfil_id"),
                        rs.getInt("usuario_id"), rs.getInt("endereco_id"), rs.getString("nome"),
                        rs.getString("cpf"), rs.getString("telefone"));
            }
        } catch (SQLException ex) {
        } catch (Exception ex) {

        }
        return null;
    }

    @Override
    public Pessoa getSingle(String email) throws DAOException, SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(QUERY_GET + "WHERE nome like '%?%'");
            ps.setString(1, email);

            rs = ps.executeQuery();

            if (rs.next()) {
                return new Pessoa(rs.getInt("pessoa_id"), rs.getInt("perfil_id"),
                        rs.getInt("usuario_id"), rs.getInt("endereco_id"), rs.getString("nome"),
                        rs.getString("cpf"), rs.getString("telefone"));
            }
        } catch (SQLException ex) {
        } catch (Exception ex) {

        }
        return null;
    }

    @Override
    public List<Pessoa> getList() throws DAOException, SQLException {
        List<Pessoa> lista = null;
        Statement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.createStatement();
            rs = ps.executeQuery(QUERY_GET);
            lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(new Pessoa(rs.getInt("pessoa_id"), rs.getInt("perfil_id"),
                        rs.getInt("usuario_id"), rs.getInt("endereco_id"), rs.getString("nome"),
                        rs.getString("cpf"), rs.getString("telefone")));
            }
        } catch (SQLException ex) {
        } catch (Exception ex) {

        }
        return lista;
    }

    @Override
    public Integer insert(Pessoa obj) throws DAOException, SQLException {

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
        PreparedStatement stmt = conn.prepareStatement(QUERY_UPDATE);
        stmt.setInt(1, obj.getPerfil_Id());
        stmt.setInt(2, obj.getUsuario_Id());
        stmt.setInt(3, obj.getEndereco_Id());
        stmt.setString(4, obj.getNome());
        stmt.setString(5, obj.getCpf());
        stmt.setString(6, obj.getTelefone());
        stmt.setInt(7, obj.getPessoa_Id());
        stmt.executeUpdate();
    }

    @Override
    public void remove(Pessoa obj) throws DAOException, SQLException {
        PreparedStatement stmt = conn.prepareStatement(QUERY_REMOVE);
        stmt.setInt(1, obj.getPessoa_Id());
        stmt.executeUpdate();
    }

}
