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
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

                        <jsp:useBean id="pessoa" class="sac.model.Pessoa" scope="request">
                            <jsp:setProperty name="pessoa" property="*" />
                        </jsp:useBean>

                        <div class="input-group mb-1 col-12">
                            <input type="text" class="form-control" id="nome" name="nome" placeholder="Nome completo" value="${pessoa.nome}">
                            <div class="input-group-append">
                                <div class="input-group-text">
                                    <span class="fas fa-user"></span>
                                </div>
                            </div>
                        </div>

                        <div class="input-group mb-1 col-6">
                            <input class="form-control" type="text" id="cpf" name="cpf" placeholder="CPF" data-inputmask='"mask": "999.999.999-99"' data-mask value="${pessoa.cpf}">
                            <div class="input-group-append">
                                <div class="input-group-text">
                                    <span class="far fa-id-card"></span>
                                </div>
                            </div>
                        </div>
                        <div class="input-group mb-1 col-6">
                            <input class="form-control" type="text" id="telefone" name="telefone" placeholder="Telefone" data-inputmask='"mask": "(99) 99999-9999"' data-mask value="${pessoa.telefone}">
                            <div class="input-group-append">
                                <div class="input-group-text">
                                    <span class="fas fa-phone"></span>
                                </div>
                            </div>
                        </div>

                        <div class="input-group mb-1 col-9">
                            <input class="form-control" type="text" id="rua" name="rua" placeholder="Rua" value="${pessoa.rua}">
                            <div class="input-group-append">
                                <div class="input-group-text">
                                    <span class="far fa-address-book"></span>
                                </div>
                            </div>
                        </div>
                        <div class="input-group mb-1 col-3">
                            <input class="form-control" type="text" id="numero" name="numero" placeholder="Número" value="${pessoa.numero}">
                            <div class="input-group-append">
                                <div class="input-group-text">
                                    <span class="far fa-address-book"></span>
                                </div>
                            </div>
                        </div>
                        <div class="input-group mb-1 col-3">
                            <input class="form-control" type="text" id="complemento" name="complemento" placeholder="Complemento" value="${pessoa.complemento}">
                            <div class="input-group-append">
                                <div class="input-group-text">
                                    <span class="far fa-address-book"></span>
                                </div>
                            </div>
                        </div>
                        <div class="input-group mb-1 col-5">
                            <input class="form-control" type="text" id="bairro" name="bairro" placeholder="Bairro" value="${pessoa.bairro}">
                            <div class="input-group-append">
                                <div class="input-group-text">
                                    <span class="far fa-address-book"></span>
                                </div>
                            </div>
                        </div>
                        <div class="input-group mb-1 col-4">
                            <input class="form-control" type="text" id="cep" name="cep" placeholder="CEP" data-inputmask='"mask": "99999-999"' data-mask value="${pessoa.cep}">
                            <div class="input-group-append">
                                <div class="input-group-text">
                                    <span class="far fa-address-book"></span>
                                </div>
                            </div>
                        </div>
                        <div class="input-group mb-1 col-6">

                            <jsp:useBean id="estados" class="sac.model.Estados" scope="request"/>

                            <select class="custom-select form-control-border" id="estado_id" name="estado_id" value="${pessoa.estado_id}" placeholder="Estado">
                                <c:if test="${pessoa.estado_id == 0}">
                                    <option selected="selected" value="">Selecione o estado</option>    
                                </c:if>
                                <c:if test="${pessoa.estado_id != 0}">
                                    <option value="">Selecione o estado</option>    
                                </c:if>
                                <c:forEach var="estado" items="${estados.getEstados()}">

                                    <c:if test="${pessoa.estado_id == estado.estado_id}">
                                        <option selected="selected" value="">Selecione o estado</option>    
                                    </c:if>
                                    <option value="${estado.estado_id}">${estado.nome}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="input-group mb-1 col-6">
                            <select class="custom-select form-control-border" id="cidade_id" name="cidade_id" value="${pessoa.cidade_id}" placeholder="Cidade">
                                <option selected="selected" value="">Selecione a cidade</option>
                            </select>
                        </div>

                        <div class="input-group mb-1 col-12">
                            <input type="email" class="form-control" id="email" name="email" placeholder="Email" value="${pessoa.email}">
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
        </script>

    </body>
</html>
