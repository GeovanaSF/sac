<%-- 
    Document   : novo_categoria
    Created on : 03/06/2021, 20:54:59
    Author     : geova
--%>

<%@page import="sac.util.Erro"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
    <head>        
        <jsp:include page="header.jsp" />
        <title>SAC - Produto</title>
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
                                <h3 class="m-0"> Produto</h3>
                            </div>
                            <div class="col-sm-2">
                                <a href="Produto" class="nav-link btn btn-default">Voltar</a>
                            </div>
                        </div><!-- /.row -->
                    </div><!-- /.container-fluid -->
                </div>
                <!-- /.content-header -->

                <!-- Main content -->
                <div class="content">
                    <div class="container">
                        <jsp:useBean id="cadastro" class="sac.model.Cadastro" scope="request">
                            <jsp:setProperty name="cadastro" property="*" />
                        </jsp:useBean>
                        <form class="" action="Novo_Produto" method="post">
                            <div class="row">
                                <div class="col-5"><!-- comment -->
                                    <!-- comment -->
                                    <div class="input-group mb-1 col-12">
                                        <input type="hidden" id="categoria_id" name="produto_id" value="${cadastro.produto_id}">
                                        <input type="text" class="form-control" id="nome" name="nome" placeholder="Nome produto" value="${cadastro.nome}">

                                    </div>
                                    <div class="input-group mb-1 col-12">
                                        <input type="text" class="form-control" id="peso" name="peso" placeholder="Peso" value="${cadastro.peso==0 ? "" : cadastro.peso}">
                                    </div>
                                </div>
                                <div class="input-group mb-1 col-5">
                                    <textarea class="form-control" rows="2" style="resize: none;" id="descricao" name="descricao" placeholder="Descrição" value="${cadastro.descricao}">${cadastro.descricao}</textarea>
                                </div>

                                <div class="input-group mb-1 col-5">
                                    <jsp:useBean id="categorias" class="sac.model.Categorias" scope="request"/>

                                    <select class="custom-select" id="categoria_id" name="categoria_id" value="${cadastro.categoria_id}" placeholder="Categoria" style="margin: auto 2%;">
                                        <c:if test="${cadastro.categoria_id == 0}">
                                            <option >Selecione categoria</option>    
                                        </c:if>
                                        <c:if test="${cadastro.categoria_id != 0}">
                                            <option value="">Selecione categoria</option>    
                                        </c:if>
                                        <c:forEach var="categoria" items="${categorias.getCategorias()}">
                                            <option ${categoria.categoria_id==cadastro.categoria_id ? "selected": ""} value="${categoria.categoria_id}">${categoria.nome}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="input-group mb-1 col-5">
                                </div>
                                <div class="col-2">
                                    <button type="submit" name="bRegistrar" value="registrar" class="btn btn-primary btn-block">Salvar</button>
                                </div>
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
