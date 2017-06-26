<%@ page import="zg.augusto.UsuariosController" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="custom"/>
	<title>Pontos por usuário</title>
</head>

<body>
<h4 class="spacing-bottom">Todos usuários</h4>

<g:form class="form-inline spacing-bottom" action="listar" params="${params.remove('buscarNome')}">
	<div class="form-group">
		<label for="input-buscar-nome">Buscar por nome:</label>
		<g:textField class="form-control" id="input-buscar-nome" name="buscarNome" value="${nomeBuscado}"/>
	</div>

	<button class="btn btn-primary" type="submit">Buscar</button>
</g:form>

<div class="table-responsive">
	<table class="table table-striped table-hover table-bordered">
		<thead>
		<tr>
			<th>Nome</th>
			<th>CPF</th>
			<th>Salário</th>
			<th>Data nascimento</th>
			<th>Ações</th>
		</tr>
		</thead>

		<tbody>
		<g:if test="${resultado.size() < 1}">
			<tr><td colspan="5">Nenhum resultado encontrado</td></tr>
		</g:if>
		<g:each in="${resultado}">
			<tr>
				<td>${it.nome}</td>
				<td>${it.cpf}</td>
				<td><g:formatNumber number="${it.salario}" type="currency" currencyCode="BRL"/></td>
				<td>${it.dataNascimento}</td>
				<td class="text-center">
					<g:link controller="registros" action="usuario" id="${it.id}">
						<i class="glyphicon glyphicon-th-list" title="Verificar pontos marcados"></i>
					</g:link>
					<g:link controller="registros" action="mostrar-relatorios" id="${it.id}">
						<i class="glyphicon glyphicon-list-alt" title="Mostrar relatórios"></i>
					</g:link>
				</td>
			</tr>
		</g:each>
		</tbody>
	</table>
</div>

<zg:pagination max="${UsuariosController.MAX_USUARIOS_PER_PAGE}" total="${total}" offsetatual="${params.offset}" controller="usuarios" action="listar" />

<g:link action="exibir-cadastrar-novo" class="btn btn-primary"><i class="glyphicon glyphicon-plus"></i> Cadastrar novo</g:link>
</body>

</html>