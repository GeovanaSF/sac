/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sac.controller;

import java.io.IOException;
import java.sql.Connection;
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
import sac.domain.Usuario;
import sac.model.Atendimentos;
import sac.model.Consultas;
import sac.model.DadosDashboard;

/**
 *
 * @author 
 */
public class Dashboard extends HttpServlet {

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
        
        Connection connection = ConnectionFactory.getConnection();
        
        AtendimentoDAO atDAO = new AtendimentoDAO(connection);
        Consultas consulta = new Consultas();
        Usuario user = (Usuario) request.getSession().getAttribute("usuarioLogado");
        if (user == null) {
            request.getSession().invalidate();
            response.sendRedirect("/SAC_V1/Login");
            return;
        }
        if (user.getPerfil_Id() == 1) {
            //Carrega dados do cliente
            List<Atendimentos> lista = atDAO.getListMeusAtendimentos(user.getUsuario_Id());
            consulta.setAtendimentos(lista);
        } else if (user.getPerfil_Id() == 2) {
            //carrega dados do funcionario
            List<Atendimentos> lista = atDAO.getListTodosAtendimentosAberto();
            consulta.setAtendimentos(lista);
        } else if (user.getPerfil_Id() == 3) {
            //carrega dados do gerente
            consulta.setAtendimentos_efetuados(atDAO.getQuantidadeBySituacao(2));
            consulta.setAtendimentos_abertos(atDAO.getQuantidadeBySituacao(1));
            int total = consulta.getAtendimentos_abertos() + consulta.getAtendimentos_efetuados();
            consulta.setAtendimentos_abertos_porcentagem(total == 0 ? 0 : (consulta.getAtendimentos_abertos() * 100) / total);
            
            List<DadosDashboard> dados = atDAO.getListAtendimentosTipoAtendimento();
            consulta.setDadosDashboard(dados);
        }
        
        request.setAttribute("consulta", consulta);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/dashboard.jsp");
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
        }  catch (DAOException ex) {
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
