/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sac.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import sac.dao.ConnectionFactory;
import sac.dao.DAOException;

/**
 *
 * @author geova
 */
public class Relatorio extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws sac.dao.DAOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, DAOException {

        String action = request.getServletPath();
        Connection connection = ConnectionFactory.getConnection();

        if (action.equals("/RelatorioFuncionario")) {
            //retorna pdf
            JasperPrint jasperPrint = null;
            try {
                String reportFileName = "\\Relatorio_Funcionarios.jrxml";
                String reportPath = request.getServletContext().getRealPath("/") + reportFileName;

                String targetFileName = reportFileName.replace(".jrxml", ".pdf");
                JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
                jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);
                ServletOutputStream outputstream = response.getOutputStream();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, byteArrayOutputStream);
                response.setContentType("application/pdf");
                outputstream.write(byteArrayOutputStream.toByteArray());
                response.setHeader("Cache-Control", "max-age=0");
                response.setHeader("Content-Disposition", "attachment; filename=" + targetFileName);
                outputstream.flush();
                outputstream.close();

                request.setAttribute("mensagem", "Relatório gerado com sucesso!");
                request.getRequestDispatcher("/jsp/erro.jsp").forward(request, response);
                //String host = "http://" + request.getServerName() + ":" + request.getServerPort();
                //String jasper = request.getContextPath() + "/Relatorio_Funcionarios.jasper";
                //URL jasperURL = new URL(host + jasper);

                //HashMap params = new HashMap();
                //byte[] bytes = JasperRunManager.runReportToPdf(jasperURL.openStream(), params, connection);
                //if (bytes != null) {
                //    response.setContentType("application/pdf");
                //    OutputStream ops = response.getOutputStream();
                //    ops.write(bytes);
                //}
            } catch (JRException ex) {
                request.setAttribute("mensagem", "Erro no jasper: " + ex.getMessage());
                request.getRequestDispatcher("/jsp/erro.jsp").forward(request, response);
                Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (action.equals("/RelatorioProduto")) {
            //retorna pdf
            JasperPrint jasperPrint = null;
            try {
                String reportFileName = "\\Relatorio_Produtos.jrxml";
                String reportPath = request.getServletContext().getRealPath("/") + reportFileName;

                String targetFileName = reportFileName.replace(".jrxml", ".pdf");
                JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
                jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);
                ServletOutputStream outputstream = response.getOutputStream();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, byteArrayOutputStream);
                response.setContentType("application/pdf");
                outputstream.write(byteArrayOutputStream.toByteArray());
                response.setHeader("Cache-Control", "max-age=0");
                response.setHeader("Content-Disposition", "attachment; filename=" + targetFileName);
                outputstream.flush();
                outputstream.close();
            } catch (JRException ex) {
                request.setAttribute("mensagem", "Erro no jasper: " + ex.getMessage());
                request.getRequestDispatcher("/jsp/erro.jsp").forward(request, response);
                Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equals("/RelatorioAtendimento")) {
            //retorna tela para escolher data
            request.setAttribute("mensagem", "");
            request.setAttribute("tipo", 1);
            request.getRequestDispatcher("/jsp/relatorio.jsp").forward(request, response);
        } else if (action.equals("/RelatorioReclamacao")) {
            //retorna tela para escolher situação
            request.setAttribute("tipo", 2);
            request.getRequestDispatcher("/jsp/relatorio.jsp").forward(request, response);
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
            request.setAttribute("mensagem", "Erro no DAO: " + ex.getMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
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
            request.getRequestDispatcher("erro.jsp").forward(request, response);
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
