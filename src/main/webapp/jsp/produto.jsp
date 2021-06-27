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
                                <h3 class="m-0"> Produto</h3>
                            </div>
                            <div class="col-sm-2">
                                <a href="Novo_Produto" class="nav-link btn btn-primary">Novo </a>
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

                            <sql:query dataSource="${conexao}" var="consulta">
                                select p.produto_id as produto_id, p.nome as produto, p.descricao as descricao, c.nome as categoria 
                                from produto p 
                                join categoria c on p.categoria_id = c.categoria_id 
                                order by p.nome
                            </sql:query>

                            <table class="table table-striped col-12">
                                <thead>
                                    <tr>
                                        <th class="col-1">#</th>
                                        <th class="col-3">Produto</th>
                                        <th class="col-3">Descrição</th>
                                        <th class="col-2">Categoria</th>
                                        <th class="col-2">Ação</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="item" items="${consulta.rows}">
                                        <tr>
                                            <td>${item.produto_id}</td>
                                            <td>${item.produto}</td>
                                            <td>${item.descricao}</td>
                                            <td>${item.categoria}</td>
                                            <td>
                                                <a  href="Produto?id=${item.produto_id}"><i style="margin:5%;" class="fas fa-edit" alt="Visualizar"></i></a> 

                                                <i style="cursor:pointer;" class="fas fa-trash-alt" alt="Excluir" onclick="excluir(${item.produto_id})"></i>
                                            </td>       
                                        </tr>
                                    </c:forEach>
                                    <c:if test="${fn: length(consulta.rows)==0}">
                                        <tr>
                                            <td colspan="5" style="text-align: center;">Nenhum item encontrado</td>
                                        </tr>
                                    </c:if>
                                </tbody>
                            </table>

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

            function excluir(id) {
                Swal.fire({
                    title: 'Deseja excluir?',
                    text: "Não será possível reverter a ação!",
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Sim!',
                    cancelButtonText: 'Não!'
                }).then((result) => {
                    if (result.isConfirmed) {
                        $.get("Excluir?tipo=produto&id=" + id, function (responseJson) {
                            if (responseJson.sucesso) {
                                Swal.fire(
                                        'Excluído!',
                                        'Registro excluído com sucesso.',
                                        'success'
                                        ).then(function () {
                                    location.href = "/SAC_V1/Produto";
                                })
                            } else {
                                Swal.fire(
                                        'Falha!',
                                        'Erro ao excluir registro, tente mais tarde.',
                                        'error'
                                        )
                            }
                        });
                    }
                })
            }
        </script>
    </body>
</html>
