/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sac.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sac.dao.AtendimentoDAO;
import sac.dao.CategoriaDAO;
import sac.dao.CidadeDAO;
import sac.dao.ConnectionFactory;
import sac.dao.DAOException;
import sac.util.Retorno;

/**
 *
 * @author geova
 */
public class Excluir extends HttpServlet {

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
        Retorno retorno = new Retorno();
        try {
            Connection connection = ConnectionFactory.getConnection();

            String tipo = request.getParameter("tipo");
            String _id = request.getParameter("id");

            if (tipo.equals("atendimento")) {
                int id = Integer.parseInt(_id);

                AtendimentoDAO dao = new AtendimentoDAO(connection);
                dao.remove(id);
            }else if (tipo.equals("categoria")) {
                int id = Integer.parseInt(_id);

                CategoriaDAO dao = new CategoriaDAO(connection);
                dao.remove(id);
            }

            retorno.setSucesso(true);
        } catch (DAOException ex) {
            retorno.setSucesso(false);
        } catch (SQLException ex) {
            retorno.setSucesso(false);
        }
        String json = new Gson().toJson(retorno);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
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
