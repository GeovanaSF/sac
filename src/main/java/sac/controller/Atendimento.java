/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sac.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sac.dao.AtendimentoDAO;
import sac.dao.ConnectionFactory;
import sac.dao.DAOException;
import sac.dao.ProdutoDAO;
import sac.dao.TipoAtendimentoDAO;
import sac.domain.Produto;
import sac.domain.TipoAtendimento;
import sac.domain.Usuario;
import sac.model.Atendimentos;
import sac.model.Consultas;
import sac.model.Produtos;
import sac.model.TipoAtendimentos;
import sac.util.Erro;

/**
 *
 * @author geova
 */
public class Atendimento extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getServletPath();
            Erro erros = new Erro();
            String url = "";
            Connection connection = ConnectionFactory.getConnection();

            AtendimentoDAO atDAO = new AtendimentoDAO(connection);
            Consultas consulta = new Consultas();
            sac.domain.Atendimento atend = new sac.domain.Atendimento();
            Usuario user = (Usuario) request.getSession().getAttribute("usuarioLogado");

            String parametros = request.getQueryString();
            if (request.getParameter("bSalvar") != null && parametros == null) {
                String produto = request.getParameter("produto_id");
                String tipoatendimento = request.getParameter("tipoatendimento_id");
                String descricao = request.getParameter("descricao");
                String id_atendimento = request.getParameter("atendimento_id");
                String solucao = request.getParameter("solucao");

                Integer produto_id = 0;
                if (produto!= null && !produto.isEmpty()) {
                    produto_id = Integer.parseInt(produto);
                }
                Integer tipoatendimento_id = 0;
                if (tipoatendimento!= null && !tipoatendimento.isEmpty()) {
                    tipoatendimento_id = Integer.parseInt(tipoatendimento);
                }
                Integer atendimento_id = 0;
                if (!id_atendimento.isEmpty()) {
                    atendimento_id = Integer.parseInt(id_atendimento);
                }

                if (descricao == null || descricao.isEmpty()) {
                    erros.add("'Preencha o campo descricao!'");
                }
                if (produto_id == 0 && user.getPerfil_Id() == 1) {
                    erros.add("'Selecione um produto!'");
                }
                if (tipoatendimento_id == 0 && user.getPerfil_Id() == 1) {
                    erros.add("'Selecione um tipo de atendimento!'");
                }
                if (user.getPerfil_Id() == 2 && (solucao == null || solucao.isEmpty())) {
                    erros.add("'Preencha uma solução!'");
                }

                if (!erros.isExisteErros()) {
                    sac.domain.Atendimento atendimento = new sac.domain.Atendimento();
                    atendimento.setCliente_id(user.getUsuario_Id());
                    atendimento.setProduto_id(produto_id);
                    atendimento.setDescricao(descricao);
                    atendimento.setTipoatendimento_id(tipoatendimento_id);
                    atendimento.setAtendimento_id(atendimento_id);
                    atendimento.setSolucao(solucao);
                    
                    if (user.getPerfil_Id() == 2) {
                        atendimento.setFuncionario_id(user.getUsuario_Id());
                        atendimento.setSituacao(2);

                        long millis = System.currentTimeMillis();
                        java.sql.Date date = new java.sql.Date(millis);
                        atendimento.setDatafinalizacao(date);
                    } else {
                        atendimento.setFuncionario_id(null);
                        atendimento.setSituacao(1);
                    }

                    if (atendimento_id == 0) {
                        long millis = System.currentTimeMillis();
                        java.sql.Date date = new java.sql.Date(millis);
                        atendimento.setDatacriacao(date);
                    }

                    AtendimentoDAO atendDAO = new AtendimentoDAO(connection);
                    if (atendimento_id == 0) {
                        atendimento_id = atendDAO.insert(atendimento);
                    } else if (user.getPerfil_Id() == 2) {
                        atendDAO.updateAtendimento(atendimento);
                    } else {
                        atendDAO.updateAtendimentoCliente(atendimento);
                    }

                    request.setAttribute("bSalvar", "");
                    if (user.getPerfil_Id() == 1) {
                        RequestDispatcher dispatcher = request.getRequestDispatcher("MeusAtendimentos?page=1");
                        dispatcher.forward(request, response);
                    } else {
                        RequestDispatcher dispatcher = request.getRequestDispatcher("TodosAtendimentos?page=1");
                        dispatcher.forward(request, response);
                    }
                } else {
                    atend.setProduto_id(produto_id);
                    atend.setDescricao(descricao);
                    atend.setTipoatendimento_id(tipoatendimento_id);
                }
            }

            if (url.isEmpty()) {
                if (action.equals("/TodosAtendimentos")) {
                    List<Atendimentos> lista = atDAO.getListTodosAtendimentos();
                    consulta.setAtendimentos(lista);

                    url = "/jsp/atendimento.jsp";
                } else if (action.equals("/TodosAtendimentosAberto")) {
                    List<Atendimentos> lista = atDAO.getListTodosAtendimentosAberto();
                    consulta.setAtendimentos(lista);

                    url = "/jsp/atendimento.jsp";
                } else if (action.equals("/MeusAtendimentos")) {
                    List<Atendimentos> lista = atDAO.getListMeusAtendimentos(user.getUsuario_Id());
                    consulta.setAtendimentos(lista);

                    url = "/jsp/atendimento.jsp";
                } else if (action.equals("/Novo_Atendimento")) {
                    try {
                        ProdutoDAO produtoDAO = new ProdutoDAO(connection);
                        List<Produto> produtos;
                        produtos = produtoDAO.getList();
                        Produtos p = new Produtos(produtos);
                        request.setAttribute("produtos", p);

                        TipoAtendimentoDAO tipoAtendimentoDAO = new TipoAtendimentoDAO(connection);
                        List<TipoAtendimento> tipos = tipoAtendimentoDAO.getList();
                        TipoAtendimentos ta = new TipoAtendimentos(tipos);
                        request.setAttribute("tipoatendimentos", ta);

                    } catch (SQLException ex) {
                        Logger.getLogger(Atendimento.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (DAOException ex) {
                        Logger.getLogger(Atendimento.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    url = "/jsp/novo_atendimento.jsp";
                } else if (action.equals("/Atendimento")) {
                    String _id = request.getParameter("id");
                    if (_id == null || _id.isEmpty()) {
                        url = "/jsp/atendimento.jsp";
                    } else {
                        int id = Integer.parseInt(_id);
                        if (id > 0) {

                            try {
                                AtendimentoDAO atenDAO = new AtendimentoDAO(connection);
                                atend = atenDAO.getById(id);
                                if (atend != null) {
                                    url = "/jsp/novo_atendimento.jsp";

                                    ProdutoDAO produtoDAO = new ProdutoDAO(connection);
                                    List<Produto> produtos;
                                    produtos = produtoDAO.getList();
                                    Produtos p = new Produtos(produtos);
                                    request.setAttribute("produtos", p);

                                    TipoAtendimentoDAO tipoAtendimentoDAO = new TipoAtendimentoDAO(connection);
                                    List<TipoAtendimento> tipos = tipoAtendimentoDAO.getList();
                                    TipoAtendimentos ta = new TipoAtendimentos(tipos);
                                    request.setAttribute("tipoatendimentos", ta);
                                } else {
                                    url = "/jsp/atendimento.jsp";
                                }
                            } catch (SQLException ex) {
                                url = "/jsp/atendimento.jsp";
                            }
                        }
                    }
                }

                request.setAttribute("action", action);
                request.setAttribute("mensagens", erros);
                request.setAttribute("consulta", consulta);
                request.setAttribute("atendimento", atend);

                RequestDispatcher dispatcher = request.getRequestDispatcher(url);
                dispatcher.forward(request, response);
            }
        } catch (DAOException ex) {
            Logger.getLogger(Atendimento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
