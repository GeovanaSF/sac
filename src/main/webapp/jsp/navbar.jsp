<!-- Navbar -->
<nav class="main-header navbar navbar-expand-md navbar-light navbar-white">
    <div class="container">
        <a href="Home" class="navbar-brand">
            <span class="brand-text font-weight-light">SAC</span>
        </a>

        <div class="collapse navbar-collapse order-3" id="navbarCollapse">
            <!-- Left navbar links -->
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a href="Home" class="nav-link">Home</a>
                </li>
                <jsp:useBean id="usuarioLogado" class="sac.domain.Usuario" scope="session"/>
                <c:if test="${usuarioLogado.perfil_Id == 1}"> 
                    <li class="nav-item">
                        <a href="AlterarDados" class="nav-link">Alteração de dados</a>
                    </li> 
                </c:if>
                <c:if test="${usuarioLogado.perfil_Id == 1}> 
                    <li class="nav-item">
                        <a href="NovoAtendimento" class="nav-link">Novo atendimento</a>
                    </li>
                </c:if>
                <c:if test="${usuarioLogado.perfil_Id != 1}" var="teste"> 
                    <c:out value="${teste}" />
                    <li class="nav-item">
                        <a href="Atendimentos/EmAberto" class="nav-link">Atendimentos em aberto</a>
                    </li>
                </c:if>
                <li class="nav-item">
                    <a href="Atendimentos" class="nav-link">Todos Atendimentos</a>
                </li>
                <c:if test="${usuarioLogado.perfil_Id != 1}"> 
                    <li class="nav-item dropdown">
                        <a id="dropdownSubMenu1" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link dropdown-toggle">Cadastros</a>
                        <ul aria-labelledby="dropdownSubMenu1" class="dropdown-menu border-0 shadow">
                            <c:if test="${usuarioLogado.perfil_Id == 2}"> 
                                <li><a href="Novo/Categoria" class="dropdown-item">Categorias</a></li>
                                <li><a href="Novo/Produto" class="dropdown-item">Produtos</a></li>
                            </c:if>
                            <c:if test="${usuarioLogado.perfil_Id == 3}"> 
                                <li><a href="Novo/Funcionario" class="dropdown-item">Funcionários/Gerentes</a></li>
                            </c:if>
                        </ul>
                    </li>
                </c:if>
                <c:if test="${usuarioLogado.perfil_Id == 3}"> 
                    <li class="nav-item dropdown">
                        <a id="dropdownSubMenu1" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link dropdown-toggle">Relatórios</a>
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
                <a class="nav-link" data-toggle="dropdown" href="#">
                    <i class="fas fa-user"></i>
                    <span class="badge badge-danger navbar-badge"></span>
                </a>
                <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">                   
                    <div class="dropdown-divider"></div>
                    <a href="#" class="dropdown-item dropdown-footer">Logout</a>
                </div>
            </li>                
        </ul>
    </div>
</nav>
<!-- /.navbar -->
