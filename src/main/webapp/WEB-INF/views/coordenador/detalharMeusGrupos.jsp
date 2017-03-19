<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html lang="pt-br">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>UniProf</title>

	<link rel="shortcut icon" href="resources/img/Green/logo.png">
	
	<link rel="stylesheet" type="text/css" href="resources/lib/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="resources/lib/bootstrap/css/bootstrap-theme.css">
	<link rel="stylesheet" type="text/css" href="resources/css/style.css">
</head>

<body class="layout-main" style="background: #dbe7e6;">	
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
					<h1 style="margin-bottom: 50px;">Detalhar Meus Grupos</h1>
					
					<div class="col-md-offset-2 col-md-8">
						<form:form class="form-horizontal" action="salvarGrupo" method="post" modelAttribute="grupo">
							<form:hidden path="cdGrupo"/>
							
							<div class="form-group">
								<label for="grupo">Nome do Grupo</label>
								<form:input type="text" id="nomeGrupo" path="nomeGrupo" name="nomeGrupo" class="form-control" placeholder="Nome do Grupo" disabled="true"/>
							</div>
							
							<div class="form-group">
								<label for="grupo">Descri��o do Grupo</label>
								<form:input type="text" id="dsGrupo" path="dsGrupo" name="dsGrupo" class="form-control" placeholder="Descri��o do Grupo" disabled="true"/>
							</div>
							
							<div class="form-group">
								<label for="curso">Nome do Curso</label>
								<form:input type="text" id="nomeCurso" path="curso.nomeCurso" name="nomeCurso" class="form-control" placeholder="Nome do Curso" disabled="true"/>
							</div>
						</form:form>
					</div>
					
					<div class="text-right">
						<a href="adicionarGrupoUsuario?cdGrupo=${grupo.cdGrupo}" class="btn btn-form-uniprof col-md-offset-7 col-md-3">Adicionar Usu�rio</a>
						<a href="deletaUsuarioGrupo?cdGrupo=${grupo.cdGrupo}" class="btn btn-form-uniprof col-md-offset-7 col-md-3">Remover Usu�rio</a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- <footer class="footer">
		<div class="container text-center">
			� Copyright 2015 Projeto UniProf - Todos os direitos reservados - By Lucas Lisboa
		</div>
	</footer> -->
	
	<script src="resources/lib/jquery/jquery.js"></script>
	<script src="resources/lib/angular/angular.js"></script>
	<script src="resources/lib/bootstrap/js/bootstrap.js"></script>
	<script src="resources/js/my.js"></script>
</body>
</html>