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
import sac.dao.ConnectionFactory;
import sac.dao.DAOException;
import sac.dao.EnderecoDAO;
import sac.dao.EstadoDAO;
import sac.dao.PessoaDAO;
import sac.domain.Endereco;
import sac.domain.Estado;
import sac.domain.Pessoa;
import sac.domain.Usuario;
import sac.model.Estados;
import sac.util.Erro;
import sac.util.Password;

/**
 *
 * @author 
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
        sac.model.Pessoa p = new sac.model.Pessoa();

        Connection connection = ConnectionFactory.getConnection();

        if (request.getParameter("bRegistrar") != null) {
            String nome = request.getParameter("nome");
            String cpf = request.getParameter("cpf");
            String telefone = request.getParameter("telefone");
            String rua = request.getParameter("rua");
            String numero = request.getParameter("numero");
            String complemento = request.getParameter("complemento");
            String bairro = request.getParameter("bairro");
            String cep = request.getParameter("cep");
            String estado = request.getParameter("estado_id");
            String cidade = request.getParameter("cidade_id");

            Integer cidade_id = 0;
            if (!cidade.isEmpty()) {
                cidade_id = Integer.parseInt(cidade);
            }

            String email = request.getParameter("email");
            String password = request.getParameter("senha");
            String password_conf = request.getParameter("conf_senha");

            if (email == null || email.isEmpty()) {
                erros.add("'E-mail n??o informado!'");
            }
            if (password == null || password.isEmpty()) {
                erros.add("'Senha n??o informada!'");
            }
            if (password_conf == null || password_conf.isEmpty()) {
                erros.add("'Senha n??o informada!'");
            }
            if (password != null && !password.isEmpty() && password_conf != null && !password_conf.isEmpty() && !password.equals(password_conf)) {
                erros.add("'Confirma????o de senha inv??lida'");
            }
            if (nome == null || nome.isEmpty()) {
                erros.add("'Nome ?? obrigat??rio!'");
            }
            if (cpf == null || cpf.isEmpty()) {
                erros.add("'CPF ?? obrigat??rio!'");
            }
            if (telefone == null || telefone.isEmpty()) {
                erros.add("'Telefone ?? obrigat??rio!'");
            }
            if (rua == null || rua.isEmpty()) {
                erros.add("'Rua ?? obrigat??rio!'");
            }
            if (numero == null || numero.isEmpty()) {
                erros.add("'N??mero ?? obrigat??rio!'");
            }
            if (bairro == null || bairro.isEmpty()) {
                erros.add("'Bairro ?? obrigat??rio!'");
            }
            if (cep == null || cep.isEmpty()) {
                erros.add("'CEP ?? obrigat??rio!'");
            }
            if (estado == null || estado.isEmpty()) {
                erros.add("'Selecione um estado!'");
            }
            if (cidade_id == 0) {
                erros.add("'Selecione uma cidade!'");
            }

            PessoaDAO pessoaDAO = new PessoaDAO(connection);
            Usuario user = pessoaDAO.getUserByEmail(email);
            if (user != null) {
                erros.add("'E-mail j?? cadastrado'");
            }

            if (!erros.isExisteErros()) {
                Pessoa pessoa = new Pessoa(nome, cpf.replaceAll("[^0-9]", ""), telefone.replaceAll("[^0-9?!\\.]", ""), 1);

                if (user != null) {
                    pessoa.setPessoa_Id(user.getUsuario_Id());
                }

                // Generate Salt. The generated value can be stored in DB. 
                String salt = Password.getSalt(30);
                // Protect user's password. The generated value can be stored in DB.
                String mySecurePassword = Password.generateSecurePassword(password, salt);

//                user = new Usuario(email, mySecurePassword);
//                user.setPerfil_Id(1);
//                user.setKey(salt);

                pessoa.setEmail(email);
                pessoa.setSenha(mySecurePassword);
                pessoa.setKey(salt);

                EnderecoDAO enderecoDAO = new EnderecoDAO(connection);
                Endereco endereco = new Endereco(0, cidade_id, rua, numero, complemento, bairro, cep);
                pessoa.setEndereco_Id(enderecoDAO.insert(endereco));

                int id = pessoaDAO.insert(pessoa);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/Login");
                dispatcher.forward(request, response);
            } else {

                p.setBairro(bairro);
                p.setCep(cep);
                p.setComplemento(complemento);
                p.setEmail(email);
                p.setNome(nome);
                p.setNumero(numero);
                p.setRua(rua);
                p.setTelefone(telefone);
                p.setCpf(cpf);
                
                if (!estado.isEmpty()) {
                    p.setEstado_id(Integer.parseInt(estado));
                }

                p.setCidade_id(cidade_id);
            }
        }
        request.getSession().invalidate();

        request.setAttribute("mensagens", erros);
        request.setAttribute("pessoa", p);

        String action = request.getServletPath();
        if (action.equals("/Registrar")) {
            EstadoDAO estadoDAO = new EstadoDAO(connection);
            List<Estado> estados = estadoDAO.getList(1);
            Estados e = new Estados(estados);
            request.setAttribute("estados", e);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/register.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Login");
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
            Erro erros = new Erro();
            sac.model.Pessoa p = new sac.model.Pessoa();
            request.setAttribute("mensagens", erros);
            request.setAttribute("pessoa", p);

            //adicionar verifica????o de que se existe n??o precisa pesquisar de novo
            Connection connection = ConnectionFactory.getConnection();
            EstadoDAO estadoDAO = new EstadoDAO(connection);
            List<Estado> estados = estadoDAO.getList(1);
            Estados e = new Estados(estados);
            request.setAttribute("estados", e);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/register.jsp");
            dispatcher.forward(request, response);

//            processRequest(request, response);
        }  catch (DAOException ex) {
            request.setAttribute("mensagem", "Erro no DAO: " + ex.getMessage());
            request.getRequestDispatcher("/jsp/erro.jsp").forward(request, response);
        } catch (SQLException ex) {
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
        } catch (SQLException ex) {
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
