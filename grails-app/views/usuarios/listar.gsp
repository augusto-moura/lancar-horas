<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="custom"/>
	<title>Pontos por usuário</title>
</head>

<body>
<table class="table table-striped table-hover table-bordered minimun-width-table">
	<thead>
	<tr>
		<th>Nome</th>
		<th>CPF</th>
		<th>Data nascimento</th>
		<th>Ações</th>
	</tr>
	</thead>

	<tbody>
	<g:each in="${resultado}">
		<tr>
			<td>${it.nome}</td>
			<td>${it.cpf}</td>
			<td>${it.dataNascimento}</td>
			<td class="text-center">
				<g:link controller="pontos" action="usuario" id="${it.id}">
					<i class="glyphicon glyphicon-th-list" title="Verificar pontos marcados"></i>
				</g:link>
			</td>
		</tr>
	</g:each>
	</tbody>
</table>
</body>

</html>