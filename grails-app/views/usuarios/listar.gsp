<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="custom"/>
	<title>Pontos por usuário</title>
</head>

<body>
<h4 class="spacing-bottom">Todos usuários</h4>

<g:form class="form-inline spacing-bottom">
	<div class="form-group">
		<label for="input-buscar-nome">Buscar por nome:</label>
		<g:textField class="form-control" id="input-buscar-nome" name="buscarNome" value="${nomeBuscado}" />
	</div>

	<button class="btn btn-primary" type="submit">Buscar</button>
</g:form>

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
				<td>R$ ${it.salarioFormatado}</td>
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
</body>

</html>