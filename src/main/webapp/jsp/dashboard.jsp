<%-- 
    Document   : login
    Created on : 03/06/2021, 16:48:09
    Author     : geova
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
    <head>        
        <jsp:include page="header.jsp" />
        <title>SAC - Dashboard</title>
    </head>
    <body class="hold-transition layout-top-nav">

        <div class="wrapper">
            <!-- Navbar -->
            <nav class="main-header navbar navbar-expand-md navbar-light navbar-white">
                <div class="container">
                    <a href="Home" class="navbar-brand">
                        <span class="brand-text font-weight-light">SAC</span>
                    </a>

                    <div class="collapse navbar-collapse order-3" id="navbarCollapse">
                        <!-- Left navbar links -->
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                <a href="Home" class="nav-link">Home</a>
                            </li>
                            <jsp:useBean id="usuarioLogado" class="sac.domain.Usuario" scope="session"/>
                            <c:if test="${usuarioLogado.perfil_Id == 1}"> 
                                <li class="nav-item">
                                    <a href="AlterarDados" class="nav-link">Alteração de dados</a>
                                </li> 
                            </c:if>
                            <c:if test="${usuarioLogado.perfil_Id == 1}"> 
                                <li class="nav-item">
                                    <a href="NovoAtendimento" class="nav-link">Novo atendimento</a>
                                </li>
                            </c:if>
                            <c:if test="${usuarioLogado.perfil_Id != 1}" var="teste"> 
                                <li class="nav-item">
                                    <a href="Atendimentos/EmAberto" class="nav-link">Atendimentos em aberto</a>
                                </li>
                            </c:if>
                            <li class="nav-item">
                                <a href="Atendimentos" class="nav-link">Todos Atendimentos</a>
                            </li>
                            <c:if test="${usuarioLogado.perfil_Id != 1}"> 
                                <li class="nav-item dropdown">
                                    <a id="dropdownSubMenu1" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link dropdown-toggle">Cadastros</a>
                                    <ul aria-labelledby="dropdownSubMenu1" class="dropdown-menu border-0 shadow">
                                        <c:if test="${usuarioLogado.perfil_Id == 2}"> 
                                            <li><a href="Novo/Categoria" class="dropdown-item">Categorias</a></li>
                                            <li><a href="Novo/Produto" class="dropdown-item">Produtos</a></li>
                                            </c:if>
                                            <c:if test="${usuarioLogado.perfil_Id == 3}"> 
                                            <li><a href="Novo/Funcionario" class="dropdown-item">Funcionários/Gerentes</a></li>
                                            </c:if>
                                    </ul>
                                </li>
                            </c:if>
                            <c:if test="${usuarioLogado.perfil_Id == 3}"> 
                                <li class="nav-item dropdown">
                                    <a id="dropdownSubMenu1" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link dropdown-toggle">Relatórios</a>
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
                            <a class="nav-link" data-toggle="dropdown" href="#">
                                <i class="fas fa-user"></i>
                                <span class="badge badge-danger navbar-badge"></span>
                            </a>
                            <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">                   
                                <div class="dropdown-divider"></div>
                                <a href="#" class="dropdown-item dropdown-footer">Logout</a>
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
                            <div class="col-sm-6">
                                <h3 class="m-0"> Dashboard</h3>
                            </div>
                        </div><!-- /.row -->
                    </div><!-- /.container-fluid -->
                </div>
                <!-- /.content-header -->

                <!-- Main content -->
                <div class="content">
                    <div class="container">
                        <div class="row">

                            <sql:setDataSource var="conexao" driver="org.postgresql.Driver" url="jdbc:postgresql://localhost:5432/db_sac" user="sac_user" password="sac_123" />
                            <!--CLIENTE: LISTA TODOS OS ATENDIMENTOS-->
                            <c:if test="${usuarioLogado.perfil_Id == 1}"> 
                                <sql:query dataSource="${conexao}" var="consulta">
                                    select atendimento_id,datacriacao,datafinalizacao,produto_id,descricao from atendimento where cliente_id=${usuarioLogado.usuario_Id} order by dataCriacao
                                </sql:query>

                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th style="width: 10px">#</th>
                                            <th>Data Criação</th>
                                            <th>Data Resolução</th>
                                            <th>Produto</th>
                                            <th>Descrição</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="item" items="${consulta.rows}">
                                            <tr>
                                                <td>${item.atendimento_id}</td>
                                                <td>${item.datacriacao}</td>
                                                <td>${item.datafinalizacao}</td>
                                                <td>${item.produto_id}</td>
                                                <td>${item.descricao}</td>       
                                            </tr>
                                        </c:forEach>
                                        <c:if test="${fn: length(consulta.rows)==0}">
                                            <tr>
                                                <td colspan="5" style="text-align: center;">Nenhum item encontrado</td>
                                            </tr>
                                        </c:if>
                                    </tbody>
                                </table>
                            </c:if>

                            <!--FUNCIONARIO: LISTA TODOS OS ATENDIMENTOS NÃO RESOLVIDOS-->
                            <c:if test="${usuarioLogado.perfil_Id == 2}"> 
                                <sql:query dataSource="${conexao}" var="consulta">
                                    select atendimento_id,datacriacao,datafinalizacao,produto_id,descricao from atendimento where datafinalizacao is null order by dataCriacao
                                </sql:query>

                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th style="width: 10px">#</th>
                                            <th>Data Criação</th>
                                            <th>Data Resolução</th>
                                            <th>Produto</th>
                                            <th>Descrição</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="item" items="${consulta.rows}">
                                            <tr>
                                                <td>${item.atendimento_id}</td>
                                                <td>${item.datacriacao}</td>
                                                <td>${item.datafinalizacao}</td>
                                                <td>${item.produto_id}</td>
                                                <td>${item.descricao}</td>       
                                            </tr>
                                        </c:forEach>
                                        <c:if test="${fn: length(consulta.rows)==0}">
                                            <tr>
                                                <td colspan="5" style="text-align: center;">Nenhum item encontrado</td>
                                            </tr>
                                        </c:if>
                                    </tbody>
                                </table>
                            </c:if>
                            <!--GERENTE: INFORMA QUANTIDADE DE ATENDIMENTOS EFETUADOS; QTD DE ATEND ABERTOS COM PERCENTAGEM EM RELAÇÃO AO TOTAL; SEPARADOS POR CATEGORIA-->
                            <c:if test="${usuarioLogado.perfil_Id == 3}"> 
                                mais complicado a listagem
                            </c:if>
                        </div>
                        <!-- /.row -->
                    </div><!-- /.container-fluid -->
                </div>
                <!-- /.content -->
            </div>
            <!-- /.content-wrapper -->

        </div>
        <!-- ./wrapper -->


        <jsp:include page="footer_scripts.jsp" />
    </body>
</html>
