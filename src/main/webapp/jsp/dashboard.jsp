<%-- 
    Document   : login
    Created on : 03/06/2021, 16:48:09
    Author     : 
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
                            <jsp:useBean id="consulta" class="sac.model.Consultas" scope="request"/>

                            <!--CLIENTE: LISTA TODOS OS ATENDIMENTOS-->
                            <c:if test="${usuarioLogado.perfil_Id == 1}"> 

                                <table class="table table-striped col-12">
                                    <thead>
                                        <tr>
                                            <th class="col-1">#</th>
                                            <th class="col-2">Data cria��o</th>
                                            <th class="col-2">Data Resolu��o</th>
                                            <th class="col-2">Produto</th>
                                            <th class="col-2">Situa��o</th>
                                                <c:if test="${usuarioLogado.perfil_Id!=3}">
                                                <th class="col-2">A��o</th>
                                                </c:if>

                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="item" items="${consulta.getAtendimentos()}">
                                            <tr>
                                                <td>${item.atendimento_id}</td>
                                                <td>${item.dataCriacao}</td>
                                                <td>${item.dataResolucao}</td>
                                                <td>${item.produto}</td>
                                                <td>${item.situacaoAtendimento}</td>
                                                <td>
                                                    <a  href="Atendimento?id=${item.atendimento_id}">
                                                        <i style="margin:5%;" class="fas fa-eye" alt="Visualizar"  data-toggle="tooltip" data-placement="top" title="Visualizar atendimento"></i>
                                                    </a> 
                                                    <c:if test="${item.situacaoAtendimento == 'Aberto'}">
                                                        <i class="fas fa-trash-alt" alt="Excluir" onclick="excluir(${item.atendimento_id})" style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="Excluir"></i>
                                                    </c:if>
                                                </td>     
                                            </tr>
                                        </c:forEach>

                                        <c:if test="${consulta.getAtendimentos().size() == 0}"> 
                                            <tr>
                                                <td colspan="6" style="text-align: center;">Nenhum item encontrado</td>
                                            </tr>
                                        </c:if>
                                    </tbody>
                                </table>

                            </c:if>
                        </div>

                        <!--FUNCIONARIO: LISTA TODOS OS ATENDIMENTOS N�O RESOLVIDOS-->
                        <c:if test="${usuarioLogado.perfil_Id == 2}"> 

                            <table class="table table-striped col-12">
                                <thead>
                                    <tr>
                                        <th class="col-1">#</th>
                                        <th class="col-2">Data cria��o</th>
                                        <th class="col-2">Data Resolu��o</th>
                                        <th class="col-2">Cliente</th>
                                        <th class="col-2">Produto</th>
                                        <th class="col-2">Situa��o</th>
                                        <th class="col-2">A��o</th>

                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="item" items="${consulta.getAtendimentos()}">
                                        <tr>
                                            <td>${item.atendimento_id}</td>
                                            <td>${item.dataCriacao}</td>
                                            <td>${item.dataResolucao}</td>
                                            <td>${item.cliente}</td>
                                            <td>${item.produto}</td>
                                            <td>${item.situacaoAtendimento}</td>
                                            <td>
                                                <c:if test="${item.situacaoAtendimento == 'Aberto'}">
                                                    <a href="Atendimento?id=${item.atendimento_id}">
                                                        <i style="margin:5%;" class="fas fa-clipboard-check" alt="Resolver" data-toggle="tooltip" data-placement="top" title="Resolver atendimento"></i>
                                                    </a> 
                                                </c:if>
                                            </td>    
                                        </tr>
                                    </c:forEach>

                                    <c:if test="${consulta.getAtendimentos().size() == 0}"> 
                                        <tr>
                                            <td colspan="7" style="text-align: center;">Nenhum item encontrado</td>
                                        </tr>
                                    </c:if>
                                </tbody>
                            </table>
                        </c:if>
                        <!--GERENTE: INFORMA QUANTIDADE DE ATENDIMENTOS EFETUADOS; QTD DE ATEND ABERTOS COM PERCENTAGEM EM RELA��O AO TOTAL; SEPARADOS POR CATEGORIA-->
                        <c:if test="${usuarioLogado.perfil_Id == 3}"> 
                            <div class="row">
                                <div class="col-12 col-sm-6 col-md-3">
                                    <div class="info-box">
                                        <span class="info-box-icon bg-info elevation-1"><i class="fas fa-calendar-check"></i></span>

                                        <div class="info-box-content">
                                            <span class="info-box-text">Atendimentos efetuados</span>
                                            <span class="info-box-number">
                                                ${consulta.atendimentos_efetuados}
                                            </span>
                                        </div>
                                        <!-- /.info-box-content -->
                                    </div>
                                    <!-- /.info-box -->
                                </div>
                                <div class="col-12 col-sm-6 col-md-3">
                                    <div class="info-box">
                                        <span class="info-box-icon bg-info elevation-1"><i class="fas fa-calendar-plus"></i></span>

                                        <div class="info-box-content">
                                            <span class="info-box-text">Atendimentos em aberto</span>
                                            <span class="info-box-number">
                                                ${consulta.atendimentos_abertos}
                                                <small>${consulta.atendimentos_abertos_porcentagem}%</small>
                                            </span>
                                        </div>
                                        <!-- /.info-box-content -->
                                    </div>
                                    <!-- /.info-box -->
                                </div>
                            </div>
                            <div class="row">
                                <c:forEach var="item" items="${consulta.getDadosDashboard()}">
                                    <div class="col-sm-2 col-3">
                                        <div class="description-block border-right">
                                            <h5 class="description-header">${item.quantidade_aberto == null ? 0 : item.quantidade_aberto}/${item.quantidade_total}</h5>
                                            <span class="description-text">${item.tipoatendimento}</span>
                                        </div>
                                        <!-- /.description-block -->
                                    </div>
                                    <!-- /.col -->
                                </c:forEach>
                            </div>
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

    <script type="text/javascript">
        $(function () {
            $('[data-mask]').inputmask();
//                $(".ignore-click").click(function () {
//                    return false;
//                });
        });
    </script>

</body>
</html>
