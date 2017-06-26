<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="custom"/>
	<title>Pontos por usuário</title>
</head>

<body>
<h4 class="spacing-bottom">Meus Registros</h4>

<div class="table-reponsive">
	<table class="table table-striped table-hover table-bordered">
		<thead>
		<tr>
			<th>Data Criação Inicial</th>
			<th>Data Registrada</th>
			<th>Ações</th>
		</tr>
		</thead>

		<tbody>
		<g:if test="${!registros}">
			<tr>
				<td colspan="3">Nenhum resultado encontrado</td>
			</tr>
		</g:if>

		<g:each in="${registros}">
			<tr>
				<td>${it.dateCreated}</td>
				<td>${it.dataMarcada}</td>
				<td class="text-center">
					<g:link controller="requisicoesMudancaRegistro" action="exibir-nova-requisicao" id="${it.id}">
						<i class="glyphicon glyphicon-file" title="Solicitar edição de ponto"></i>
					</g:link>
				</td>
			</tr>
		</g:each>
		</tbody>
	</table>
</div>
</body>