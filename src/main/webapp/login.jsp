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
		<h1>Entrar</h1>
		<br>
		<form class="custom-form" action="LoginServlet" method="post">

			<div class="form-group">
				<label for="nome" class="fw-bold">Nome</label> <input type="text"
					class="form-control border-dark" id="nome" name="nome" required>
				<br>
			</div>

			<div class="form-group">
				<label for="senha" class="fw-bold">Senha</label> <input
					type="password" class="form-control border-dark" id="senha"
					name="senha" required> <br>
			</div>
			<br>

			<div class="d-grid gap-2 col-6 mx-auto">
				<button type="submit" class="btn btn-primary border-dark"
					type="button">Continuar</button>
			</div>
			<br>

		</form>
		<div class="d-flex justify-content-center align-items-center">
			<h5 class="text-danger">${msg}</h5>
		</div>
		<br>
	</div>
</body>
</html>