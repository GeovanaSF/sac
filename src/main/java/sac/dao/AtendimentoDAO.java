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
import sac.domain.Atendimento;
import sac.domain.Estado;
import sac.model.Atendimentos;
import sac.model.SituacaoAtendimento;

/**
 *
 * @author geova
 */
public class AtendimentoDAO implements DAO<Atendimento> {

    private static final String QUERY_GETALL = "select \n"
            + "a.atendimento_id as id,\n"
            + "c.nome as cliente,\n"
            + "f.nome as funcionario,\n"
            + "p.nome as produto,\n"
            + "ta.nome as tipoatendimento, \n"
            + "a.dataCriacao as dataCriacao, \n"
            + "a.dataFinalizacao as dataFinalizacao\n"
            + "a.situacao as situacao\n"
            + " from atendimento a\n"
            + "join pessoa c on a.cliente_id=c.pessoa_id\n"
            + "join pessoa f on a.funcionario_id=f.pessoa_id\n"
            + "join produto p on a.produto_id = p.produto_id\n"
            + "join tipoatendimento ta on a.tipoatendimento_id=ta.tipoatendimento_id";

    private static final String QUERY_GET = "select atendimento_id, cliente_id, funcionario_id, produto_id, tipoatendimento_id, datacriacao, datafinalizacao, situacao, descricao, solucao from atendimento";
    private static final String QUERY_INSERT = "INSERT INTO Atendimento (cliente_id, funcionario_id, produto_id, tipoatendimento_id, datacriacao, datafinalizacao, situacao, descricao, solucao) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String QUERY_UPDATE = "UPDATE Atendimento SET cliente_id=?, funcionario_id=?, produto_id=?, tipoatendimento_id=?, datacriacao=?, datafinalizacao=?, situacao=?, descricao=?, solucao=? where atendimento_id=?";
    private static final String QUERY_DELETE = "DELETE FROM Atendimento WHERE atendimento_id = ?";
    private final Connection conn;

    public AtendimentoDAO(Connection conn) throws DAOException {
        this.conn = conn;
    }

    @Override
    public Atendimento getById(int id) throws DAOException, SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(QUERY_GET + "WHERE usuario_id = ?");
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                return new Atendimento(rs.getInt("atendimento_id"),rs.getInt("cliente_id"),rs.getInt("funcionario_id"), 
                        rs.getInt("produto_id"),rs.getInt("tipoatendimento_id"), rs.getDate("dataCriacao"),
                        rs.getDate("dataFinalizacao"), rs.getInt("situacao"),
                        rs.getString("descricao"),rs.getString("solucao"));
            }
        } catch (SQLException ex) {
        } catch (Exception ex) {

        } 
        
        return null;
    }

    @Override
    public Atendimento getSingle(String email) throws DAOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Atendimento> getList() throws DAOException, SQLException {
        List<Atendimento> lista = null;
        Statement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.createStatement();
            rs = ps.executeQuery(QUERY_GET + " order by a.dataCriacao");
            lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(new Atendimento(rs.getInt("atendimento_id"),rs.getInt("cliente_id"),rs.getInt("funcionario_id"), 
                        rs.getInt("produto_id"),rs.getInt("tipoatendimento_id"), rs.getDate("dataCriacao"),
                        rs.getDate("dataFinalizacao"), rs.getInt("situacao"),
                        rs.getString("descricao"),rs.getString("solucao")));
            }
        } catch (SQLException ex) {
        } catch (Exception ex) {

        } 
        return lista;
    }

    public List<Atendimentos> getListTodosAtendimentos() throws DAOException, SQLException {
        List<Atendimentos> lista = null;
        Statement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.createStatement();
            rs = ps.executeQuery(QUERY_GETALL + " order by a.dataCriacao");
            lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(new Atendimentos(rs.getInt("id"), rs.getString("cliente"),
                        rs.getString("funcionario"), rs.getDate("dataCriacao"),
                        rs.getDate("dataFinalizacao"), rs.getString("produto"),
                        rs.getInt("situacao")));
            }
        } catch (SQLException ex) {
        } catch (Exception ex) {

        } finally {
            //conn.close();
        }
        return lista;
    }

    public List<Atendimentos> getListTodosAtendimentosAberto() throws DAOException, SQLException {
        List<Atendimentos> lista = null;
        Statement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.createStatement();
            rs = ps.executeQuery(QUERY_GETALL + " where a.situacao=1 order by a.dataCriacao");
            lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(new Atendimentos(rs.getInt("id"), rs.getString("cliente"),
                        rs.getString("funcionario"), rs.getDate("datacriacao"),
                        rs.getDate("datadinalizacao"), rs.getString("produto"),
                        rs.getInt("situacao")));
            }
        } catch (SQLException ex) {
        } catch (Exception ex) {

        } finally {
            //conn.close();
        }
        return lista;
    }

    public List<Atendimentos> getListMeusAtendimentos(int usuario_id) throws DAOException, SQLException {
        List<Atendimentos> lista = null;
        Statement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.createStatement();
            rs = ps.executeQuery(QUERY_GETALL + " where c.usuario_id=" + usuario_id + " order by a.dataCriacao");
            lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(new Atendimentos(rs.getInt("id"), rs.getString("cliente"),
                        rs.getString("funcionario"), rs.getDate("datacriacao"),
                        rs.getDate("datadinalizacao"), rs.getString("produto"),
                        rs.getInt("situacao")));
            }
        } catch (SQLException ex) {
        } catch (Exception ex) {

        } finally {
            //conn.close();
        }
        return lista;
    }

    @Override
    public Integer insert(Atendimento obj) throws DAOException, SQLException {
        int key = 0;

        PreparedStatement stmt = conn.prepareStatement(QUERY_INSERT,
                Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, obj.getCliente_id());
        stmt.setInt(2, obj.getFuncionario_id());
        stmt.setInt(3, obj.getProduto_id());
        stmt.setInt(4, obj.getTipoatendimento_id());
        stmt.setDate(5, obj.getDatacriacao());
        stmt.setDate(6, obj.getDatafinalizacao());
        stmt.setInt(7, obj.getSituacao());
        stmt.setString(8, obj.getDescricao());
        stmt.setString(9, obj.getSolucao());
        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            // Retrieve the auto generated key(s).
            key = rs.getInt(1);
        }

        return key;
    }

    @Override
    public void update(Atendimento obj) throws DAOException, SQLException {
        PreparedStatement stmt = conn.prepareStatement(QUERY_UPDATE);
        stmt.setInt(1, obj.getCliente_id());
        stmt.setInt(2, obj.getFuncionario_id());
        stmt.setInt(3, obj.getProduto_id());
        stmt.setInt(4, obj.getTipoatendimento_id());
        stmt.setDate(5, obj.getDatacriacao());
        stmt.setDate(6, obj.getDatafinalizacao());
        stmt.setInt(7, obj.getSituacao());
        stmt.setString(8, obj.getDescricao());
        stmt.setString(9, obj.getSolucao());
        stmt.setInt(10, obj.getAtendimento_id());
        stmt.executeUpdate();
    }

    @Override
    public void remove(Atendimento obj) throws DAOException, SQLException {
        PreparedStatement stmt = conn.prepareStatement(QUERY_DELETE);
        stmt.setInt(1, obj.getAtendimento_id());
        stmt.executeUpdate();
    }

}
