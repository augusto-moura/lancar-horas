<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="custom"/>
	<title>Pontos por usuário</title>
	<style type="text/css">
	.tabela-listar-requisicoes td {
		vertical-align: middle !important;
	}
	</style>
</head>

<body>
<h4 class="spacing-bottom">Meus Registros</h4>

<table class="table table-striped table-hover table-bordered tabela-listar-requisicoes">
	<thead>
	<tr class="text-nowrap">
		<th>Usuario</th>
		<th>Data Original</th>
		<th>Data Requisitada</th>
		<th style="width: 100%">Justificativa</th>
		<th>Ações</th>
	</tr>
	</thead>

	<tbody>
	<g:if test="${!requisicoes}">
		<tr>
			<td colspan="4">Nenhum resultado encontrado</td>
		</tr>
	</g:if>

	<g:each in="${requisicoes}">
		<tr>
			<td>${it.registro.usuario.nome}</td>
			<td class="text-nowrap">${it.registro.dataMarcada}</td>
			<td class="text-nowrap">${it.dataMudanca}</td>
			<td>
				<g:if test="${it.justificativa.size() >= 100}">
					${it.justificativa.substring(0, 100)}
					<a class="zg-expand-link" href="javascript: void 0">...</a>
					<span class="zg-expand-text hide">${it.justificativa.substring(101)}</span>
				</g:if>
				<g:else>
					${it.justificativa}
				</g:else>
			</td>
			<td class="text-center">
				<g:link controller="requisicoesMudancaRegistro" action="exibir-nova-requisicao" id="${it.id}">
					<i class="glyphicon glyphicon-file" title="Solicitar edição de ponto"></i>
				</g:link>
			</td>
		</tr>
	</g:each>
	</tbody>
</table>

<script>
    $(() => {
        $('.zg-expand-link').on('click', function () {
            const $this = $(this);

            $this.sibling('.zg-expand-text').removeClass('hide')
            $this.remove();
        });
    });
</script>
</body>