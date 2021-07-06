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
import sac.dao.CidadeDAO;
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
 * @author geova
 */
public class AlterarDados extends HttpServlet {

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
        Usuario user = (Usuario) request.getSession().getAttribute("usuarioLogado");
        if (user == null) {
            request.getSession().invalidate();
            response.sendRedirect("/SAC_V1/Login");
            return;
        }

        Erro erros = new Erro();
        sac.model.Cadastro cadastro = new sac.model.Cadastro();
        sac.model.Pessoa p = new sac.model.Pessoa();
        Connection connection = ConnectionFactory.getConnection();
        String url = "/jsp/alteracao_dados.jsp";
        if (request.getParameter("bSalvar") != null) {
            try {
                String nome = request.getParameter("nome");
                String cpf = request.getParameter("cpf");
                String telefone = request.getParameter("telefone");
                String rua = request.getParameter("rua");
                String numero = request.getParameter("numero");
                String complemento = request.getParameter("complemento");
                String bairro = request.getParameter("bairro");
                String cep = request.getParameter("cep");
                String estado = request.getParameter("estado_id");
                String cidade_id = request.getParameter("cidade_id");
                String endereco_ = request.getParameter("endereco_id");
                String id_pessoa = request.getParameter("pessoa_id");

                Integer cidade = 0;
                if (!cidade_id.isEmpty()) {
                    cidade = Integer.parseInt(cidade_id);
                }

                Integer endereco_id = 0;
                if (!endereco_.isEmpty()) {
                    endereco_id = Integer.parseInt(endereco_);
                }

                Integer pessoa_id = 0;
                if (!id_pessoa.isEmpty()) {
                    pessoa_id = Integer.parseInt(id_pessoa);
                }

                String email = request.getParameter("email");
                String password = request.getParameter("senha");
                String password_conf = request.getParameter("conf_senha");

                if (nome == null || nome.isEmpty()) {
                    erros.add("'Nome é obrigatório!'");
                }
//                if (cpf == null || cpf.isEmpty()) {
//                    erros.add("'CPF é obrigatório!'");
//                }
                if (telefone == null || telefone.isEmpty()) {
                    erros.add("'Telefone é obrigatório!'");
                }
                if (rua == null || rua.isEmpty()) {
                    erros.add("'Rua é obrigatório!'");
                }
                if (numero == null || numero.isEmpty()) {
                    erros.add("'Número é obrigatório!'");
                }
                if (bairro == null || bairro.isEmpty()) {
                    erros.add("'Bairro é obrigatório!'");
                }
                if (cep == null || cep.isEmpty()) {
                    erros.add("'CEP é obrigatório!'");
                }
                if (estado == null || estado.isEmpty()) {
                    erros.add("'Selecione um estado!'");
                }
                if (cidade == 0) {
                    erros.add("'Selecione uma cidade!'");
                }

//                if (email == null || email.isEmpty()) {
//                    erros.add("'E-mail não informado!'");
//                }
//                if (password == null || password.isEmpty()) {
//                    erros.add("'Senha não informada!'");
//                }
//                if (password_conf == null || password_conf.isEmpty()) {
//                    erros.add("'Confirmação de senha não informada!'");
//                }

                if (password != null && !password.isEmpty() && password_conf != null && !password_conf.isEmpty() && !password.equals(password_conf)) {
                    erros.add("'Confirmação de senha inválida'");
                }

                if (!erros.isExisteErros()) {
                    PessoaDAO pessoaDAO = new PessoaDAO(connection);
                    Pessoa pessoa = new Pessoa(pessoa_id, nome, telefone.replaceAll("[^0-9?!\\.]", ""));

                    if (password != null && !password.isEmpty() && password_conf != null && !password_conf.isEmpty()) {
                        //TODO: PRECISA FAZER O UPDATE DA SENHA
                        // Generate Salt. The generated value can be stored in DB. 
                        String salt = Password.getSalt(30);
                        // Protect user's password. The generated value can be stored in DB.
                        String mySecurePassword = Password.generateSecurePassword(password, salt);

                        pessoa.setEmail(email);
                        pessoa.setSenha(mySecurePassword);
                        pessoa.setKey(salt);

                        pessoaDAO.updateSenha(pessoa);
                    }

                    EnderecoDAO enderecoDAO = new EnderecoDAO(connection);
                    Endereco endereco = new Endereco(endereco_id, cidade, rua, numero, complemento, bairro, cep);
                    if (endereco_id == 0) {
                        endereco_id = enderecoDAO.insert(endereco);
                    } else {
                        enderecoDAO.update(endereco);
                    }

                    pessoa.setEndereco_Id(endereco_id);

                    if (pessoa_id == 0) {
                        pessoa_id = pessoaDAO.insert(pessoa);
                    } else {
                        pessoa.setPessoa_Id(pessoa_id);
                        pessoaDAO.updateDados(pessoa);
                    }
                    url = "/Dashboard";
                } else {
                    p.setPessoa_id(pessoa_id);
                    p.setEndereco_id(endereco_);
                    p.setBairro(bairro);
                    p.setCep(cep);
                    p.setComplemento(complemento);
                    p.setEmail(email);
                    p.setNome(nome);
                    p.setNumero(numero);
                    p.setRua(rua);
                    p.setTelefone(telefone);
                    if (!estado.isEmpty()) {
                        p.setEstado_id(Integer.parseInt(estado));
                    }

                    p.setCidade_id(cidade);
                }
            } catch (SQLException ex) {
            request.setAttribute("mensagem", "Erro no DAO: " + ex.getMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
            }
        }

        try {
            PessoaDAO pessoaDAO = new PessoaDAO(connection);
            p = pessoaDAO.getPessoaByUserId(user.getUsuario_Id());
            if (p != null) {
                p.setSenha("");

                EstadoDAO estadoDAO = new EstadoDAO(connection);
                List<Estado> estados = estadoDAO.getList(1);
                Estados e = new Estados(estados);
                request.setAttribute("estados", e);

                CidadeDAO cidadeDAO = new CidadeDAO(connection);
                List<sac.domain.Cidade> cidades = cidadeDAO.getList(p.getEstado_id().toString());
                e.setCidades(cidades);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlterarDados.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("mensagens", erros);
        request.setAttribute("cadastro", cadastro);
        request.setAttribute("pessoa", p);

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
