<%-- 
    Document   : novo_funcionario
    Created on : 03/06/2021, 20:55:37
    Author     : geova
--%>

<%@page import="sac.util.Erro"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
    <head>        
        <jsp:include page="header.jsp" />
        <title>SAC - Funcionários/Gerentes</title>
    </head>
    <body class="hold-transition layout-top-nav">

        <div class="wrapper">
            <!-- Navbar -->
            <nav class="main-header navbar navbar-expand-md navbar-light navbar-white">
                <div class="container">
                    <a href="" class="navbar-brand">
                        <span class="brand-text font-weight-light">SAC</span>
                    </a>

                    <div class="collapse navbar-collapse order-3" id="navbarCollapse">
                        <!-- Left navbar links -->
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                <a href="Dashboard" class="nav-link">Home</a>
                            </li>
                            <jsp:useBean id="usuarioLogado" class="sac.domain.Usuario" scope="session"/>
                            <c:if test="${usuarioLogado.perfil_Id == 1}"> 
                                <li class="nav-item">
                                    <a href="AlterarDados" class="nav-link">Alteração de dados</a>
                                </li> 
                            </c:if>
                            <c:if test="${usuarioLogado.perfil_Id == 1}"> 
                                <li class="nav-item">
                                    <a href="MeusAtendimentos" class="nav-link">Meus atendimentos</a>
                                </li>
                            </c:if>
                            <c:if test="${usuarioLogado.perfil_Id != 1}" var="teste"> 
                                <li class="nav-item">
                                    <a href="TodosAtendimentosAberto" class="nav-link">Atendimentos em aberto</a>
                                </li>
                                <li class="nav-item">
                                    <a href="TodosAtendimentos" class="nav-link">Todos Atendimentos</a>
                                </li>
                            </c:if>
                            <c:if test="${usuarioLogado.perfil_Id != 1}"> 
                                <li class="nav-item dropdown">
                                    <a id="dropdownSubMenu1" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link dropdown-toggle ignore-click">Cadastros</a>
                                    <ul aria-labelledby="dropdownSubMenu1" class="dropdown-menu border-0 shadow">
                                        <c:if test="${usuarioLogado.perfil_Id == 2}"> 
                                            <li><a href="Categoria" class="dropdown-item">Categorias</a></li>
                                            <li><a href="Produto" class="dropdown-item">Produtos</a></li>
                                            </c:if>
                                            <c:if test="${usuarioLogado.perfil_Id == 3}"> 
                                            <li><a href="Funcionario" class="dropdown-item">Funcionários/Gerentes</a></li>
                                            </c:if>
                                    </ul>
                                </li>
                            </c:if>
                            <c:if test="${usuarioLogado.perfil_Id == 3}"> 
                                <li class="nav-item dropdown">
                                    <a id="dropdownSubMenu1" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link dropdown-toggle ignore-click">Relatórios</a>
                                    <ul aria-labelledby="dropdownSubMenu1" class="dropdown-menu border-0 shadow">
                                        <li><a href="#" class="dropdown-item">Funcionários</a></li>
                                        <li><a href="#" class="dropdown-item">Produtos mais reclamados</a></li>
                                        <li><a href="#" class="dropdown-item">Atendimentos abertos</a></li>
                                        <li><a href="#" class="dropdown-item">Reclamações</a></li>
                                    </ul>
                                </li>
                            </c:if>
                        </ul>

                    </div>

                    <!-- Right navbar links -->
                    <ul class="order-1 order-md-3 navbar-nav navbar-no-expand ml-auto">
                        <!-- Messages Dropdown Menu -->
                        <li class="nav-item dropdown">
                            <a class="nav-link ignore-click" data-toggle="dropdown" href="">
                                <i class="fas fa-user"></i>
                                <span class="badge badge-danger navbar-badge"></span>
                            </a>
                            <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">                   
                                <div class="dropdown-divider"></div>
                                <a href="Logout" class="dropdown-item dropdown-footer">Logout</a>
                            </div>
                        </li>                
                    </ul>
                </div>
            </nav>
            <!-- /.navbar -->


            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <div class="content-header">
                    <div class="container">
                        <div class="row mb-2">
                            <div class="col-sm-10">
                                <h3 class="m-0"> Funcionários/Gerentes</h3>
                            </div>
                            <div class="col-sm-2">
                                <a href="Funcionario" class="nav-link btn btn-default">Voltar</a>
                            </div>
                        </div><!-- /.row -->
                    </div><!-- /.container-fluid -->
                </div>
                <!-- /.content-header -->

                <!-- Main content -->
                <div class="content">
                    <div class="container">
                        <form class="row" action="Novo_Funcionario" method="post">

                            <jsp:useBean id="pessoa" class="sac.model.Pessoa" scope="request">
                                <jsp:setProperty name="pessoa" property="*" />
                            </jsp:useBean>

                            <input type="hidden" id="pessoa_id" name="pessoa_id" value="${pessoa.pessoa_id}">
                            <input type="hidden" id="endereco_id" name="endereco_id" value="${pessoa.endereco_id}">

                            <div class="input-group mb-1 col-12">
                                <div class="custom-control custom-radio col-2">
                                    <input class="custom-control-input" type="radio" id="perfil_gerente" name="perfil_id" value="3" ${pessoa.perfil_Id == 3 ? "checked" : ""}>
                                    <label for="perfil_gerente" class="custom-control-label">Gerente</label>
                                </div>
                                <div class="custom-control custom-radio col-2">
                                    <input class="custom-control-input" type="radio" id="perfil_funcionario" name="perfil_id" value="2"${pessoa.perfil_Id == 2 ? "checked" : ""}>
                                    <label for="perfil_funcionario" class="custom-control-label">Funcionário</label>
                                </div>
                            </div>

                            <div class="input-group mb-1 col-12">
                                <input type="text" class="form-control" id="nome" name="nome" placeholder="Nome completo" value="${pessoa.nome}">
                                <div class="input-group-append">
                                    <div class="input-group-text">
                                        <span class="fas fa-user"></span>
                                    </div>
                                </div>
                            </div>

                            <div class="input-group mb-1 col-6">
                                <input class="form-control" type="text" id="cpf" name="cpf" placeholder="CPF" data-inputmask='"mask": "999.999.999-99"' data-mask value="${pessoa.cpf}">
                                <div class="input-group-append">
                                    <div class="input-group-text">
                                        <span class="far fa-id-card"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="input-group mb-1 col-6">
                                <input class="form-control" type="text" id="telefone" name="telefone" placeholder="Telefone" data-inputmask='"mask": "(99) 99999-9999"' data-mask value="${pessoa.telefone}">
                                <div class="input-group-append">
                                    <div class="input-group-text">
                                        <span class="fas fa-phone"></span>
                                    </div>
                                </div>
                            </div>

                            <div class="input-group mb-1 col-9">
                                <input class="form-control" type="text" id="rua" name="rua" placeholder="Rua" value="${pessoa.rua}">
                                <div class="input-group-append">
                                    <div class="input-group-text">
                                        <span class="far fa-address-book"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="input-group mb-1 col-3">
                                <input class="form-control" type="text" id="numero" name="numero" placeholder="Número" value="${pessoa.numero}">
                                <div class="input-group-append">
                                    <div class="input-group-text">
                                        <span class="far fa-address-book"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="input-group mb-1 col-3">
                                <input class="form-control" type="text" id="complemento" name="complemento" placeholder="Complemento" value="${pessoa.complemento}">
                                <div class="input-group-append">
                                    <div class="input-group-text">
                                        <span class="far fa-address-book"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="input-group mb-1 col-5">
                                <input class="form-control" type="text" id="bairro" name="bairro" placeholder="Bairro" value="${pessoa.bairro}">
                                <div class="input-group-append">
                                    <div class="input-group-text">
                                        <span class="far fa-address-book"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="input-group mb-1 col-4">
                                <input class="form-control" type="text" id="cep" name="cep" placeholder="CEP" data-inputmask='"mask": "99999-999"' data-mask value="${pessoa.cep}">
                                <div class="input-group-append">
                                    <div class="input-group-text">
                                        <span class="far fa-address-book"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="input-group mb-1 col-6">

                                <jsp:useBean id="estados" class="sac.model.Estados" scope="request"/>

                                <select class="custom-select form-control-border" id="estado_id" name="estado_id" value="${pessoa.estado_id}" placeholder="Estado">
                                    <c:if test="${pessoa.estado_id == 0}">
                                        <option selected="selected" value="">Selecione o estado</option>    
                                    </c:if>
                                    <c:if test="${pessoa.estado_id != 0}">
                                        <option value="">Selecione o estado</option>    
                                    </c:if>
                                    <c:forEach var="estado" items="${estados.getEstados()}">
                                        <option ${pessoa.estado_id == estado.estado_id ? "selected" : ""} value="${estado.estado_id}">${estado.nome}</option>
                                    </c:forEach>
                                </select>

                            </div>
                            <div class="input-group mb-1 col-6">
                                <select class="custom-select form-control-border" id="cidade_id" name="cidade_id" value="${pessoa.cidade_id}" placeholder="Cidade">
                                    <c:if test="${estados.getCidades().size() == 0}">
                                        <option selected="selected" value="">Selecione a cidade</option>    
                                    </c:if>
                                    <c:if test="${pessoa.cidade_id != 0}">
                                        <option value="">Selecione a cidade</option>    
                                    </c:if>
                                    <c:forEach var="cidade" items="${estados.getCidades()}">
                                        <option ${pessoa.cidade_id == cidade.cidade_id ? "selected" : ""} value="${cidade.cidade_id}">${cidade.nome}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="input-group mb-1 col-12">
                                <input type="email" class="form-control" id="email" name="email" placeholder="Email" value="${pessoa.email}">
                                <div class="input-group-append">
                                    <div class="input-group-text">
                                        <span class="fas fa-envelope"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="input-group mb-1 col-6">
                                <input type="password" class="form-control" id="senha" name="senha" placeholder="Senha" value="${pessoa.senha}">
                                <div class="input-group-append">
                                    <div class="input-group-text">
                                        <span class="fas fa-lock"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="input-group mb-1 col-6">
                                <input type="password" class="form-control" id="conf_senha" name="conf_senha" placeholder="Confirmação senha" value="${pessoa.senha}">
                                <div class="input-group-append">
                                    <div class="input-group-text">
                                        <span class="fas fa-lock"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-10"></div>
                            <div class="col-2">
                                <button type="submit" name="bSalvar" value="salvar" class="btn btn-primary btn-block">Salvar</button>
                            </div>
                        </form>
                        <!-- /.row -->
                    </div><!-- /.container-fluid -->
                </div>
                <!-- /.content -->
            </div>
            <!-- /.content-wrapper -->

        </div>
        <!-- ./wrapper -->


        <jsp:include page="footer_scripts.jsp" />
        
        <script type="text/javascript">
            $(function () {
                $('[data-mask]').inputmask();

            <%
                Erro mensagens = (Erro) request.getAttribute("mensagens");
            %>
                var existe = ${mensagens.isExisteErros()};
                var mensagens = ${mensagens.getErros()};
                if (existe && mensagens.length > 0)
                {
                    $.each(mensagens, function (i, el) {
                        toastr.error(el)
                    })
                }

                $("#estado_id").on("change", function () {
                    var id = $(this).find(":selected").val();

                    $.get("Cidade?estado_id=" + id, function (responseJson) {
                        var $select = $("#cidade_id");
                        $select.find("option").remove()
                        $("<option>").val("").text("Selecione a cidade").appendTo($select);

                        $.each(responseJson, function (index, el) {
                            $("<option>").val(el.cidade_id).text(el.nome).appendTo($select);
                        });
                    });
                });
            });
        </script>
    </body>
</html>