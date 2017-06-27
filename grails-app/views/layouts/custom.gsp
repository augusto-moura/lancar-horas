<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title><g:layoutTitle default="Principal"/> - Lançar Horas</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<asset:stylesheet src="application.css"/>
	<asset:javascript src="application.js"/>
	<g:layoutHead/>
</head>

<body>
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<g:link uri="/" class="navbar-brand">ZeroPontos</g:link>
		</div>

		<ul class="nav navbar-nav">
			<li><g:link controller="registros" action="exibir-bater-ponto" class="zg-link">Bater ponto</g:link></li>
			<li><g:link controller="registros" action="meus-registros" class="zg-link">Minhas marcações</g:link></li>
			<li><g:link controller="relatorios" action="meu-relatorio" class="zg-link">Meu relatório</g:link></li>
			<sec:ifAllGranted roles="ROLE_ADMIN">
				<li><g:link controller="usuarios" action="listar" class="zg-link">Usuários</g:link></li>
				<li>
					<g:link controller="requisicoesMudancaRegistro" action="listar-requisicoes" class="zg-link">
						Requisições Mudança de Ponto
					</g:link>
				</li>
			</sec:ifAllGranted>
		</ul>

		<ul class="nav navbar-nav navbar-right">
			<li><a href=""><i class="glyphicon glyphicon-wrench"></i> Configurações</a></li>
			<li><g:link controller="logout"><i class="glyphicon glyphicon-off"></i> Sair (<sec:username/>)</g:link></li>
		</ul>
	</div>
</nav>

<main class="container-fluid zg-principal">
	<g:each in="${flash.errors}">
		<div class="alert alert-danger overflow-auto" role="alert">${it}</div>
	</g:each>

	<g:each in="${flash.success}">
		<div class="alert alert-success overflow-auto">${it}</div>
	</g:each>

	<div>
		<g:layoutBody/>
	</div>
</main>

<footer class="footer" role="contentinfo"></footer>
</body>

</html>
