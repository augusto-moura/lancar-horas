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
<h4 class="spacing-bottom">Todas requisições</h4>

<div class="table-responsive">
	<table class="table table-striped table-hover table-bordered tabela-listar-requisicoes">
		<thead>
		<tr class="text-nowrap">
			<th>Usuário</th>
			<th>Data Original</th>
			<th>Data Solicitada</th>
			<th style="width: 100%">Justificativa</th>
			<th>Ações</th>
		</tr>
		</thead>

		<tbody>
		<g:if test="${!requisicoes}">
			<tr>
				<td colspan="5">Nenhum resultado encontrado</td>
			</tr>
		</g:if>

		<g:each in="${requisicoes}">
			<tr>
				<td class="text-center">${it.registro.usuario.nome}</td>
				<td class="text-nowrap">${it.registro.dataMarcada}</td>
				<td class="text-nowrap">${it.dataMudanca}</td>
				<td>
					<g:if test="${it.justificativa.size() >= 100}">
						${it.justificativa.substring(0, 100)}<span class="zg-expand-text hide">${it.justificativa.substring(101)}</span>
						<div class="zg-expand-link"><a href="javascript: void 0" title="Mostrar mais">mais...</a></div>
					</g:if>
					<g:else>
						${it.justificativa}
					</g:else>
				</td>
				<td class="text-center">
					<g:link controller="requisicoesMudancaRegistro" action="aceitar-requisicao" id="${it.id}">
						<i class="glyphicon glyphicon-ok" title="Aceitar solicitação de mudança de ponto"></i>
					</g:link>
					<g:link controller="requisicoesMudancaRegistro" action="recusar-requisicao" id="${it.id}">
						<i class="glyphicon glyphicon-remove" title="Rejeitar solicitação de mudança de ponto"></i>
					</g:link>
				</td>
			</tr>
		</g:each>
		</tbody>
	</table>
</div>

<script>
    $(() => {
        $('.zg-expand-link').on('click', function () {
            const $this = $(this);

            $this.siblings('.zg-expand-text').removeClass('hide');
            $this.remove();
        });
    });
</script>
</body>