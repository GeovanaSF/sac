/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sac.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sac.dao.CategoriaDAO;
import sac.dao.ConnectionFactory;
import sac.dao.DAOException;
import sac.dao.ProdutoDAO;
import sac.domain.Categoria;
import sac.domain.Produto;
import sac.model.Categorias;
import sac.util.Erro;

/**
 *
 * @author geova
 */
public class Novo extends HttpServlet {

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
            throws ServletException, IOException, DAOException {

        String action = request.getServletPath();
        Erro erros = new Erro();
        sac.model.Cadastro cadastro = new sac.model.Cadastro();
        String url = "";

        Connection connection = ConnectionFactory.getConnection();

        if (request.getParameter("bRegistrar") != null) {
            sac.model.Cadastro pessoa = (sac.model.Cadastro) request.getAttribute("cadastro");

            String nome = request.getParameter("nome");
            String descricao = request.getParameter("descricao");

            Float peso = null;
            String peso_ = request.getParameter("peso");
            if (peso_ != null && !peso_.isEmpty()) {
                peso = Float.parseFloat(peso_);
            }

            Integer categoria_id = 0;
            String categoria_ = request.getParameter("categoria_id");
            if (categoria_ != null && !categoria_.isEmpty()) {
                categoria_id = Integer.parseInt(categoria_);
            }
            Integer produto_id = 0;
            String produto_ = request.getParameter("produto_id");
            if (produto_ != null && !produto_.isEmpty()) {
                produto_id = Integer.parseInt(produto_);
            }

            if (nome == null || nome.isEmpty()) {
                erros.add("'Nome é obrigatório!'");
            }
            if (action.equals("/Novo_Produto") && (categoria_id == 0)) {
                erros.add("'Categoria é obrigatório!'");
            }

            if (!erros.isExisteErros()) {
                if (action.equals("/Novo_Categoria")) {
                    try {
                        CategoriaDAO daoCategoria = new CategoriaDAO(connection);
                        Categoria categoria = new Categoria(categoria_id, nome);
                        if (categoria_id == 0) {
                            categoria.setCategoria_id(daoCategoria.insert(categoria));
                        } else {
                            daoCategoria.update(categoria);
                        }

                        url = "/Categoria";
                    } catch (SQLException ex) {
                        Logger.getLogger(Novo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (action.equals("/Novo_Produto")) {
                    try {
                        ProdutoDAO daoProduto = new ProdutoDAO(connection);
                        Produto produto = new Produto(produto_id, categoria_id, nome, descricao, peso);
                        if (produto_id == 0) {
                            produto.setProduto_id(daoProduto.insert(produto));
                        } else {
                            daoProduto.update(produto);
                        }

                        url = "/Produto";
                    } catch (SQLException ex) {
                        Logger.getLogger(Novo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                cadastro.setCategoria_id(categoria_id);
                cadastro.setProduto_id(produto_id);
                cadastro.setNome(nome);
                cadastro.setPeso(peso);
                cadastro.setDescricao(descricao);
            }
        }
        if (url.isEmpty()) {
            if (action.equals("/Categoria")) {
                url = "/jsp/categoria.jsp";
            } else if (action.equals("/Novo_Categoria")) {
                url = "/jsp/novo_categoria.jsp";
            } else if (action.equals("/Produto")) {
                url = "/jsp/produto.jsp";
            } else if (action.equals("/Novo_Produto")) {

                try {
                    CategoriaDAO catDAO = new CategoriaDAO(connection);
                    List<Categoria> categorias;
                    categorias = catDAO.getList();
                    Categorias c = new Categorias(categorias);
                    request.setAttribute("categorias", c);
                } catch (SQLException ex) {
                    Logger.getLogger(Novo.class.getName()).log(Level.SEVERE, null, ex);
                }

                url = "/jsp/novo_produto.jsp";
            } else if (action.equals("/Funcionario")) {
                url = "/jsp/novo_funcionario.jsp";
            } else if (action.equals("/Atendimento")) {
                url = "/jsp/novo_atendimento.jsp";
            }
        }

        request.setAttribute("mensagens", erros);
        request.setAttribute("cadatro", cadastro);

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (DAOException ex) {
            Logger.getLogger(Novo.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (DAOException ex) {
            Logger.getLogger(Novo.class.getName()).log(Level.SEVERE, null, ex);
        }
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
