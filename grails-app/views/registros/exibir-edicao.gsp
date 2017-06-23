<%@ page import="java.text.SimpleDateFormat;" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="custom"/>
	<title>Pontos por usuário</title>
</head>

<body>
<h4 class="spacing-bottom">Editar registro</h4>

<g:form name="formEdicao" class="form-inline" controller="registros" action="editar-data-marcada" id="${params.id}">
	<div class="form-group">
		<label for="input-data">Nova data:</label>
		<input class="form-control" id="input-data" name="dataMarcada" type="datetime-local"
			   value="${new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(entidade.dataMarcada)}"/>
	</div>

	<button class="btn btn-primary" type="submit">Salvar</button>
</g:form>
</body>

</html>