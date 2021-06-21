<%-- 
    Document   : alteracao_dados
    Created on : 03/06/2021, 20:53:58
    Author     : geova
--%>

<!DOCTYPE html>
<html lang="en">
    <head>        
        <jsp:include page="header.jsp" />
        <title>SAC - Dashboard</title>
    </head>
    <body class="hold-transition layout-top-nav">

        <div class="wrapper">
            <!-- Navbar -->
            <jsp:include page="navbar.jsp" />
            <!-- /.navbar -->

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <div class="content-header">
                    <div class="container">
                        <div class="row mb-2">
                            <div class="col-sm-6">
                                <h3 class="m-0"> Atualizar dados</h3>
                            </div><!-- /.col -->
                            <div class="col-sm-6">
                                <ol class="breadcrumb float-sm-right">
                                    <li class="breadcrumb-item"><a href="dashboard.jsp">Home</a></li>
                                    <li class="breadcrumb-item active"><a href="alteracao_dados.jsp">Perfil</a></li>
                                </ol>
                            </div><!-- /.col -->
                        </div><!-- /.row -->
                    </div><!-- /.container-fluid -->
                </div>
                <!-- /.content-header -->

                <!-- Main content -->
                <div class="content">
                    <div class="container">
                        <div class="row">
                            <div class="input-group mb-1 col-12">
                                <input type="text" class="form-control" id="name" placeholder="Nome completo">
                                <div class="input-group-append">
                                    <div class="input-group-text">
                                        <span class="fas fa-user"></span>
                                    </div>
                                </div>
                            </div>

                            <div class="input-group mb-1 col-6">
                                <input class="form-control" type="text" id="cpf" placeholder="CPF" data-inputmask='"mask": "999.999.999-99"' data-mask readonly="readonly">
                                <div class="input-group-append">
                                    <div class="input-group-text">
                                        <span class="far fa-id-card"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="input-group mb-1 col-6">
                                <input class="form-control" type="text" id="telefone" placeholder="Telefone" data-inputmask='"mask": "(99) 99999-9999"' data-mask>
                                <div class="input-group-append">
                                    <div class="input-group-text">
                                        <span class="fas fa-phone"></span>
                                    </div>
                                </div>
                            </div>

                            <div class="input-group mb-1 col-9">
                                <input class="form-control" type="text" id="rua" placeholder="Rua">
                                <div class="input-group-append">
                                    <div class="input-group-text">
                                        <span class="far fa-address-book"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="input-group mb-1 col-3">
                                <input class="form-control" type="text" id="numero" placeholder="Número">
                                <div class="input-group-append">
                                    <div class="input-group-text">
                                        <span class="far fa-address-book"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="input-group mb-1 col-3">
                                <input class="form-control" type="text" id="complemento" placeholder="Complemento">
                                <div class="input-group-append">
                                    <div class="input-group-text">
                                        <span class="far fa-address-book"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="input-group mb-1 col-5">
                                <input class="form-control" type="text" id="bairro" placeholder="Bairro">
                                <div class="input-group-append">
                                    <div class="input-group-text">
                                        <span class="far fa-address-book"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="input-group mb-1 col-4">
                                <input class="form-control" type="text" id="cep" placeholder="CEP">
                                <div class="input-group-append">
                                    <div class="input-group-text">
                                        <span class="far fa-address-book"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="input-group mb-1 col-6">
                                <input class="form-control" type="text" id="cidade" placeholder="Cidade">
                                <div class="input-group-append">
                                    <div class="input-group-text">
                                        <span class="far fa-address-book"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="input-group mb-1 col-6">
                                <input class="form-control" type="text" id="estado" placeholder="Estado">
                                <div class="input-group-append">
                                    <div class="input-group-text">
                                        <span class="far fa-address-book"></span>
                                    </div>
                                </div>
                            </div>

                            <div class="input-group mb-1 col-12">
                                <input type="email" class="form-control" id="email" placeholder="Email" readonly="readonly">
                                <div class="input-group-append">
                                    <div class="input-group-text">
                                        <span class="fas fa-envelope"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="input-group mb-1 col-6">
                                <input type="password" class="form-control" id="senha" placeholder="Senha">
                                <div class="input-group-append">
                                    <div class="input-group-text">
                                        <span class="fas fa-lock"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="input-group mb-1 col-6">
                                <input type="password" class="form-control" id="conf_senha" placeholder="Confirmação senha">
                                <div class="input-group-append">
                                    <div class="input-group-text">
                                        <span class="fas fa-lock"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-4">
                                <button type="submit" class="btn btn-primary btn-block">Alterar</button>
                            </div>
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
                $('[data-mask]').inputmask()
            });
        </script>
    </body>
</html>
