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
					<h1 style="margin-bottom: 50px;">Detalhar Questionario</h1>

					<form:form class="form-horizontal" modelAttribute="questionario">
						<form:hidden path="cdQuestionario"/>
						
						<label for="tituloQuestionario">Título</label>
						<form:input type="text" id="marb-10" path="tituloQuestionario" name="tituloQuestionario" class="form-control" placeholder="Título do Questionário" disabled="true"/>
						
						<label for="dsQuestionario">Descrição</label>
						<form:textarea id="marb-10" path="dsQuestionario" class="form-control" placeholder="Descrição do Questionário" disabled="true"/>
						
						<label for="qtdQuestoes">Número Máximo de Questões</label>
						<form:input type="number" id="marb-10" path="qtdQuestoes" name="qtdQuestoes" class="form-control" placeholder="Quantidade de Questões" disabled="true"/>
						
						<label for="categoria">Categoria</label>
						<form:input type="text" id="marb-10" path="categoria.dsCategoria" name="categoria" class="form-control" placeholder="Categoria" disabled="true"/>
						
						<hr class="separator">
						
						<c:forEach var="questoes" items="${listaQuestoes}" varStatus="count">
							<label for="enunciado">Questão <c:out value="${count.index+1}" /></label>
							<textarea id="marb-10" class="form-control" placeholder="Enunciado da Questão" disabled="true">${questoes.enunciadoQuestao}</textarea>
							
							<label for="altA">Alternativa A</label>
							<div id="marb-10" class="input-group">
								<span class="input-group-addon">
									<c:choose>
										<c:when test="${questoes.alternativaCorreta == 'A'}">
											<input type="radio" value="${questoes.alternativaCorreta}" disabled="true" checked="checked"/>
										</c:when>
										<c:otherwise>
											<input type="radio" disabled="true"/>
										</c:otherwise>
									</c:choose>
								</span>
								<input type="text" class="form-control form" name="altA" value="${questoes.alternativaA}" placeholder="Alternativa A" disabled="true"/>
							</div>
							
							<label for="altB">Alternativa B</label>
							<div id="marb-10" class="input-group">
								<span class="input-group-addon">
									<c:choose>
										<c:when test="${questoes.alternativaCorreta == 'B'}">
											<input type="radio" value="${questoes.alternativaCorreta}" disabled="true" checked="checked"/>
										</c:when>
										<c:otherwise>
											<input type="radio" disabled="true"/>
										</c:otherwise>
									</c:choose>
								</span>
								<input type="text" class="form-control form" name="altB" value="${questoes.alternativaB}" placeholder="Alternativa B" disabled="true"/>
							</div>
							
							<label for="altC">Alternativa C</label>
							<div id="marb-10" class="input-group">
								<span class="input-group-addon">
									<c:choose>
										<c:when test="${questoes.alternativaCorreta == 'C'}">
											<input type="radio" value="${questoes.alternativaCorreta}" disabled="true" checked="checked"/>
										</c:when>
										<c:otherwise>
											<input type="radio" disabled="true"/>
										</c:otherwise>
									</c:choose>
								</span>
								<input type="text" class="form-control form" name="altC" value="${questoes.alternativaC}" placeholder="Alternativa C" disabled="true"/>
							</div>
							
							<label for="altD">Alternativa D</label>
							<div id="marb-10" class="input-group">
								<span class="input-group-addon">
									<c:choose>
										<c:when test="${questoes.alternativaCorreta == 'D'}">
											<input type="radio" value="${questoes.alternativaCorreta}" disabled="true" checked="checked"/>
										</c:when>
										<c:otherwise>
											<input type="radio" disabled="true"/>
										</c:otherwise>
									</c:choose>
								</span>
								<input type="text" class="form-control form" name="altD" value="${questoes.alternativaD}" placeholder="Alternativa D" disabled="true"/>
							</div>
							
							<label for="altE">Alternativa E</label>
							<div id="marb-10" class="input-group">
								<span class="input-group-addon">
									<c:choose>
										<c:when test="${questoes.alternativaCorreta == 'E'}">
											<input type="radio" value="${questoes.alternativaCorreta}" disabled="true" checked="checked"/>
										</c:when>
										<c:otherwise>
											<input type="radio" disabled="true"/>
										</c:otherwise>
									</c:choose>
								</span>
								<input type="text" class="form-control form" name="altE" value="${questoes.alternativaE}" placeholder="Alternativa E" disabled="true"/>
							</div>							
							
							<hr class="separator">
						</c:forEach>
						
						<div class="text-right">
							<a href="adicionarQuestao?cdQuestionario=${questionario.cdQuestionario}" class="btn btn-form-uniprof col-md-offset-9 col-md-3">Adicionar Questão</a>
							<a href="deletaQuestao?cdQuestionario=${questionario.cdQuestionario}" class="btn btn-form-uniprof col-md-offset-9 col-md-3">Remover Questão</a>
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