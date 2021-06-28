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
    private static final String QUERY_GETPESSOA = "SELECT \n"
            + " P.pessoa_id, P.nome, P.cpf, P.telefone, E.cidade_id, C.estado_id, U.usuario_id, U.email, U.senha,\n"
            + " E.rua, E.numero, E.complemento, E.bairro, E.cep, P.endereco_id, P.perfil_id\n"
            + " FROM PESSOA P\n"
            + "JOIN ENDERECO E ON P.endereco_id = E.endereco_id\n"
            + "JOIN CIDADE C ON E.cidade_id = C.cidade_id\n"
            + "JOIN USUARIO U ON P.usuario_id = U.usuario_id ";
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

    public sac.model.Pessoa getPessoaById(int id) throws DAOException, SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        ps = conn.prepareStatement(QUERY_GETPESSOA + "WHERE pessoa_id = ?");
        ps.setInt(1, id);

        rs = ps.executeQuery();

        if (rs.next()) {
            return new sac.model.Pessoa(rs.getInt("pessoa_id"), rs.getString("nome"), rs.getString("cpf"), rs.getString("telefone"),
                    rs.getInt("cidade_id"), rs.getInt("estado_id"), rs.getString("email"), rs.getString("senha"),
                    rs.getInt("perfil_id"), rs.getString("rua"), rs.getString("numero"), rs.getString("complemento"),
                    rs.getString("bairro"), rs.getString("cep"), rs.getString("endereco_id"), rs.getInt("usuario_id"));
        }

        return null;
    }
    
    public sac.model.Pessoa getPessoaByUserId(int id) throws DAOException, SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        ps = conn.prepareStatement(QUERY_GETPESSOA + "WHERE P.usuario_id = ?");
        ps.setInt(1, id);

        rs = ps.executeQuery();

        if (rs.next()) {
            return new sac.model.Pessoa(rs.getInt("pessoa_id"), rs.getString("nome"), rs.getString("cpf"), rs.getString("telefone"),
                    rs.getInt("cidade_id"), rs.getInt("estado_id"), rs.getString("email"), rs.getString("senha"),
                    rs.getInt("perfil_id"), rs.getString("rua"), rs.getString("numero"), rs.getString("complemento"),
                    rs.getString("bairro"), rs.getString("cep"), rs.getString("endereco_id"), rs.getInt("usuario_id"));
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
        stmt.setObject(3, obj.getEndereco_Id());
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
        stmt.setObject(3, obj.getEndereco_Id());
        stmt.setString(4, obj.getNome());
        stmt.setString(5, obj.getCpf());
        stmt.setString(6, obj.getTelefone());
        stmt.setInt(7, obj.getPessoa_Id());
        stmt.executeUpdate();
    }

    @Override
    public void remove(int id) throws DAOException, SQLException {
        PreparedStatement stmt = conn.prepareStatement(QUERY_REMOVE);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

}
