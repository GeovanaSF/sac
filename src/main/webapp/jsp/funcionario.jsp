<%@page import="sac.util.Erro"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
    <head>        
        <jsp:include page="header.jsp" />
        <title>SAC - Funcion�rios/Gerentes</title>
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
                                <h3 class="m-0"> Funcion�rios/Gerentes</h3>
                            </div>
                            <div class="col-sm-2">
                                <a href="Novo_Funcionario" class="nav-link btn btn-primary">Novo </a>
                            </div>
                        </div><!-- /.row -->
                    </div><!-- /.container-fluid -->
                </div>
                <!-- /.content-header -->

                <!-- Main content -->
                <div class="content">
                    <div class="container">
                        <div class="row">
                            <jsp:useBean id="novo" class="sac.model.Novo" scope="request"/>
                            <!--GERENTE: LISTA TODOS OS FUNCIONARIOS-->


                            <table class="table table-striped col-12">
                                <thead>
                                    <tr>
                                        <th class="col-1">#</th>
                                        <th class="col-2">Nome</th>
                                        <th class="col-2">E-mail</th>
                                        <th class="col-2">Perfil</th>
                                        <th class="col-2">A��o</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="item" items="${novo.getFuncionarios()}">
                                        <tr>
                                            <td>${item.pessoa_id}</td>
                                            <td>${item.nome}</td>
                                            <td>${item.email}</td>
                                            <td>${item.perfil}</td>
                                            <td>
                                                <a  href="Funcionario?id=${item.pessoa_id}">
                                                    <i style="margin:5%;" class="fas fa-edit" alt="Editar" data-toggle="tooltip" data-placement="top" title="Editar funcion�rio"></i>
                                                </a> 

                                                <c:if test="${item.pessoa_id != usuarioLogado.usuario_Id}">
                                                    <i style="cursor:pointer;" class="fas fa-trash-alt" alt="Excluir" onclick="excluir(${item.pessoa_id})" data-toggle="tooltip" data-placement="top" title="Excluir funcion�rio"></i>
                                                </c:if>

                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <c:if test="${novo.getFuncionarios().size()==0}">
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
                $('[data-toggle="tooltip"]').tooltip();

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
                    text: "N�o ser� poss�vel reverter a a��o!",
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Sim!',
                    cancelButtonText: 'N�o!'
                }).then((result) => {
                    if (result.isConfirmed) {
                        $.get("Excluir?tipo=funcionario&id=" + id, function (responseJson) {
                            if (responseJson.sucesso) {
                                Swal.fire(
                                        'Exclu�do!',
                                        'Registro exclu�do com sucesso.',
                                        'success'
                                        ).then(function () {
                                    location.href = "/SAC_V1/Categoria";
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


