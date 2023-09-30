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
			<h4 style="text-align: center;">Clientes</h4>
			<div class="container">
				<br>
				<div class="d-grid gap-2 col-6 mx-auto">
					<button type="button" class="btn btn-primary border-dark"
						onclick="outraPagina()">Ir para usuários</button>
				</div>
			</div>
			<br> <a class="nav-link fw-bold"
				href="<%=request.getContextPath()%>/LoginServlet?acao=logout"
				style="float: right;"><%=request.getSession().getAttribute("usuario")%>
				- Logout</a>
			<form action="<%=request.getContextPath()%>/ClienteServlet"
				method="post" id="formCliente">
				<div class="mb-3">
					<label for="formGroupExampleInput" class="form-label fw-bold">ID</label>
					<input type="text" name="id" id="id"
						class="form-control border-dark" placeholder="Código do cliente"
						readonly="readonly" value="${newCliente.id}">
				</div>
				<div class="mb-3">
					<label for="formGroupExampleInput" class="form-label fw-bold">Nome</label>
					<input type="text" name="nome" id="nome"
						class="form-control border-dark" placeholder="Nome do Cliente"
						value="${newCliente.nome}">
				</div>
				<div class="mb-3">
					<label for="formGroupExampleInput2" class="form-label fw-bold">Endereço</label>
					<input type="text" class="form-control border-dark" name="endereco"
						id="endereco" placeholder="Endereço do cliente"
						value="${newCliente.endereco}">
				</div>
				<div class="mb-3">
					<label for="formGroupExampleInput2" class="form-label fw-bold">Modalidade</label>
					<input type="text" class="form-control border-dark"
						name="modalidade" id="modalidade"
						placeholder="Selecione modalidade"
						value="${newCliente.modalidade}">
				</div>
				<div class="mb-3">
					<label for="formGroupExampleInput2" class="form-label fw-bold">Cpf/Cnpj</label>
					<input type="text" class="form-control border-dark" name="cpf"
						id="cpf" placeholder="Cpf/Cnpj do cliente"
						value="${newCliente.cpf}">
				</div>
				<input type="hidden" name="acao" id="acao" value=""> <br>
				<button type="submit" class="btn btn-success border-dark">Salvar</button>
				<button type="button" class="btn btn-info border-dark"
					onclick="limparDados();">Limpar campos</button>
				<button type="button" class="btn btn-warning border-dark"
					onclick="apagarCliente();">Apagar</button>
				<button type="button" class="btn btn-danger border-dark"
					onclick="apagarClienteAjax();">Apagar Ajax</button>
				<button type="button" class="btn btn-primary border-dark"
					data-bs-toggle="modal" data-bs-target="#meuModal">Consultar
					Cliente</button>
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
					<h4 class="modal-title">Consultar Cliente</h4>
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
								onclick="consultarCliente();">Consultar</button>
						</div>
						<table class="table" id="tableCliente">
							<thead>
								<tr>
									<th scope="col">ID</th>
									<th scope="col">Cliente</th>
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
		function editarCliente(id) {
			var urlAction = document.getElementById("formCliente").action;
			window.location.href = urlAction + '?acao=buscarEditar&id=' + id;
		}

		function consultarCliente() {
			var nomeBusca = document.getElementById('nomeBusca').value;

			if (nomeBusca != null && nomeBusca != '' && nomeBusca.trim() != '') {
				var urlAction = document.getElementById("formCliente").action;

				$
						.ajax(
								{
									method : "get",
									url : urlAction,
									data : "nomeBusca=" + nomeBusca
											+ '&acao=consultarAjax',
									success : function(response) {

										var json = JSON.parse(response);
										$('#tableCliente > tbody > tr').remove();
										for (var x = 0; x < json.length; x++) {
											$('#tableCliente > tbody')
													.append(
															'<tr> <td>'
																	+ json[x].id
																	+ '</td><td>'
																	+ json[x].cliente
																	+ '</td><td><button onclick="editarCliente('
																	+ json[x].id
																	+ ')" type="button" class="btn btn-info">Editar</button></tr>')
										}
										document
												.getElementById('totalResultados').textContent = 'Resultados: '
												+ json.length;
									}

								}).fail(
								function(xhr, status, errorThrown) {
									alert('Erro ao deletar cliente com Ajax: '
											+ xhr.responseText);
								});
			}
		}

		function apagarClienteAjax() {
			if (confirm("Deseja realemente apagar o cliente com Ajax")) {
				var urlAction = document.getElementById("formCliente").action;
				var idCliente = document.getElementById('id').value;

				$.ajax({

					method : "get",
					url : urlAction,
					data : "id=" + idCliente + '&acao=deletarajax',
					success : function(response) {

						limparDados();
						document.getElementById('msg').textContent = response;
					}

				}).fail(
						function(xhr, status, errorThrown) {
							alert('Erro ao deletar cliente com Ajax: '
									+ xhr.responseText);
						});
			}
		}

		function apagarCliente() {
			if (confirm("Deseja realmente apagar o cliente?")) {
				document.getElementById("formCliente").method = 'get';
				document.getElementById("acao").value = 'deletar';
				document.getElementById("formCliente").submit();
			}
		}

		function limparDados() {
			var campus = document.getElementById("formCliente").elements;

			for (x = 0; x < campus.length; x++) {
				campus[x].value = '';
			}
		}
		function outraPagina() {
		    window.location.href = "<%=request.getContextPath()%>/painel/inicio.jsp";
		}
	</script>
</body>
</html>