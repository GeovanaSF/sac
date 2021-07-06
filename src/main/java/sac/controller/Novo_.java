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
import sac.dao.CategoriaDAO;
import sac.dao.ConnectionFactory;
import sac.dao.DAOException;
import sac.dao.EnderecoDAO;
import sac.dao.EstadoDAO;
import sac.dao.PessoaDAO;
import sac.dao.ProdutoDAO;
import sac.domain.Categoria;
import sac.domain.Endereco;
import sac.domain.Estado;
import sac.domain.Pessoa;
import sac.domain.Produto;
import sac.domain.Usuario;
import sac.model.Categorias;
import sac.model.Estados;
import sac.util.Erro;
import sac.util.Password;

/**
 *
 * @author geova
 */
public class Novo_ extends HttpServlet {

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
            if ((action.equals("/Novo_Produto") || action.equals("/Update_Produto")) && (categoria_id == 0)) {
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
                if (action.equals("/Novo_Produto") || produto_id > 0) {
                    try {
                        ProdutoDAO daoProduto = new ProdutoDAO(connection);
                        Produto produto = new Produto(produto_id, categoria_id, nome, descricao, peso, "");
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
                String perfil = request.getParameter("perfil_id");
                String endereco_ = request.getParameter("endereco_id");
                String id_pessoa = request.getParameter("pessoa_id");

                Integer cidade = 0;
                if (cidade_id != null && !cidade_id.isEmpty()) {
                    cidade = Integer.parseInt(cidade_id);
                }

                Integer perfil_id = 0;
                if (perfil != null && !perfil.isEmpty()) {
                    perfil_id = Integer.parseInt(perfil);
                }

                Integer endereco_id = 0;
                if (endereco_ != null && !endereco_.isEmpty()) {
                    endereco_id = Integer.parseInt(endereco_);
                }

                Integer pessoa_id = 0;
                if (id_pessoa != null && !id_pessoa.isEmpty()) {
                    pessoa_id = Integer.parseInt(id_pessoa);
                }

                String email = request.getParameter("email");
                String password = request.getParameter("senha");
                String password_conf = request.getParameter("conf_senha");

                if (perfil == null || perfil.isEmpty()) {
                    erros.add("'Selecione um perfil!'");
                }
                if (nome == null || nome.isEmpty()) {
                    erros.add("'Nome é obrigatório!'");
                }
                if (cpf == null || cpf.isEmpty()) {
                    erros.add("'CPF é obrigatório!'");
                }
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

                if (email == null || email.isEmpty()) {
                    erros.add("'E-mail não informado!'");
                }
                if (password == null || password.isEmpty()) {
                    erros.add("'Senha não informada!'");
                }
                if (password_conf == null || password_conf.isEmpty()) {
                    erros.add("'Senha não informada!'");
                }
                if (password != null && !password.isEmpty() && password_conf != null && !password_conf.isEmpty() && !password.equals(password_conf)) {
                    erros.add("'Confirmação de senha inválida'");
                }

                PessoaDAO pessoaDAO = new PessoaDAO(connection);
                Usuario user = pessoaDAO.getUserByEmail(email);
                if (user != null && pessoa_id == 0) {
                    erros.add("'E-mail já cadastrado'");
                }

                if (!erros.isExisteErros()) {
                    Pessoa pessoa = new Pessoa(nome, cpf.replaceAll("[^0-9]", ""), telefone.replaceAll("[^0-9?!\\.]", ""), perfil_id);

                    if (user != null) {
                        pessoa.setPessoa_Id(user.getUsuario_Id());
                    }
                    if (password != null && !password.isEmpty() && password_conf != null && !password_conf.isEmpty()) {
                        // Generate Salt. The generated value can be stored in DB. 
                        String salt = Password.getSalt(30);
                        // Protect user's password. The generated value can be stored in DB.
                        String mySecurePassword = Password.generateSecurePassword(password, salt);
                        pessoa.setEmail(email);
                        pessoa.setSenha(mySecurePassword);
                        pessoa.setKey(salt);

                        if (pessoa_id != 0) {
                            pessoaDAO.updateSenha(pessoa);
                        }
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
                        pessoaDAO.update(pessoa);

                    }

                    RequestDispatcher dispatcher = request.getRequestDispatcher("/Funcionario");
                    dispatcher.forward(request, response);
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
                    p.setPerfil_Id(perfil_id);
                    p.setCpf(cpf);
                    p.setSenha("");
                    if (!estado.isEmpty()) {
                        p.setEstado_id(Integer.parseInt(estado));
                    }

                    p.setCidade_id(cidade);
                }
            } catch (SQLException ex) {
            request.setAttribute("mensagem", "Erro no DAO: " + ex.getMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
            return;
            }
        }

        if (url.isEmpty()) {
            if (action.equals("/Novo_Categoria")) {
                url = "/jsp/novo_categoria.jsp";
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
            } else if (action.equals("/Novo_Funcionario")) {
                try {
                    EstadoDAO estadoDAO = new EstadoDAO(connection);
                    List<Estado> estados = estadoDAO.getList(1);
                    Estados e = new Estados(estados);
                    request.setAttribute("estados", e);

                } catch (SQLException ex) {
                    Logger.getLogger(Novo.class.getName()).log(Level.SEVERE, null, ex);
                }
                url = "/jsp/novo_funcionario.jsp";
            }
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
