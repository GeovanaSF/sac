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
import sac.model.Atendimentos;

/**
 *
 * @author geova
 */
public class AtendimentoDAO implements DAO<Atendimento> {

    private static final String QUERY_GETALL = "select \n"
            + "a.atendimento_id as id,\n"
            + "pc.nome as cliente,\n"
            + "pf.nome as funcionario,\n"
            + "p.nome as produto,\n"
            + "ta.nome as tipoatendimento, \n"
            + "to_char(a.dataCriacao, 'DD-MM-YYYY HH:mm:ss') as dataCriacao, \n"
            + "to_char(a.dataFinalizacao, 'DD-MM-YYYY HH:mm:ss') as dataFinalizacao, \n"
            + "a.situacao as situacao_id,"
            + "case when a.situacao=1 then 'Aberto'"
            + " when a.situacao=2 then 'Finalizado' end as situacao\n"
            + " from atendimento a\n"
            + "join usuario c on a.cliente_id=c.usuario_id\n"
            + "join pessoa pc on c.usuario_id = pc.usuario_id\n"
            + "left join usuario f on a.funcionario_id=f.usuario_id\n"
            + "left join pessoa pf on f.usuario_id=pf.usuario_id\n"
            + "join produto p on a.produto_id = p.produto_id\n"
            + "join tipoatendimento ta on a.tipoatendimento_id=ta.tipoatendimento_id";

    private static final String QUERY_GET = "select atendimento_id, cliente_id, funcionario_id, produto_id, tipoatendimento_id, datacriacao, datafinalizacao, situacao, descricao, solucao from atendimento";
    private static final String QUERY_INSERT = "INSERT INTO Atendimento (cliente_id, funcionario_id, produto_id, tipoatendimento_id, datacriacao, datafinalizacao, situacao, descricao, solucao) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String QUERY_UPDATE = "UPDATE Atendimento SET cliente_id=?, funcionario_id=?, produto_id=?, tipoatendimento_id=?, datacriacao=?, datafinalizacao=?, situacao=?, descricao=?, solucao=? where atendimento_id=?";
    private static final String QUERY_UPDATECLIENTE = "UPDATE Atendimento SET produto_id=?, tipoatendimento_id=?, descricao=? where atendimento_id=?";
    private static final String QUERY_UPDATEATEND = "UPDATE Atendimento SET funcionario_id=?, datafinalizacao=?, situacao=?, solucao=? where atendimento_id=?";
    private static final String QUERY_DELETE = "DELETE FROM Atendimento WHERE atendimento_id = ?";
    private final Connection conn;

    public AtendimentoDAO(Connection conn) throws DAOException {
        this.conn = conn;
    }

    @Override
    public Atendimento getById(int id) {
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = conn.prepareStatement(QUERY_GET + " WHERE atendimento_id = ?");
            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                return new Atendimento(rs.getInt("atendimento_id"), rs.getInt("cliente_id"), rs.getObject("funcionario_id"),
                        rs.getInt("produto_id"), rs.getInt("tipoatendimento_id"), rs.getTimestamp("dataCriacao"),
                        rs.getTimestamp("dataFinalizacao"), rs.getInt("situacao"),
                        rs.getString("descricao"), rs.getString("solucao"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(AtendimentoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                lista.add(new Atendimento(rs.getInt("atendimento_id"), rs.getInt("cliente_id"), rs.getInt("funcionario_id"),
                        rs.getInt("produto_id"), rs.getInt("tipoatendimento_id"), rs.getTimestamp("dataCriacao"),
                        rs.getTimestamp("dataFinalizacao"), rs.getInt("situacao"),
                        rs.getString("descricao"), rs.getString("solucao")));
            }
        } catch (SQLException ex) {
        } catch (Exception ex) {

        }
        return lista;
    }

    public List<Atendimentos> getListTodosAtendimentos() {
        try {
            List<Atendimentos> lista = null;
            Statement ps = null;
            ResultSet rs = null;
            ps = conn.createStatement();
            rs = ps.executeQuery(QUERY_GETALL + " order by a.dataCriacao");
            lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(new Atendimentos(rs.getInt("id"), rs.getString("cliente"),
                        rs.getString("funcionario"), rs.getString("dataCriacao"),
                        rs.getString("dataFinalizacao"), rs.getString("produto"),
                        rs.getString("situacao"),rs.getInt("situacao_id")));
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(AtendimentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Atendimentos> getListTodosAtendimentosAberto() {
        try {
            List<Atendimentos> lista = null;
            Statement ps = null;
            ResultSet rs = null;
            ps = conn.createStatement();
            rs = ps.executeQuery(QUERY_GETALL + " where a.situacao=1 order by a.dataCriacao");
            lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(new Atendimentos(rs.getInt("id"), rs.getString("cliente"),
                        rs.getString("funcionario"), rs.getString("datacriacao"),
                        rs.getString("dataFinalizacao"), rs.getString("produto"),
                        rs.getString("situacao"),rs.getInt("situacao_id")));
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(AtendimentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Atendimentos> getListMeusAtendimentos(int usuario_id) {
        try {
            List<Atendimentos> lista = null;
            Statement ps = null;
            ResultSet rs = null;
            ps = conn.createStatement();
            rs = ps.executeQuery(QUERY_GETALL + " where c.usuario_id=" + usuario_id + " order by a.dataCriacao");
            lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(new Atendimentos(rs.getInt("id"), rs.getString("cliente"),
                        rs.getString("funcionario"), rs.getString("datacriacao"),
                        rs.getString("dataFinalizacao"), rs.getString("produto"),
                        rs.getString("situacao"),rs.getInt("situacao_id")));
            }

            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(AtendimentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }catch (Exception ex) {
            Logger.getLogger(AtendimentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Integer insert(Atendimento obj) {
        int key = 0;
        try {

            PreparedStatement stmt = conn.prepareStatement(QUERY_INSERT,
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, obj.getCliente_id());
            stmt.setObject(2, obj.getFuncionario_id());
            stmt.setInt(3, obj.getProduto_id());
            stmt.setInt(4, obj.getTipoatendimento_id());
            stmt.setTimestamp(5, obj.getDatacriacaoTime());
            stmt.setObject(6, obj.getDatafinalizacaoTime());
            stmt.setInt(7, obj.getSituacao());
            stmt.setString(8, obj.getDescricao());
            stmt.setString(9, obj.getSolucao());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                // Retrieve the auto generated key(s).
                key = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AtendimentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return key;
    }

    @Override
    public void update(Atendimento obj) {
        try {
            PreparedStatement stmt = conn.prepareStatement(QUERY_UPDATE);
            stmt.setInt(1, obj.getCliente_id());
            stmt.setObject(2, obj.getFuncionario_id());
            stmt.setInt(3, obj.getProduto_id());
            stmt.setInt(4, obj.getTipoatendimento_id());
            stmt.setTimestamp(5, obj.getDatacriacaoTime());
            stmt.setObject(6, obj.getDatafinalizacaoTime());
            stmt.setInt(7, obj.getSituacao());
            stmt.setString(8, obj.getDescricao());
            stmt.setString(9, obj.getSolucao());
            stmt.setInt(10, obj.getAtendimento_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AtendimentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateAtendimento(Atendimento obj) {
        try {
            PreparedStatement stmt = conn.prepareStatement(QUERY_UPDATEATEND);
            stmt.setObject(1, obj.getFuncionario_id());
            stmt.setObject(2, obj.getDatafinalizacaoTime());
            stmt.setInt(3, obj.getSituacao());
            stmt.setString(4, obj.getSolucao());
            stmt.setInt(5, obj.getAtendimento_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AtendimentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateAtendimentoCliente(Atendimento obj) {
        try {
            PreparedStatement stmt = conn.prepareStatement(QUERY_UPDATECLIENTE);
            stmt.setInt(1, obj.getProduto_id());
            stmt.setInt(2, obj.getTipoatendimento_id());
            stmt.setString(3, obj.getDescricao());
            stmt.setInt(4, obj.getAtendimento_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AtendimentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public void remove(int id) throws SQLException {
        try {
            PreparedStatement stmt = conn.prepareStatement(QUERY_DELETE);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        }
    }

}
