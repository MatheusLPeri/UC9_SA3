<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ShoesHappy</title>
<jsp:include page="painel/bootstrap.jsp"></jsp:include>

</head>
<style>
.custom-bg {
	height: 100vh;
	width: 100%;
	background-image: linear-gradient(-90deg, rgb(255, 255, 128),
		rgb(255, 128, 64));
}

.container-rounded {
	max-width: 450px;
	border-radius: 10px;
	margin: 50px auto;
}
</style>
<body
	class="custom-bg d-flex justify-content-center align-items-center vh-100">
	<div class="container container-rounded bg-light">
		<br>
		<h1>Cadastre-se!</h1>
		<br>
		<form class="custom-form" action="LoginServlet" method="post">

			<div class="form-group">
				<label for="nome" class="fw-bold">Nome</label> <input type="text"
					class="form-control border-dark" id="nome" name="nome" required>
				<br>
			</div>

			<div class="form-group">
				<label for="matricula" class="fw-bold">Matrícula</label> <input
					type="text" class="form-control border-dark" id="matricula"
					name="matricula" required> <br>
			</div>

			<div class="form-group">
				<label for="endereco" class="fw-bold">Endereço</label> <input
					type="text" class="form-control border-dark" id="endereco"
					name="endereco" required> <br>
			</div>

			<div class="form-group">
				<label for="senha" class="fw-bold">Senha</label> <input
					type="password" class="form-control border-dark" id="senha"
					name="senha" required> <br>
			</div>

			<div class="form-group">
				<label for="modalidade" class="fw-bold">Modalidade</label> <select
					class="form-control border-dark" id="modalidade" name="modalidade">
					<option value="">Selecione a modalidade</option>
					<option value="cpf">Pessoa física (CPF)</option>
					<option value="cnpj">Pessoa jurídica (CNPJ)</option>
				</select> <br>
			</div>

			<div class="form-group" id="cpfField" style="display: none;">
				<label for="cpf" class="fw-bold">CPF</label> <input type="text"
					class="form-control border-dark" id="cpf" name="cpf"> <br>
			</div>

			<div class="form-group" id="cnpjField" style="display: none;">
				<label for="cnpj" class="fw-bold">CNPJ</label> <input type="text"
					class="form-control border-dark" id="cnpj" name="cnpj"> <br>
			</div>

			<br>
			<div class="d-grid gap-2 col-6 mx-auto">
				<button type="submit" class="btn btn-primary border-dark"
					type="button">Continuar</button>
			</div>
			<br>
			
		</form>
		<div class="d-flex justify-content-center align-items-center">
			<h5>${msg}</h5>
		</div>
	</div>

	<script>
		document.getElementById('modalidade').addEventListener('change',
				function() {
					var cpfField = document.getElementById('cpfField');
					var cnpjField = document.getElementById('cnpjField');

					if (this.value === 'cpf') {
						cpfField.style.display = 'block';
						cnpjField.style.display = 'none';
					} else if (this.value === 'cnpj') {
						cpfField.style.display = 'none';
						cnpjField.style.display = 'block';
					} else {
						cpfField.style.display = 'none';
						cnpjField.style.display = 'none';
					}
				});
	</script>
</body>
</html>