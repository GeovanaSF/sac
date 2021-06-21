/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sac.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import sac.dao.ConnectionFactory;
import sac.dao.DAOException;
import sac.dao.EnderecoDAO;
import sac.dao.EstadoDAO;
import sac.dao.PessoaDAO;
import sac.dao.UsuarioDAO;
import sac.domain.Endereco;
import sac.domain.Estado;
import sac.domain.Pessoa;
import sac.domain.Usuario;
import sac.model.Estados;
import sac.util.Erro;

/**
 *
 * @author geova
 */
public class Registrar extends HttpServlet {

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
            throws ServletException, IOException, DAOException, SQLException {
        Erro erros = new Erro();

        if (request.getParameter("bRegistrar") != null) {
            String nome = request.getParameter("nome");
            String cpf = request.getParameter("cpf");
            String telefone = request.getParameter("telefone");
            String rua = request.getParameter("rua");
            String numero = request.getParameter("numero");
            String complemento = request.getParameter("complemento");
            String bairro = request.getParameter("bairro");
            String cep = request.getParameter("cep");
            String estado = request.getParameter("estado");
            Integer cidade = Integer.parseInt(request.getParameter("cidade"));
            String email = request.getParameter("email");
            String password = request.getParameter("senha");
            String password_conf = request.getParameter("conf_senha");

            if (email == null || email.isEmpty()) {
                erros.add("'E-mail não informado!'");
            }
            if (password == null || password.isEmpty()) {
                erros.add("'Senha não informada!'");
            }
            if (password_conf == null || password_conf.isEmpty()) {
                erros.add("'Senha não informada!'");
            }
            if (!password.equals(password_conf)) {
                erros.add("'Confirmação de senha inválida'");
            }

            if (!erros.isExisteErros()) {
                Connection connection = ConnectionFactory.getConnection();
                Pessoa pessoa = new Pessoa(nome, cpf.replaceAll("[^0-9]", ""), telefone.replaceAll("[^0-9?!\\.]",""), 1);

                UsuarioDAO daoUser = new UsuarioDAO(connection);
                Usuario user = daoUser.getSingle(email);
                if (user != null) {
                    pessoa.setUsuario_Id(user.getUsuario_Id());
                } else {
                    user = new Usuario(email, password);
                    pessoa.setUsuario_Id(daoUser.insert(user));
                }

                EnderecoDAO enderecoDAO = new EnderecoDAO(connection);
                Endereco endereco = new Endereco(cidade, rua, numero, complemento, bairro, cep);
                pessoa.setEndereco_Id(enderecoDAO.insert(endereco));

                PessoaDAO pessoaDAO = new PessoaDAO(connection);
                int id = pessoaDAO.insert(pessoa);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/Login");
                dispatcher.forward(request, response);
            }
        }
        request.getSession().invalidate();

        request.setAttribute("mensagens", erros);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Login");
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
            Logger.getLogger(Registrar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Registrar.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Registrar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Registrar.class.getName()).log(Level.SEVERE, null, ex);
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
