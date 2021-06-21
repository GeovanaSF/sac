/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sac.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sac.dao.ConnectionFactory;
import sac.dao.DAOException;
import sac.dao.UsuarioDAO;
import sac.domain.Usuario;
import sac.util.Erro;

/**
 *
 * @author geova
 */

public class Login extends HttpServlet {

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

        if (request.getParameter("bLogin") != null) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            if (email == null || email.isEmpty()) {
                erros.add("'E-mail não informado!'");
            }
            if (password == null || password.isEmpty()) {
                erros.add("'Senha não informada!'");
            }
            if (!erros.isExisteErros()) {
                Connection connection = ConnectionFactory.getConnection();
                UsuarioDAO dao = new UsuarioDAO(connection);
                Usuario user = dao.getSingle(email);
                if (user != null) {
                    if (user.getSenha().equalsIgnoreCase(password)) {
                        request.getSession().setAttribute("usuarioLogado", user);
                        response.sendRedirect("/SAC_V1/Dashboard");
                        return;
                    } else {
                        erros.add("'Senha inválida!'");
                    }
                } else {
                    erros.add("'Usuário não encontrado!'");
                }
            }
        }
        request.getSession().invalidate();

        request.setAttribute("mensagens", erros);
        String action = request.getServletPath();
        String pagina = request.getParameter("pagina");
        if (pagina == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/index.jsp");
            dispatcher.forward(request, response);
        } else if (action == "/esqueciSenha") {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/esqueci_senha.jsp");
            dispatcher.forward(request, response);
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

        try {
            processRequest(request, response);
        } catch (DAOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Login da aplicação";
    }// </editor-fold>

}
