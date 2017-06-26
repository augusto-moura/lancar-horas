<%@ page import="java.util.Date;" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="custom"/>
	<title>Bater ponto</title>
</head>

<body>
<h4>Nova solicitação de mudança de ponto</h4>

<g:form action="cadastrar-novo">
	<fieldset>
		<div class="form-group">
			<label for="input-username">Usuário de Login:</label>
			<input class="form-control" id="input-username" name="username" value="${params.username}" type="text" required>
		</div>

		<div class="form-group">
			<label for="input-password">Senha:</label>
			<input class="form-control" id="input-password" name="password" type="password" required>
		</div>

		<div class="form-group">
			<label for="input-nome">Nome:</label>
			<input class="form-control" id="input-nome" name="nome" value="${params.nome}" type="text" required>
		</div>

		<div class="form-group">
			<label for="input-cpf">CPF:</label>
			<input class="form-control" id="input-cpf" name="cpf" value="${params.cpf}" type="text" pattern="\d{3}\.\d{3}.\d{3}-\d{2}" title="No formato xxx.xxx.xxx-xx" required>
		</div>

		<div class="form-group">
			<label for="input-data-nascimento">Data de nascimento:</label>
			<div class="form-inline zg-datepicker">
				<g:datePicker id="input-data-nascimento" name="dataNascimento" value="${params.dataNascimento ?: new Date()}" precision="day"/>
			</div>
		</div>

		<div class="form-group">
			<label for="input-salario">Salário:</label>
			<input class="form-control" id="input-salario" name="salario" value="${params.salario}" type="number" required>
		</div>

		<div class="form-group">
			<label for="input-carga-horaria">Carga horária diária:</label>
			<input class="form-control" id="input-carga-horaria" name="cargaHorariaDiaria" value="${params.cargaHorariaDiaria}" type="number" required>
		</div>
	</fieldset>

	<button class="btn btn-danger" type="reset">Limpar</button>
	<button class="btn btn-primary" type="submit">Cadastrar</button>
</g:form>
</body>

</html>