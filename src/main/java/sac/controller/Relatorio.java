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
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.openide.util.Exceptions;
import sac.dao.ConnectionFactory;
import sac.dao.DAOException;
import sac.util.Erro;

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
            throws IOException, ServletException, DAOException, ParseException {

        String action = request.getServletPath();
        Connection connection = ConnectionFactory.getConnection();

        Erro erros = new Erro();
        boolean generatePdf = false;

        String reportFileName = "";
        HashMap<String, Object> map = new HashMap<String, Object>();

        if (action.equals("/RelatorioFuncionario")) {
            //retorna pdf
            generatePdf = true;
            reportFileName = "\\Relatorio_Funcionarios.jrxml";

        } else if (action.equals("/RelatorioProduto")) {
            //retorna pdf

            generatePdf = true;
            reportFileName = "\\Relatorio_Produtos.jrxml";

        } else if (action.equals("/GerarRelatorioAtendimento")) {
            request.setAttribute("tipo", 1);
            String dataInicio = request.getParameter("dataInicio");
            String dataFim = request.getParameter("dataFim");
            String dates = request.getParameter("dates");

            if (dates == null || dates.isEmpty()) {
                erros.add("'Escolha um período!'");
            } else {
                dataInicio = dates.substring(0, 22);

                dataFim = dates.substring(25, dates.length());

                if (dataInicio == null || dataInicio.isEmpty()) {
                    erros.add("'Data inicial é obrigatória!'");
                }

                if (dataFim == null || dataFim.isEmpty()) {
                    erros.add("'Data final é obrigatória!'");
                }
            }

            if (!erros.isExisteErros()) {
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss aa");
                // you can change format of date
                Date dateInicio = formatter.parse(dataInicio);
                Timestamp timeStampDateInicio = new Timestamp(dateInicio.getTime());
                // you can change format of date
                Date dateFim = formatter.parse(dataFim);
                Timestamp timeStampDateFim = new Timestamp(dateFim.getTime());

                map.put("dataInicio", timeStampDateInicio);
                map.put("dataFim", timeStampDateFim);

                generatePdf = true;
                reportFileName = "\\Relatorio_Atendimento_Aberto.jrxml";
            }
        } else if (action.equals("/RelatorioAtendimento")) {
            //retorna tela para escolher data
            request.setAttribute("tipo", 1);
        } else if (action.equals("/GerarRelatorioReclamacao")) {
            request.setAttribute("tipo", 2);
            String situacao = request.getParameter("situacao");
            Integer situacao_ = Integer.parseInt(situacao);

            if (situacao_ == 0) {
                erros.add("'Selecione uma opção!'");
            } else {
                map.put("situacao", situacao_ == 3 ? null : situacao_);
                generatePdf = true;
                reportFileName = "\\Relatorio_Reclamacao.jrxml";
            }

        } else if (action.equals("/RelatorioReclamacao")) {
            //retorna tela para escolher situação
            request.setAttribute("tipo", 2);
        }

        if (generatePdf) {
            //retorna pdf
            JasperPrint jasperPrint = null;
            try {
                String reportPath = request.getServletContext().getRealPath("/") + reportFileName;

                String targetFileName = reportFileName.replace(".jrxml", ".pdf");
                JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
                jasperPrint = JasperFillManager.fillReport(jasperReport, map, connection);
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
        } else {
            request.setAttribute("mensagem", "");
            request.setAttribute("mensagens", erros);
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
            request.getRequestDispatcher("/jsp/erro.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("mensagem", "Erro no DAO: " + ex.getMessage());
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
            request.setAttribute("mensagem", "Erro no DAO: " + ex.getMessage());
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
