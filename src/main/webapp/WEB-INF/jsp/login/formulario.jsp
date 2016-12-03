<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css">
<title>Formulário de Login</title>
</head>
<body>

	<div class="container">
		
		<form action="<c:url value='/login/autentica'/>" method="post">
			Login: <input type="text" class="form-control" name="usuario.nome">
			Senha: <input type="password" class="form-control" name="usuario.senha">
			<button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
		</form>
		
		<c:if test="${not empty errors}">
			<div>
				<c:forEach var="error" items="${errors}">
					${error.category} - ${error.message} <br/>
				</c:forEach>
			</div>
		</c:if>
		
	</div>

</body>
</html>