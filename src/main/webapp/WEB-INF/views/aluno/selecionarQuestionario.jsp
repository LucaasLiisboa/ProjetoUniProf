<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

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
					<h1 style="margin-bottom: 50px;">Selecionar Questionário</h1>

					<form class="form-horizontal">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="Pesquisar">
							<span class="input-group-btn">
								<button class="btn btn-pesquisar" type="button">Pesquisar</button>
							</span>
						</div>
					</form>

					<div class="table-responsive mar-top5p">
						<table class="table table-striped">
							<thead>
								<th>Título</th>
								<th>Descrição</th>
								<th>Categoria</th>
								<th>Professor</th>
								<th>Ação</th>
							</thead>

							<tbody>
								<c:forEach var="questionario" items="${listaQuestionarios}">
									<tr>
										<td>${questionario.tituloQuestionario}</td>
										<td>${questionario.dsQuestionario}</td>
										<td>${questionario.categoria.dsCategoria}</td>
										<td>${questionario.usuario.nomeUsuario.concat(' ').concat(questionario.usuario.sobrenomeUsuario)}</td>
										<td>
											<a href="sobreQuestionario?cdQuestionario=${questionario.cdQuestionario}" class="btn overflow-btn btn-pesquisar">Responder</a>											
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
	
	<script src="resources/lib/jquery/jquery.js"></script>
	<script src="resources/lib/angular/angular.js"></script>
	<script src="resources/lib/bootstrap/js/bootstrap.js"></script>
	<script src="resources/js/my.js"></script>
</body>
</html>