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

	<link rel="shortcut icon" href="resources/img/Green/logo.png">
	
	<link rel="stylesheet" type="text/css" href="resources/lib/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="resources/lib/bootstrap/css/bootstrap-theme.css">
	<link rel="stylesheet" type="text/css" href="resources/css/style.css">
</head>

<body class="bg-index">	
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="text-right" style="margin: 3% 2% 0 0;">
					<a href="novoUsuario" class="btn btn-uniprofv2">Cadastrar</a>
				</div>
			
				<div class="col-md-offset-4 col-md-4" style="margin-top: 12%;">
					<div class="text-center mar-bot30">
						<img src="resources/img/White/Logo-Full.png" class="img-responsive" style="max-width: 250px;">
						<h4>Uma nova forma de aprender!</h4>
					</div>

					<form:form id="formLogin" action="login" method="POST" modelAttribute="usuario">
						<div class="form-group input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
							<form:input path="emailUsuario" class="form-control" type="text" name="email" placeholder="E-mail"/>
						</div>

						<div class="form-group input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
							<form:input path="senhaUsuario" class="form-control" type="password" name="senha" placeholder="Senha"/>
						</div>

						<div class="text-right">							
							<button type="submit" class="btn btn-uniprofv2 btn-block">Entrar</button>							
						</div>
						<div class="text-right mar-top5p">
							<a href="#" class="link" data-toggle="modal" data-target="#modal-recuperarSenha">Esqueci minha senha</a>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal Recuperar Senha -->
	<div class="modal fade" id="modal-recuperarSenha">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close">&times;</button>
					<h4 class="titulo-modal">Recuperar Senha</h4>
				</div>

				<div class="modal-body">
					<form name="formLogin" method="POST">						
						<div class="form-group">
							<label id="lbl" for="email">E-mail</label>
							<input type="text" id="email" class="form-control" placeholder="E-mail">
						</div>

						<button type="submit" class="btn btn-modal btn-block">Enviar</button>
					</form>
				</div>

				<div class="modal-footer">
					<button type="submit" class="btn btn-modal pull-left" onclick="usuarioInativo()"><span class="glyphicon glyphicon-remove"></span>Cancelar</button>
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