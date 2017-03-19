<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-md-3">
	<div class="profile">
		<div class="profile-sidebar">
			<div class="profile-photo">
				<img src="resources/img/user.png" height="100" width="auto" class="user-image img-responsive" alt="">
			</div>

			<div class="profile-usertitle">
				<div class="profile-usertitle-name">
					<a href="editarPerfil?cdUsuario=${usuarioLogado.cdUsuario}">${usuarioLogado.nomeUsuario.concat(' ').concat(usuarioLogado.sobrenomeUsuario)}</a>					
				</div>

				<div class="profile-usertitle-type">${usuarioLogado.dsPermissao}</div>
			</div>
		</div>
		
		<div class="profile-usermenu">
			<ul class="nav">
				
					<c:if test="${usuarioLogado.dsPermissao == 'Administrador'}">
						<li>
							<a href="#gerenciarUsuario" data-toggle="collapse"><i class="glyphicon glyphicon-pencil"></i>Gerenciar Usuário</a>
							
							<div class="collapse" id="gerenciarUsuario">
								<a href="consultarUsuario" class="list-group-item">Consultar e Manter Usuário</a>
							</div>
						</li>
					</c:if>
				
				
				
					<c:if test="${usuarioLogado.dsPermissao == 'Administrador' || usuarioLogado.dsPermissao == 'Coordenador'}">
						<li>
							<a href="#gerenciarGrupo" data-toggle="collapse"><i	class="glyphicon glyphicon-menu-right"></i>Gerenciar Grupo</a>
							
							<div class="collapse" id="gerenciarGrupo">								
								<a href="consultarGrupo" class="list-group-item">Consultar	Grupo</a>
								<a href="consultarMeusGrupos" class="list-group-item">Consultar e Manter Grupo</a>
								<a href="novoGrupo" class="list-group-item">Cadastrar Grupo</a>						
							</div>
						</li>
					</c:if>
				
				
				
					<c:if test="${usuarioLogado.dsPermissao == 'Administrador' || usuarioLogado.dsPermissao == 'Coordenador'}">
						<li>
							<a href="#gerenciarCurso" data-toggle="collapse"><i class="glyphicon glyphicon-menu-right"></i>Gerenciar Curso</a>

							<div class="collapse" id="gerenciarCurso">
								<a href="consultarCurso" class="list-group-item">Consultar Curso</a>
								<a href="consultarMeusCursos" class="list-group-item">Consultar e Manter Curso</a>
								<a href="novoCurso" class="list-group-item">Cadastrar Curso</a>
							</div>
						</li>
					</c:if>
				
				
				<c:choose>
					<c:when	test="${usuarioLogado.dsPermissao == 'Administrador'}">
						<li>
							<a href="#gerenciarCategoria" data-toggle="collapse"><i class="glyphicon glyphicon-menu-right"></i>Gerenciar Categoria</a>

							<div class="collapse" id="gerenciarCategoria">
								<a href="consultarCategoria" class="list-group-item">Consultar e Manter Categoria</a>
								<a href="novaCategoria" class="list-group-item">Cadastrar Categoria</a>
							</div>
						</li>
					</c:when>
				</c:choose>
				
				<c:choose>
					<c:when	test="${usuarioLogado.dsPermissao == 'Administrador' || usuarioLogado.dsPermissao == 'Coordenador' || usuarioLogado.dsPermissao == 'Professor'}">
						<li>
							<a href="#gerenciarQuestao" data-toggle="collapse"><i class="glyphicon glyphicon-menu-right"></i>Gerenciar Questão</a>
		
							<div class="collapse" id="gerenciarQuestao">
								<a href="consultarQuestao" class="list-group-item">Consultar Questão</a>
								<a href="consultarMinhasQuestoes" class="list-group-item">Consultar e Manter Questão</a>
								<a href="novaQuestao" class="list-group-item">Cadastrar Questão</a>
								
							</div>
						</li>
					</c:when>
				</c:choose>
				
				<c:choose>
					<c:when	test="${usuarioLogado.dsPermissao == 'Administrador' || usuarioLogado.dsPermissao == 'Coordenador' || usuarioLogado.dsPermissao == 'Professor'}">
						<li>
							<a href="#gerenciarQuestionario" data-toggle="collapse"><i class="glyphicon glyphicon-menu-right"></i>Gerenciar Questionário</a>
	
							<div class="collapse" id="gerenciarQuestionario">								
								<a href="consultarQuestionario" class="list-group-item">Consultar Questionário</a>
								<a href="consultarMeusQuestionarios" class="list-group-item">Consultar e Manter Questionário</a>
								<a href="novoQuestionario" class="list-group-item">Cadastrar Questionário</a>
							</div>
						</li>
					</c:when>
				</c:choose>
				
				<c:choose>
					<c:when	test="${usuarioLogado.dsPermissao == 'Administrador' || usuarioLogado.dsPermissao == 'Coordenador' || usuarioLogado.dsPermissao == 'Professor'}">
						<li>
							<a href="#responderQuestionario" data-toggle="collapse"><i class="glyphicon glyphicon-menu-right"></i>Gerenciar Respostas</a>
		
							<div class="collapse" id="responderQuestionario">								
								<a href="consultarGrupoCurso" class="list-group-item">Consultar e Manter Grupos/Curso</a>
							</div>
						</li>
					</c:when>
				</c:choose>
				
				<c:if test="${usuarioLogado.dsPermissao == 'Aluno'}">
					<li><a href="selecionarQuestionario" class="list-group-item">Questionários</a></li>
				</c:if>
				
				<li><a href="sair"><i class="glyphicon glyphicon-log-out"></i>Sair</a></li>
			</ul>
		</div>
	</div>
</div>