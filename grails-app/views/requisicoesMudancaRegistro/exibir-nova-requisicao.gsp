<%@ page import="java.text.SimpleDateFormat;" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="custom"/>
	<title>Bater ponto</title>
</head>

<body>
<h4>Nova solicitação de mudança de ponto</h4>

<g:form action="nova-requisicao" id="${params.id}">
	<fieldset>
		<div class="form-group">
			<label for="data-antiga">Marcação Atual:</label>
			<input class="form-control"
				   id="data-antiga"
				   type="datetime-local"
				   value="${new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(registro.dataMarcada)}"
				   readonly/>
		</div>

		<div class="form-group">
			<label for="nova-data">Nova marcação:</label>
			<input class="form-control"
				   id="nova-data"
				   name="dataMudanca"
				   type="datetime-local"
				   value="${new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(registro.dataMarcada)}"/>
		</div>

		<div class="form-group">
			<label for="justificativa">Justificativa:</label>
			<textarea class="form-control zg-textarea" id="justificativa" name="justificativa" rows="3"></textarea>
		</div>
	</fieldset>

	<button class="btn btn-danger" type="reset">Limpar</button>
	<button class="btn btn-primary" type="submit">Enviar Solicitação</button>
</g:form>
</body>

</html>