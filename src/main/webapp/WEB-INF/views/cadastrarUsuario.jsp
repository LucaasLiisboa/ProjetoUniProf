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

<body class="bg-index">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="text-left" style="margin: 3% 0 0 2%;">
					<a href="voltar" class="btn btn-uniprofv2">Voltar</a>
				</div>
				
				<div class="col-md-offset-4 col-md-4" style="margin-top: 12%;">
					<div class="text-center mar-bot30">
						<img src="resources/img/White/Logo-Full.png" class="img-responsive" style="max-width: 200px;">
						<h2>Cadastrar Usuário</h2>
					</div>
					
					<form:form class="form-horizontal" action="cadastrarUsuario" method="POST" modelAttribute="usuario">
						<div class="form-group">
							<label id="lblCadastroUsuario" for="nome">Nome</label>
							<form:input type="text" id="nomeUsuario" path="nomeUsuario" name="nomeUsuario" class="form-control" placeholder="Nome"/>
						</div>
						
						<div class="form-group">
							<label id="lblCadastroUsuario" for="sobrenome">Sobrenome</label>
							<form:input type="text" id="sobrenomeUsuario" path="sobrenomeUsuario" name="sobrenomeUsuario" class="form-control" placeholder="Sobrenome"/>
						</div>
							
						<div class="form-group">
							<label id="lblCadastroUsuario" for="email">E-mail</label>
							<form:input type="text" id="emailUsuario" path="emailUsuario" name="emailUsuario" class="form-control" placeholder="E-mail"/>
						</div>
							
						<div class="form-group">
							<label id="lblCadastroUsuario" for="senha">Senha</label>
							<form:input type="password" id="senhaUsuario" path="senhaUsuario" name="senhaUsuario" class="form-control" placeholder="Senha"/>
						</div>
						
						<div class="form-group">
							<button type="submit" class="btn btn-uniprofv2 btn-block">Cadastrar</button>					
						</div>
					</form:form>
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