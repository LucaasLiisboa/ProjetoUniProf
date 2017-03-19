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
					<h1 style="margin-bottom: 50px;">Editar Usuário</h1>
					<div class="col-md-offset-2 col-md-8">
						<form:form class="form-horizontal" action="alterarPermissao" method="post" modelAttribute="usuario">
							<form:hidden path="cdUsuario"/>
							
							<div class="form-group">
								<label for="nome">Nome</label>
								<form:input type="text" id="nomeUsuario" path="nomeUsuario" name="nomeUsuario" class="form-control" placeholder="Nome" disabled="true"/>
							</div>
							
							<div class="form-group">
								<label for="sobrenome">Sobrenome</label>
								<form:input type="text" id="sobrenomeUsuario" path="sobrenomeUsuario" name="sobrenomeUsuario" class="form-control" placeholder="Sobrenome" disabled="true"/>
							</div>
							
							<div class="form-group">
								<label for="email">E-mail</label>
								<form:input type="text" id="emailUsuario" path="emailUsuario" name="emailUsuario" class="form-control" placeholder="E-mail" disabled="true"/>
							</div>
							
							<div class="form-group">
								<label for="senha">Senha</label>
								<form:input type="password" id="senhaUsuario" path="senhaUsuario" name="senhaUsuario" class="form-control" placeholder="Senha" disabled="true"/>
							</div>
							
							<div class="form-group">
								<label for="perguntaSeguranca">Pergunta de Segurança</label>
								<form:input type="text" id="perguntaSeguranca" path="perguntaSeguranca" name="perguntaSeguranca" class="form-control" placeholder="Pergunta de Segurança" disabled="true"/>
							</div>
							
							<div class="form-group">
								<label for="respostaSeguranca">Resposta de Segurança</label>
								<form:input type="text" id="respostaSeguranca" path="respostaSeguranca" name="respostaSeguranca" class="form-control" placeholder="Resposta de Segurança" disabled="true"/>
							</div>
							
							<div class="form-group">
								<label for="tokenUsuario">Chave de Segurança</label>
								<form:input type="text" id="tokenUsuario" path="tokenUsuario" name="tokenUsuario" class="form-control" placeholder="Token" disabled="true"/>
							</div>
							
							<div class="form-group">
								<label for="nivelPermissao">Nível de Permissão</label>
								<form:select name="cmbPermissao" path="dsPermissao" class="form-control form">
									<form:option value="">Selecione</form:option>
									<form:option value="Aluno">Aluno</form:option>
									<form:option value="Professor">Professor</form:option>
									<form:option value="Coordenador">Coordenador</form:option>
									<form:option value="Administrador">Administrador</form:option>
								</form:select>
							</div>
							
							<div class="form-group">
								<label for="statusUsuario">Status do Usuário</label>								
								<form:input type="text" id="statusUsuario" path="statusUsuario" name="statusUsuario" class="form-control" placeholder="Status do Usuario" disabled="true"/>
							</div>
							
							<div class="form-group text-right">
								<button type="submit" class="btn btn-form-uniprof col-md-offset-9 col-md-3">Salvar</button>
							</div>
						</form:form>
					</div>
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