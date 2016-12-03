<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>VRaptor Produtos</title>
</head>
<body>

<h4>Listagem de Produtos do ${usuarioLogado.usuario.nome}</h4>

<a href="<c:url value='/produto/formulario'/>">>> Adicionar mais produtos</a><br/>
<a href="<c:url value='/produto/listaXML'/>">>> Lista de produtos em Xml</a><br/>
<a href="<c:url value='/produto/listaJSON'/>">>> Lista de produtos em Json</a>

<table class="table table-striped table-bordered table-hover">
	<tr>
		<td>Nome</td>
		<td>Valor</td>
		<td>Quantidade</td>
		<td>Ação</td>
		<td>Email</td>
	</tr>
	<c:forEach var="produto" items="${produtoList}">
	<tr>
		<td>${produto.nome}</td>
		<td>${produto.valor}</td>
		<td>${produto.quantidade}</td>
		<td><a href="<c:url value='/produto/remove?produto.id=${produto.id}'/>">Remover</a></td>
		<td><a href="<c:url value='/produto/enviaPedidoDeNovosItens?produto.nome=${produto.nome}'/>">Pedir mais itens</a></td>
	</tr>
	</c:forEach>
</table>
<c:if test="${not empty mensagem}">
    <div class="alert alert-success">${mensagem}</div>
</c:if>
</body>
</html>