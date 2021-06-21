<%-- 
    Document   : register
    Created on : 03/06/2021, 16:59:11
    Author     : geova
--%>

<%@page import="sac.util.Erro"%>
<%@page import="sac.dao.EstadoDAO"%>
<%@page import="sac.domain.Estado"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="header.jsp" />
        <title>SAC - Registre-se</title>    
    </head>

    <body class="hold-transition register-page">
        <div class="register-box">
            <div class="login-logo">
                <a href="index.jsp"><b>SAC</b> Sistema de Atendimento ao Cliente</a>
            </div>
            <div class="card">
                <div class="card-body register-card-body">
                    <p class="login-box-msg">Completar cadastro de usuário</p>

                    <form class="row" action="Registrar" method="post">
                        <div class="input-group mb-1 col-12">
                            <input type="text" class="form-control" id="nome" name="nome" placeholder="Nome completo">
                            <div class="input-group-append">
                                <div class="input-group-text">
                                    <span class="fas fa-user"></span>
                                </div>
                            </div>
                        </div>

                        <div class="input-group mb-1 col-6">
                            <input class="form-control" type="text" id="cpf" name="cpf" placeholder="CPF" data-inputmask='"mask": "999.999.999-99"' data-mask>
                            <div class="input-group-append">
                                <div class="input-group-text">
                                    <span class="far fa-id-card"></span>
                                </div>
                            </div>
                        </div>
                        <div class="input-group mb-1 col-6">
                            <input class="form-control" type="text" id="telefone" name="telefone" placeholder="Telefone" data-inputmask='"mask": "(99) 99999-9999"' data-mask>
                            <div class="input-group-append">
                                <div class="input-group-text">
                                    <span class="fas fa-phone"></span>
                                </div>
                            </div>
                        </div>

                        <div class="input-group mb-1 col-9">
                            <input class="form-control" type="text" id="rua" name="rua" placeholder="Rua">
                            <div class="input-group-append">
                                <div class="input-group-text">
                                    <span class="far fa-address-book"></span>
                                </div>
                            </div>
                        </div>
                        <div class="input-group mb-1 col-3">
                            <input class="form-control" type="text" id="numero" name="numero" placeholder="Número">
                            <div class="input-group-append">
                                <div class="input-group-text">
                                    <span class="far fa-address-book"></span>
                                </div>
                            </div>
                        </div>
                        <div class="input-group mb-1 col-3">
                            <input class="form-control" type="text" id="complemento" name="complemento" placeholder="Complemento">
                            <div class="input-group-append">
                                <div class="input-group-text">
                                    <span class="far fa-address-book"></span>
                                </div>
                            </div>
                        </div>
                        <div class="input-group mb-1 col-5">
                            <input class="form-control" type="text" id="bairro" name="bairro" placeholder="Bairro">
                            <div class="input-group-append">
                                <div class="input-group-text">
                                    <span class="far fa-address-book"></span>
                                </div>
                            </div>
                        </div>
                        <div class="input-group mb-1 col-4">
                            <input class="form-control" type="text" id="cep" name="cep" placeholder="CEP" data-inputmask='"mask": "99999-999"' data-mask>
                            <div class="input-group-append">
                                <div class="input-group-text">
                                    <span class="far fa-address-book"></span>
                                </div>
                            </div>
                        </div>
                        <div class="input-group mb-1 col-6">

                            <input class="form-control" type="text" id="estado" name="estado" placeholder="Estado">
                            <div class="input-group-append">
                                <div class="input-group-text">
                                    <span class="far fa-address-book"></span>
                                </div>
                            </div>
                        </div>
                        <div class="input-group mb-1 col-6">
                            <input class="form-control" type="text" id="cidade" name="cidade" placeholder="Cidade">
                            <div class="input-group-append">
                                <div class="input-group-text">
                                    <span class="far fa-address-book"></span>
                                </div>
                            </div>
                        </div>

                        <div class="input-group mb-1 col-12">
                            <input type="email" class="form-control" id="email" name="email" placeholder="Email">
                            <div class="input-group-append">
                                <div class="input-group-text">
                                    <span class="fas fa-envelope"></span>
                                </div>
                            </div>
                        </div>
                        <div class="input-group mb-1 col-6">
                            <input type="password" class="form-control" id="senha" name="senha" placeholder="Senha">
                            <div class="input-group-append">
                                <div class="input-group-text">
                                    <span class="fas fa-lock"></span>
                                </div>
                            </div>
                        </div>
                        <div class="input-group mb-1 col-6">
                            <input type="password" class="form-control" id="conf_senha" name="conf_senha" placeholder="Confirmação senha">
                            <div class="input-group-append">
                                <div class="input-group-text">
                                    <span class="fas fa-lock"></span>
                                </div>
                            </div>
                        </div>
                        <div class="col-12">
                            <button type="submit" name="bRegistrar" value="registrar" class="btn btn-primary btn-block">Registrar</button>
                        </div>
                    </form>

                    <a href="Login" class="text-center">Já estou registrado</a>
                </div>
                <!-- /.form-box -->
            </div><!-- /.card -->
        </div>
        <!-- /.register-box -->

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
