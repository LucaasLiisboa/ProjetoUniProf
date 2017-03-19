<%@page import="com.projeto.uniprof.dao.CategoriaDAOImpl"%>
<%@page import="com.projeto.uniprof.dao.CategoriaDAO"%>
<%@page import="com.projeto.uniprof.model.Categoria"%>
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
					<h1 style="margin-bottom: 50px;">Detalhar Questão</h1>

					<form:form class="form-horizontal" action="salvarQuestao" method="post" modelAttribute="questao">
						<form:hidden path="cdQuestao"/>
						
						
						<label class="control-label" for="enunciado">Enunciado</label>
						<form:textarea id="marb-10" path="enunciadoQuestao" class="form-control" placeholder="Enunciado da Questão" disabled="true"/>
						
						<label class="control-label" for="categoria">Categoria</label>
						<form:select id="marb-10" path="categoria.cdCategoria" class="form-control form" disabled="true">
							<option value="">Selecione</option>
							<c:forEach var="listaCategoria" items="${listaCategoria}">
								<form:option value="${listaCategoria.cdCategoria}">
									${listaCategoria.dsCategoria}									
								</form:option>
							</c:forEach>
						</form:select>
						
						<label class="control-label" for="email">E-mail do Autor</label>						
						<form:input type="text" id="marb-10" path="usuario.emailUsuario" class="form-control form" name="emailAutor" placeholder="E-mail do Autor" disabled="true"/>
						
						
						<label class="control-label" for="altA">Alternativa A</label>
						<div id="marb-10" class="input-group">
							<span class="input-group-addon">
								<form:radiobutton path="alternativaCorreta" value="A" disabled="true"></form:radiobutton>								
							</span>
							<form:input type="text" path="alternativaA" class="form-control form" name="altA" placeholder="Alternativa A" disabled="true"/>
						</div>
						
						<label class="control-label" for="altB">Alternativa B</label>
						<div id="marb-10" class="input-group">
							<span class="input-group-addon">
								<form:radiobutton path="alternativaCorreta" value="B" disabled="true"></form:radiobutton>
							</span>
							<form:input type="text" path="alternativaB" class="form-control form" name="altB" placeholder="Alternativa B" disabled="true"/>
						</div>
						
						<label class="control-label" for="altC">Alternativa C</label>
						<div id="marb-10" class="input-group">
							<span class="input-group-addon">
								<form:radiobutton path="alternativaCorreta" value="C" disabled="true"></form:radiobutton>
							</span>
							<form:input type="text" path="alternativaC" class="form-control form" name="altC" placeholder="Alternativa C" disabled="true"/>
						</div>
						
						<label class="control-label" for="altD">Alternativa D</label>
						<div id="marb-10" class="input-group">
							<span class="input-group-addon">
								<form:radiobutton path="alternativaCorreta" value="D" disabled="true"></form:radiobutton>
							</span>
							<form:input type="text" path="alternativaD" class="form-control form" name="altD" placeholder="Alternativa D" disabled="true"/>
						</div>

						<label class="control-label" for="altE">Alternativa E</label>
						<div id="marb-10" class="input-group">
							<span class="input-group-addon">
								<form:radiobutton path="alternativaCorreta" value="E" disabled="true"></form:radiobutton>
							</span>
							<form:input type="text" path="alternativaE" class="form-control form" name="altE" placeholder="Alternativa E" disabled="true"/>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>

	<!-- <footer class="footer">
		<div class="container text-center">
			© Copyright 2015 Projeto UniProf - Todos os direitos reservados - By Lucas Lisboa
		</div>
	</footer> -->
	
	<script src="resources/lib/jquery/jquery.js"></script>
	<script src="resources/lib/angular/angular.js"></script>
	<script src="resources/lib/bootstrap/js/bootstrap.js"></script>
	<script src="resources/js/my.js"></script>
</body>
</html>