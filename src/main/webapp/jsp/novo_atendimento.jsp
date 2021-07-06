<%-- 
    Document   : novo_atendimento
    Created on : 03/06/2021, 20:54:46
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
        <title>SAC - Atendimento</title>
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
                                        <li><a href="RelatorioFuncionario" class="dropdown-item">Funcionários</a></li>
                                        <li><a href="RelatorioProduto" class="dropdown-item">Produtos mais reclamados</a></li>
                                        <li><a href="RelatorioAtendimento" class="dropdown-item">Atendimentos abertos</a></li>
                                        <li><a href="RelatorioReclamacao" class="dropdown-item">Reclamações</a></li>
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
                                <h3 class="m-0"> Atendimento</h3>
                            </div>
                            <div class="col-sm-2">
                                <a href="MeusAtendimentos" class="nav-link btn btn-default">Voltar</a>
                            </div>
                        </div><!-- /.row -->
                    </div><!-- /.container-fluid -->
                </div>
                <!-- /.content-header -->

                <!-- Main content -->
                <div class="content">
                    <div class="container">
                        <form class="row" action="Novo_Atendimento" method="post">

                            <jsp:useBean id="atendimento" class="sac.domain.Atendimento" scope="request">
                                <jsp:setProperty name="atendimento" property="*" />
                            </jsp:useBean>

                            <input type="hidden" id="atendimento_id" name="atendimento_id" value="${atendimento.atendimento_id}">
                            <div class="input-group mb-1 col-6">

                                <jsp:useBean id="produtos" class="sac.model.Produtos" scope="request"/>

                                <select class="custom-select form-control-border" id="produto_id" name="produto_id" value="${atendimento.produto_id}" placeholder="Selecione o produto" ${usuarioLogado.perfil_Id != 1 || atendimento.situacao == 2? "disabled" : ""}>
                                    <c:if test="${atendimento.produto_id == 0}">
                                        <option selected="selected">Selecione o produto</option>    
                                    </c:if>
                                    <c:if test="${atendimento.produto_id != 0}">
                                        <option>Selecione o produto</option>    
                                    </c:if>
                                    <c:forEach var="produto" items="${produtos.getProdutos()}">
                                        <option ${atendimento.produto_id == produto.produto_id ? "selected" : ""} value="${produto.produto_id}">${produto.nome}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="input-group mb-1 col-6">
                                <jsp:useBean id="tipoatendimentos" class="sac.model.TipoAtendimentos" scope="request"/>

                                <select class="custom-select form-control-border" id="tipoatendimento_id" name="tipoatendimento_id" value="${atendimento.tipoatendimento_id}" placeholder="Tipo de atendimento" ${usuarioLogado.perfil_Id != 1 || atendimento.situacao == 2? "disabled" : ""}>
                                    <c:if test="${atendimento.tipoatendimento_id == 0}">
                                        <option selected="selected">Selecione o tipo de atendimento</option>    
                                    </c:if>
                                    <c:if test="${atendimento.tipoatendimento_id != 0}">
                                        <option>Selecione o produto</option>    
                                    </c:if>
                                    <c:forEach var="tipo" items="${tipoatendimentos.getTipoAtendimentos()}">
                                        <option ${atendimento.tipoatendimento_id == tipo.tipoAtendimento_id ? "selected" : ""} value="${tipo.tipoAtendimento_id}">${tipo.nome}</option>    
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="input-group mb-1 col-12">
                                <textarea class="form-control" rows="2" style="resize: none;" id="descricao" name="descricao" placeholder="Descrição" ${usuarioLogado.perfil_Id != 1 || atendimento.situacao == 2? "readonly" : ""}>${atendimento.descricao}</textarea>
                            </div>

                            <div class="input-group mb-1 col-12">
                                <textarea class="form-control" rows="2" style="resize: none;" id="solucao" name="solucao" placeholder="Solucao" ${usuarioLogado.perfil_Id != 2 || atendimento.situacao == 2 ? "readonly" : ""}>${atendimento.solucao}</textarea>
                            </div>

                            <div class="col-10"></div>
                            <div class="col-2 ${atendimento.situacao == 2 ? "hidden" : ""}">
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
            });
        </script>
    </body>
</html>