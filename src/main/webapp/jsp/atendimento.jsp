<%-- 
    Document   : atendimento
    Created on : 24/06/2021, 22:26:32
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
                                <h3 class="m-0"> Atendimentos</h3>
                            </div>

                            <c:if test="${usuarioLogado.perfil_Id == 1}"> 
                                <div class="col-sm-2">
                                    <a href="Novo_Atendimento" class="nav-link btn btn-primary">Novo </a>
                                </div>
                            </c:if>
                        </div><!-- /.row -->
                    </div><!-- /.container-fluid -->
                </div>
                <!-- /.content-header -->

                <!-- Main content -->
                <div class="content">
                    <div class="container">
                        <div class="row">      
                            <jsp:useBean id="consulta" class="sac.model.Consultas" scope="request"/>

                            <table class="table table-striped col-12">
                                <thead>
                                    <tr>
                                        <th class="col-1">#</th>
                                        <th class="col-2">Data criação</th>
                                        <th class="col-2">Cliente</th>
                                        <th class="col-2">Produto</th>
                                        <th class="col-2">Situação</th>
                                        <c:if test="${usuarioLogado.perfil_Id!=3}">
                                            <th class="col-2">Ação</th>
                                        </c:if>

                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="item" items="${consulta.getAtendimentos()}">
                                        <tr>
                                            <td>${item.atendimento_id}</td>
                                            <td>${item.dataCriacao}</td>
                                            <td>${item.cliente}</td>
                                            <td>${item.produto}</td>
                                            <td>${item.situacaoAtendimento}</td>
                                            <c:if test="${usuarioLogado.perfil_Id!=3}">
                                                <td>
                                                    <c:if test="${usuarioLogado.perfil_Id==1}">
                                                        <a  href="Atendimento?id=${item.atendimento_id}">
                                                            <i style="margin:5%;" class="fas fa-eye" alt="Visualizar"  data-toggle="tooltip" data-placement="top" title="Visualizar atendimento"></i>
                                                        </a> 
                                                    </c:if>
                                                    <c:if test="${item.situacaoAtendimento == 'Aberto' && usuarioLogado.perfil_Id==2}">
                                                        <a href="Atendimento?id=${item.atendimento_id}">
                                                            <i style="margin:5%;" class="fas fa-clipboard-check" alt="Resolver" data-toggle="tooltip" data-placement="top" title="Resolver atendimento"></i>
                                                        </a> 
                                                    </c:if>
                                                    <c:if test="${item.situacaoAtendimento == 'Aberto' && usuarioLogado.perfil_Id==1}">
                                                        <i class="fas fa-trash-alt" alt="Excluir" onclick="excluir(${item.atendimento_id})" style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="Excluir"></i>
                                                    </c:if>
                                                </td>       
                                            </c:if>
                                        </tr>
                                    </c:forEach>

                                    <c:if test="${consulta.getAtendimentos().size() == 0}"> 
                                        <tr>
                                            <c:if test="${usuarioLogado.perfil_Id!=3}">
                                                <td colspan="6" style="text-align: center;">Nenhum item encontrado</td>
                                            </c:if>

                                            <c:if test="${usuarioLogado.perfil_Id==3}">
                                                <td colspan="5" style="text-align: center;">Nenhum item encontrado</td>
                                            </c:if>
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
                        $.get("Excluir?tipo=atendimento&id=" + id, function (responseJson) {
                            if (responseJson.sucesso) {
                                Swal.fire(
                                        'Excluído!',
                                        'Registro excluído com sucesso.',
                                        'success'
                                        ).then(function () {
                                    location.href = "/SAC_V1/MeusAtendimentos";
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
