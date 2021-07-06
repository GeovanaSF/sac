<%@page import="sac.util.Erro"%>
<%@page import="sac.domain.Usuario"%>
<!DOCTYPE html>
<html lang="en">
    <head>        
        <jsp:include page="header.jsp" />
        <title>SAC - Login</title>
    </head>
    <div class="login-logo">
        <a href=""><b>SAC</b> Sistema de Atendimento ao Cliente</a>
    </div>
    <body class="hold-transition login-page">
        <div class="login-box">
            <div class="card">
                <div class="card-body login-card-body">
                    <p class="login-box-msg">Entre para iniciar sua sessão</p>

                    <form action="Login" method="post" id="login_form">
                        <div class="input-group mb-3">
                            <input type="email" class="form-control" placeholder="Email" id="email" name="email">
                            <div class="input-group-append">
                                <div class="input-group-text">
                                    <span class="fas fa-envelope"></span>
                                </div>
                            </div>
                        </div>
                        <div class="input-group mb-3">
                            <input type="password" class="form-control" placeholder="Senha" id="password" name="password">
                            <div class="input-group-append">
                                <div class="input-group-text">
                                    <span class="fas fa-lock"></span>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <button type="submit" name="bLogin" value="login" class="btn btn-primary btn-block">Entrar</button>
                            </div>
                        </div>
                    </form>

<!--                    <p class="mb-1">
                        <a href="EsqueciSenha">Esqueci minha senha</a>
                    </p>-->
                    <p class="mb-0">
                        <a href="Registrar" class="text-center">Registre-se</a>
                    </p>
                </div>
                <!-- /.login-card-body -->
            </div>
        </div>
        <!-- /.login-box -->

        <jsp:include page="footer_scripts.jsp" /> 
        <script type="text/javascript">
            $(function () {

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
//                $.validator.setDefaults({
//                    submitHandler: function () {
////                        alert("Form successful submitted!");
//                    }
//                });
//                $('#login_form').validate({
//                    rules: {
//                        email: {
//                            required: true,
//                            email: true,
//                        },
//                        password: {
//                            required: true,
//                            minlength: 6
//                        }
//                    },
//                    messages: {
//                        email: {
//                            required: "E-mail é obrigatório",
//                            email: "Infome um e-mail válido"
//                        },
//                        password: {
//                            required: "Senha é obrigatória",
//                            minlength: "Sua senha deve ter pelo menos 6 caracteres"
//                        }
//                    },
//                    errorElement: 'span',
//                    errorPlacement: function (error, element) {
//                        error.addClass('invalid-feedback');
//                        element.closest('.input-group').append(error);
//                    },
//                    highlight: function (element, errorClass, validClass) {
//                        $(element).addClass('is-invalid');
//                    },
//                    unhighlight: function (element, errorClass, validClass) {
//                        $(element).removeClass('is-invalid');
//                    }
//                });
            });
        </script>
    </body>
</html>
