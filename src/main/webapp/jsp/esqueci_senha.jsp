<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="header.jsp" />
        <title>SAC - Esqueci a senha</title>
    </head>
    <body class="hold-transition login-page">
        <div class="login-logo">
            <a href="Login"><b>SAC</b> Sistema de Atendimento ao Cliente</a>
        </div>
        <div class="card">
            <div class="card-body login-card-body">
                <p class="login-box-msg">Esqueceu sua senha? Requisite nova senha.</p>

                <form action="EsqueciSenha" method="post">
                    <div class="input-group mb-3">
                        <input type="email" class="form-control" placeholder="Email">
                        <div class="input-group-append">
                            <div class="input-group-text">
                                <span class="fas fa-envelope"></span>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <button type="submit" class="btn btn-primary btn-block">Solicitar</button>
                        </div>
                        <!-- /.col -->
                    </div>
                </form>

                <p class="mt-3 mb-1">
                    <a href="Login">Login</a>
                </p>
                <p class="mb-0">
                    <a href="Registrar" class="text-center">Registre-se</a>
                </p>
            </div>
            <!-- /.login-card-body -->
        </div>
    </div>
    <!-- /.login-box -->

    <jsp:include page="footer_scripts.jsp" />
</body>
</html>
