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
		<th>Data Hora</th>
		<th>Nome Usuário</th>
		<th>Ações</th>
	</tr>
	</thead>

	<tbody>
	<g:each in="${resultado}">
		<tr>
			<td>${it.dateCreated}</td>
			<td>${it.usuario.nome}</td>
			<td class="text-center">
				<g:link controller="pontos" action="editar" id="${it.id}">
					<i class="glyphicon glyphicon-pencil"></i>
				</g:link>
			</td>
		</tr>
	</g:each>
	</tbody>
</table>
</body>

</html>