<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
</head>
<title>Administrativo ShoesHappy</title>
<style>
.custom-bg {
	height: 100vh;
	width: 100%;
	background-image: linear-gradient(-90deg, rgb(255, 255, 128),
		rgb(255, 128, 64));
}

.centered-container {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.form-container {
	margin: 20px;
	padding: 20px;
	background-color: white;
	border-radius: 10px;
}
</style>
<body class="custom-bg">
	<jsp:include page="bootstrap.jsp"></jsp:include>
	<!--<jsp:include page="navbar.jsp"></jsp:include> -->
	<!--<jsp:include page="bg.jsp"></jsp:include>-->
	<div class="centered-container">
		<div class="bg-light form-container">
			<h1 style="text-align: center;">Painel Administrativo</h1>
			<h4 style="text-align: center;">Usuários</h4>
			<div class="container">
				<br>
				<div class="d-grid gap-2 col-6 mx-auto">
					<button type="button" class="btn btn-primary border-dark"
						onclick="outraPagina()">Ir para clientes</button>
				</div>
			</div>
			<br> <a class="nav-link fw-bold"
				href="<%=request.getContextPath()%>/LoginServlet?acao=logout"
				style="float: right;"><%=request.getSession().getAttribute("usuario")%>
				- Logout</a>
			<form action="<%=request.getContextPath()%>/UserServlet"
				method="post" id="formUsuario">
				<div class="mb-3">
					<label for="formGroupExampleInput" class="form-label fw-bold">Usuário</label>
					<input type="text" name="usuario" id="usuario"
						class="form-control border-dark" placeholder="Nome do Usuário"
						value="${newUser.usuario}">
				</div>
				<div class="mb-3">
					<label for="formGroupExampleInput2" class="form-label fw-bold">Senha</label>
					<input type="password" class="form-control border-dark"
						name="senha" id="senha" placeholder="Senha do Usuário"
						value="${newUser.senha}">
				</div>
				<div class="mb-3">
					<label for="formGroupExampleInput" class="form-label fw-bold">ID</label>
					<input type="text" name="id" id="id"
						class="form-control border-dark" placeholder="Código do Usuário"
						readonly="readonly" value="${newUser.id}">
				</div>
				<input type="hidden" name="acao" id="acao" value=""> <br>
				<button type="submit" class="btn btn-success border-dark">Salvar</button>
				<button type="button" class="btn btn-info border-dark"
					onclick="limparDados();">Limpar campos</button>
				<button type="button" class="btn btn-warning border-dark"
					onclick="apagarUsuario();">Apagar</button>
				<button type="button" class="btn btn-danger border-dark"
					onclick="apagarUsuarioAjax();">Apagar Ajax</button>
				<button type="button" class="btn btn-primary border-dark"
					data-bs-toggle="modal" data-bs-target="#meuModal">Consultar
					Usuário</button>
				<div class="container">
					<br> <span id="mensagem" class="fw-bold text-primary">${msg}</span>
				</div>
			</form>

		</div>
	</div>

	<div class="modal" id="meuModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<div class="modal-header">
					<h4 class="modal-title">Consultar Usuário</h4>
					<button type="button" class="btn-close border-dark"
						data-bs-dismiss="modal"></button>
				</div>

				<div class="modal-body">
					<div class="input-group mb-3">
						<input type="text" class="form-control border-dark"
							placeholder="Nome" aria-label="nome" id="nomeBusca"
							aria-describedby="basic-addon2">
						<div class="input-group-append">
							<button class="btn btn-success border-dark" type="button"
								onclick="consultarUsuario();">Consultar</button>
						</div>
						<table class="table" id="tableUsuaio">
							<thead>
								<tr>
									<th scope="col">ID</th>
									<th scope="col">Usuário</th>
									<th scope="col">Opção</th>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>
					</div>
					<span id="totalResultados"></span>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger border-dark"
						data-bs-dismiss="modal">Fechar</button>
				</div>

			</div>
		</div>
	</div>

	<script type="text/javascript">
		function editarUsuario(id) {
			var urlAction = document.getElementById("formUsuario").action;
			window.location.href = urlAction + '?acao=buscarEditar&id=' + id;
		}

		function consultarUsuario() {
			var nomeBusca = document.getElementById('nomeBusca').value;

			if (nomeBusca != null && nomeBusca != '' && nomeBusca.trim() != '') {
				var urlAction = document.getElementById("formUsuario").action;

				$
						.ajax(
								{
									method : "get",
									url : urlAction,
									data : "nomeBusca=" + nomeBusca
											+ '&acao=consultarAjax',
									success : function(response) {

										var json = JSON.parse(response);
										$('#tableUsuaio > tbody > tr').remove();
										for (var x = 0; x < json.length; x++) {
											$('#tableUsuaio > tbody')
													.append(
															'<tr> <td>'
																	+ json[x].id
																	+ '</td><td>'
																	+ json[x].usuario
																	+ '</td><td><button onclick="editarUsuario('
																	+ json[x].id
																	+ ')" type="button" class="btn btn-info">Editar</button></tr>')
										}
										document
												.getElementById('totalResultados').textContent = 'Resultados: '
												+ json.length;
									}

								}).fail(
								function(xhr, status, errorThrown) {
									alert('Erro ao deletar usuário com Ajax: '
											+ xhr.responseText);
								});
			}
		}

		function apagarUsuarioAjax() {
			if (confirm("Deseja realemente apagar o Usuário com Ajax")) {
				var urlAction = document.getElementById("formUsuario").action;
				var idUser = document.getElementById('id').value;

				$.ajax({

					method : "get",
					url : urlAction,
					data : "id=" + idUser + '&acao=deletarajax',
					success : function(response) {

						limparDados();
						document.getElementById('msg').textContent = response;
					}

				}).fail(
						function(xhr, status, errorThrown) {
							alert('Erro ao deletar usuário com Ajax: '
									+ xhr.responseText);
						});
			}
		}

		function apagarUsuario() {
			if (confirm("Deseja realmente apagar o usuário?")) {
				document.getElementById("formUsuario").method = 'get';
				document.getElementById("acao").value = 'deletar';
				document.getElementById("formUsuario").submit();
			}
		}

		function limparDados() {
			var campus = document.getElementById("formUsuario").elements;

			for (x = 0; x < campus.length; x++) {
				campus[x].value = '';
			}
		}
		function outraPagina() {
		    window.location.href = "<%=request.getContextPath()%>/painel/clientes.jsp";
		}
	</script>
</body>
</html>