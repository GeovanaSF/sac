<%@page import="sac.util.Erro"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
    <head>        
        <jsp:include page="header.jsp" />
        <title>SAC - Categoria</title>
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
                                    <a href="AlterarDados" class="nav-link">Altera��o de dados</a>
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
                                            <li><a href="Funcionario" class="dropdown-item">Funcion�rios/Gerentes</a></li>
                                            </c:if>
                                    </ul>
                                </li>
                            </c:if>
                            <c:if test="${usuarioLogado.perfil_Id == 3}"> 
                                <li class="nav-item dropdown">
                                    <a id="dropdownSubMenu1" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link dropdown-toggle ignore-click">Relat�rios</a>
                                    <ul aria-labelledby="dropdownSubMenu1" class="dropdown-menu border-0 shadow">
                                        <li><a href="RelatorioFuncionario" class="dropdown-item">Funcion�rios</a></li>
                                        <li><a href="RelatorioProduto" class="dropdown-item">Produtos mais reclamados</a></li>
                                        <li><a href="RelatorioAtendimento" class="dropdown-item">Atendimentos abertos</a></li>
                                        <li><a href="RelatorioReclamacao" class="dropdown-item">Reclama��es</a></li>
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
                                <h3 class="m-0"> Categoria</h3>
                            </div>
                            <div class="col-sm-2">
                                <a href="Categoria" class="nav-link btn btn-default">Voltar</a>
                            </div>
                        </div><!-- /.row -->
                    </div><!-- /.container-fluid -->
                </div>
                <!-- /.content-header -->

                <!-- Main content -->
                <div class="content">
                    <div class="container">
                        <form class="" action="Novo_Categoria" method="post">
                            <div class="row">
                                <jsp:useBean id="cadastro" class="sac.model.Cadastro" scope="request"/>
                                <div class="input-group mb-1 col-10">
                                    <input type="hidden" id="categoria_id" name="categoria_id" value="${cadastro.categoria_id}">
                                    <input type="text" class="form-control" id="nome" name="nome" placeholder="Nome categoria" value="${cadastro.nome}">
                                    <!--                                    <div class="input-group-append">
                                                                            <div class="input-group-text">
                                                                                <span class="fas fa-user"></span>
                                                                            </div>
                                                                        </div>-->
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
