<%@ page import="java.text.SimpleDateFormat;" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="custom"/>
	<title>Pontos por usuário</title>
</head>

<body>
<g:form class="form-inline" controller="pontos" action="alterar">
	<div class="form-group">
		<label for="input-data">Nova data:</label>
		<input class="form-control"
			   id="input-data"
			   name="dateCreated"
			   type="datetime-local"
			   value="${new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(entidade.dataMarcada)}"/>
	</div>

	<button class="btn btn-primary" type="submit">Salvar</button>
</g:form>
</body>

</html>