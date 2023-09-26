<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Administrativo</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
<jsp:include page="bg.jsp"></jsp:include>
</head>
<body>
	<div class="container container-rounded bg-light text-center">
		<div>
			<h1 class="fw-bold text-primary">ShoesHappy</h1>
			<h3>Painel Administrativo</h3>
		</div>
		<br>
		<div>
			<a href="login.jsp" class="btn btn-primary">Sair</a>
		</div>
		<div>
			<a class="nav-link"
				href="<%=request.getContextPath()%>/LoginServlet?acao=logout"><%=request.getSession().getAttribute("usuario")%>
				- Logout(Sair)</a>
		</div>
		<br>
	</div>
</body>
</html>