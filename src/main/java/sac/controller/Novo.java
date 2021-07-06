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
import sac.dao.CidadeDAO;
import sac.dao.ConnectionFactory;
import sac.dao.DAOException;
import sac.dao.EstadoDAO;
import sac.dao.PessoaDAO;
import sac.dao.ProdutoDAO;
import sac.domain.Categoria;
import sac.domain.Estado;
import sac.domain.Produto;
import sac.model.Categorias;
import sac.model.Estados;
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
        sac.model.Pessoa p = new sac.model.Pessoa();
        sac.model.Novo novo = new sac.model.Novo();

        String url = "";

        Connection connection = ConnectionFactory.getConnection();

        if (url.isEmpty()) {
            if (action.equals("/Categoria")) {
                String _id = request.getParameter("id");
                if (_id == null || _id.isEmpty()) {
                    url = "/jsp/categoria.jsp";

                    try {
                        CategoriaDAO catDAO = new CategoriaDAO(connection);
                        List<Categoria> categorias = catDAO.getList();
                        novo.setCategorias(categorias);
                    } catch (SQLException ex) {
                        Logger.getLogger(Novo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    int id = Integer.parseInt(_id);
                    if (id > 0) {

                        try {
                            CategoriaDAO catDAO = new CategoriaDAO(connection);
                            Categoria categoria = catDAO.getById(id);
                            if (categoria != null) {
                                cadastro.setCategoria_id(categoria.getCategoria_id());
                                cadastro.setNome(categoria.getNome());

                                url = "/jsp/novo_categoria.jsp";
                            } else {
                                url = "/jsp/categoria.jsp";
                            }
                        } catch (SQLException ex) {
                            url = "/jsp/categoria.jsp";
                        }
                    }
                }
            } else if (action.equals("/Produto")) {
                String _id = request.getParameter("id");
                if (_id == null || _id.isEmpty()) {

                    url = "/jsp/produto.jsp";

                    try {
                        ProdutoDAO prodDAO = new ProdutoDAO(connection);
                        List<Produto> produtos = prodDAO.getList();
                        novo.setProdutos(produtos);
                    } catch (SQLException ex) {
                        Logger.getLogger(Novo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    int id = Integer.parseInt(_id);
                    if (id > 0) {

                        try {
                            ProdutoDAO prodDAO = new ProdutoDAO(connection);
                            Produto produto = prodDAO.getById(id);
                            if (produto != null) {
                                cadastro.setCategoria_id(produto.getCategoria_id());
                                cadastro.setNome(produto.getNome());
                                cadastro.setPeso(produto.getPeso());
                                cadastro.setDescricao(produto.getDescricao());
                                cadastro.setProduto_id(produto.getProduto_id());

                                url = "/jsp/novo_produto.jsp";

                                CategoriaDAO catDAO = new CategoriaDAO(connection);
                                List<Categoria> categorias;
                                categorias = catDAO.getList();
                                Categorias c = new Categorias(categorias);
                                request.setAttribute("categorias", c);
                            } else {
                                url = "/jsp/produto.jsp";
                            }
                        } catch (SQLException ex) {
                            url = "/jsp/produto.jsp";
                        }
                    }
                }
            } else if (action.equals("/Funcionario")) {
                String _id = request.getParameter("id");
                if (_id == null || _id.isEmpty()) {
                    url = "/jsp/funcionario.jsp";
                    try {
                        PessoaDAO pessoaDAO = new PessoaDAO(connection);
                        List<sac.model.Pessoa> funcionarios = pessoaDAO.getPessoaByPerfilId(1);
                        novo.setFuncionarios(funcionarios);
                    } catch (SQLException ex) {
                        Logger.getLogger(Novo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    int id = Integer.parseInt(_id);
                    if (id > 0) {

                        try {
                            PessoaDAO pessoaDAO = new PessoaDAO(connection);
                            p = pessoaDAO.getPessoaById(id);
                            if (p != null) {
                                p.setSenha("");

                                url = "/jsp/novo_funcionario.jsp";

                                EstadoDAO estadoDAO = new EstadoDAO(connection);
                                List<Estado> estados = estadoDAO.getList(1);
                                Estados e = new Estados(estados);
                                request.setAttribute("estados", e);

                                CidadeDAO cidadeDAO = new CidadeDAO(connection);
                                List<sac.domain.Cidade> cidades = cidadeDAO.getList(p.getEstado_id().toString());
                                e.setCidades(cidades);
                            } else {
                                url = "/jsp/funcionario.jsp";
                            }
                        } catch (SQLException ex) {
                            url = "/jsp/funcionario.jsp";
                        }
                    }
                }
            }
        }

        request.setAttribute("mensagens", erros);
        request.setAttribute("cadastro", cadastro);
        request.setAttribute("pessoa", p);
        request.setAttribute("novo", novo);

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
        }  catch (DAOException ex) {
            request.setAttribute("mensagem", "Erro no DAO: " + ex.getMessage());
            request.getRequestDispatcher("/jsp/erro.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("mensagem", "Erro: " + ex.getMessage());
            request.getRequestDispatcher("/jsp/erro.jsp").forward(request, response);
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
            request.setAttribute("mensagem", "Erro no DAO: " + ex.getMessage());
            request.getRequestDispatcher("/jsp/erro.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("mensagem", "Erro: " + ex.getMessage());
            request.getRequestDispatcher("/jsp/erro.jsp").forward(request, response);
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
