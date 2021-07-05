<%-- 
    Document   : relatorio
    Created on : 04/07/2021, 12:50:09
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
        <!-- daterange picker -->
        <link rel="stylesheet" type="text/css" href="jsp/plugins/daterangepicker/daterangepicker.css">
        <title>SAC - Relatórios</title>
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
                            <div class="col-sm-6">
                                <h3 class="m-0"> Relatórios</h3>
                            </div>
                        </div><!-- /.row -->
                    </div><!-- /.container-fluid -->
                </div>
                <!-- /.content-header -->

                <!-- Main content -->
                <div class="content">
                    <div class="container">
                        <jsp:useBean id="tipo" class="java.lang.Integer" scope="request"/>

                        <p ${mensagem.isEmpty() ? "class='hidden'":""}>${mensagem}</p>

                        <c:if test="${tipo == 1}"> 
                            <div class="col-sm-6">
                                <h5> Relatório de atendimentos abertos por período</h5>
                            </div>
                            <form class="" action="GerarRelatorioAtendimento" method="post">
                                <div class="row">
                                    <!--                                    <div class="input-group mb-1 col-5">
                                                                            <div class="input-group date" id="datetimeinicio" data-target-input="nearest">
                                                                                <input type="text" class="form-control datetimepicker-input" data-target="#datetimeinicio" id="dataInicio" name="dataInicio" placeholder="Data de início"/>
                                                                                <div class="input-group-append" data-target="#datetimeinicio" data-toggle="datetimepicker">
                                                                                    <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="input-group mb-1 col-5">
                                                                            <div class="input-group date" id="datetimefim" data-target-input="nearest" readonly="">
                                                                                <input type="text" class="form-control datetimepicker-input" data-target="#datetimefim" id="dataFim" name="dataFim" placeholder="Data fim"/>
                                                                                <div class="input-group-append" data-target="#datetimefim" data-toggle="datetimepicker">
                                                                                    <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                                                                                </div>
                                                                            </div>
                                                                        </div>-->
                                    <div class="input-group mb-1 col-5">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text"><i class="far fa-clock"></i></span>
                                        </div>
                                        <input type="text" class="form-control float-right" id="reservationtime" name="dates" readonly="">
                                    </div>
                                    <div class="col-2">
                                        <button type="submit" name="bRegistrar" value="registrar" class="btn btn-primary btn-block">Gerar</button>
                                    </div>
                                </div>
                            </form>
                        </c:if>

                        <c:if test="${tipo == 2}"> <div class="col-sm-6">
                                <h5> Relatório de reclamações filtrado por situação</h5>
                            </div>
                            <form class="class" action="GerarRelatorioReclamacao" method="post">
                                <div class="input-group mb-1 col-6">
                                    <select class="custom-select form-control-border" id="situacao" name="situacao" placeholder="Tipo de sitação">
                                        <option selected="selected" value="0">Selecione a situação</option>    
                                        <option value="1">Abertos</option>    
                                        <option value="2">Finalizados</option>    
                                        <option value="3">Todos</option>  
                                    </select>
                                </div>
                                <div class="col-2">
                                    <button type="submit" name="bRegistrar" value="registrar" class="btn btn-primary btn-block">Gerar</button>
                                </div>
                            </form>
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

    <!-- date-range-picker -->
    <script src="jsp/plugins/daterangepicker/daterangepicker.js"></script>

    <script type="text/javascript">
        $(function () {
            $('[data-mask]').inputmask();
//                $(".ignore-click").click(function () {
//                    return false;
//                });
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

            //Date and time picker
//            $('#datetimefim').datetimepicker({
//                icons: {time: 'far fa-clock'},
//                ignoreReadonly: true,
//                format: 'DD/MM/YYYY HH:mm:ss'
//            });
//            $('#datetimeinicio').datetimepicker({
//                icons: {time: 'far fa-clock'},
//                ignoreReadonly: true,
//                format: 'DD/MM/YYYY HH:mm:ss'
//            });
//            document.getElementById('dataInicio').readOnly = true;
//            document.getElementById('dataFim').readOnly = true;
            //Date range picker with time picker
            $('#reservationtime').daterangepicker({
                timePicker: true,
                timePickerIncrement: 30,
                locale: {
                    format: 'DD/MM/YYYY hh:mm:ss A'
                }, ignoreReadonly: true
            })
        });
    </script>

</body>
</html>

