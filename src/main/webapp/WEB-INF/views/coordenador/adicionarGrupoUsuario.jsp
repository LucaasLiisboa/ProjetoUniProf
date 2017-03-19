<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html lang="pt-br">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>UniProf</title>

	<link rel="icon" href="resources/img/Green/logo.png">
	
	<link rel="stylesheet" type="text/css" href="resources/lib/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="resources/lib/bootstrap/css/bootstrap-theme.css">
	<link rel="stylesheet" type="text/css" href="resources/css/style.css">
</head>
<body style="background: #dbe7e6;">
	<nav class="navbar navbar-default navbar-fixed-top barra-menu" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed collapse-custom" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>

				<a href="principal" class="brand"></a>
			</div>

			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li class="menu-horizontal"><a href="sair">Sair</a></li>
				</ul>
			</div>
		</div>
	</nav>
		
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<jsp:include page="../static/menu.jsp"></jsp:include>

				<div class="col-md-9 content-corpo">
					<div class="col-md-12">
						<h1 style="margin-bottom: 50px;">Adicionar Usuário ao Grupo</h1>
						<form class="form-horizontal">
							<div class="input-group">
								<input type="text" path="nomeUsuario" class="form-control" placeholder="Pesquisar">

								<span class="input-group-btn">
									<button type="submit" class="btn btn-pesquisar">Pesquisar</button>
								</span>
							</div>
						</form>
						
						<div class="col-md-offset-2 col-md-8" style="margin-top: 5%;">
							<form:form modelAttribute="grupo">
								<div class="form-group">
									<label for="nome">Nome do Grupo</label>
									<form:input type="text" id="nomeGrupo" path="nomeGrupo" name="nomeGrupo" class="form-control" placeholder="Nome do Grupo" disabled="true"/>
								</div>
							</form:form>
						</div>
						<div class="col-md-12">
							<div class="table-responsive mar-top5p">
								<table class="table table-striped">
									<thead>									
										<th>Nome</th>
										<th>Sobrenome</th>
										<th>Email</th>
										<th>Ação</th>
									</thead>
	
									<tbody>
										<c:forEach var="usuario" items="${listaUsuarios}">
											<tr>
												<td>${usuario.nomeUsuario}</td>
												<td>${usuario.sobrenomeUsuario}</td>
												<td>${usuario.emailUsuario}</td>
												<td>
													<a href="detalharUsuario?cdUsuario=${usuario.cdUsuario}" class="btn overflow-btn btn-pesquisar">Detalhar</a>
													<a href="addUsuarioGrupo?cdUsuario=${usuario.cdUsuario}" class="btn overflow-btn btn-pesquisar">Adicionar</a>												
												</td>											
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- <footer class="footer">
		<div class="container text-center">
			© Copyright 2015 Projeto UniProf - Todos os direitos reservados - By Lucas Lisboa
		</div>
	</footer> 
	-->
	
	<script src="resources/lib/jquery/jquery.js"></script>
	<script src="resources/lib/angular/angular.js"></script>
	<script src="resources/lib/bootstrap/js/bootstrap.js"></script>
	<script src="resources/js/myJS.js"></script>
</body>
</html>