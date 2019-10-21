<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Livros Casa do Codigo</title>

<c:url value="/resources/css" var="cssPath" />
<link rel="stylesheet" href="${cssPath }/bootstrap.min.css">
<link rel="stylesheet" href="${cssPath }/bootstrap-theme.min.css">
<style type="text/css">
body{

	padding: 0px 0px;
}

</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light"> <a
		class="navbar-brand" href="${s:mvcUrl('HC#index').build()}">Casa
		do Código</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#conteudoNavbarSuportado"
		aria-controls="conteudoNavbarSuportado" aria-expanded="false"
		aria-label="Alterna navegação">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="conteudoNavbarSuportado">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link"
				href="${s:mvcUrl('PC#listar').build()}"> Lista de Produtos <span
					class="sr-only">(página atual)</span></a></li>
			<li class="nav-item active"><a class="nav-link"
				href="${s:mvcUrl('PC#form').build()}">Cadastro de Produtos <span
					class="sr-only">(página atual)</span></a></li>
		</ul>
		<form class="form-inline my-2 my-lg-0">
			<input class="form-control mr-sm-2" type="search"
				placeholder="Pesquisar" aria-label="Pesquisar">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Pesquisar</button>
		</form>
	</div>
	</nav>

	<div class="container">
	<h1>Cadastro de Produto</h1>
	<form:form action="${s:mvcUrl('PC#gravar').build() }" method="POST"
		commandName="produto" enctype="multipart/form-data" >
		<div class="form-group">
			<label>Titulo</label>
			<form:input path="titulo" cssClass="form-control" />
			<form:errors path="titulo" />

		</div>
		<div class="form-group">
			<label>Descriçao</label>
			<form:textarea  path="descricao" cssClass="form-control" />
			<form:errors path="descricao" />
		</div>
		<div class="form-group">
			<label>Páginas</label>
			<form:input path="paginas" cssClass="form-control" />
			<form:errors path="paginas" />

		</div>
		<div class="form-group">
			<label>Data de Lançamento</label>
			<form:input path="dataLancamento" cssClass="form-control" />
			<form:errors path="dataLancamento" />
		</div>
		<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
			<div class="form-group">
				<label>${tipoPreco }</label>
				<form:input path="precos[${status.index}].valor" cssClass="form-control" />
				<form:hidden path="precos[${status.index}].tipo"
					value="${tipoPreco}" />
			</div>
		</c:forEach>
		<div class="form-group">
			<label> Sumário </label> <input name="sumario" type="file" class="form-control"
				id="sumario">
		</div>
		<button type="submit" class="btn btn-primary">Cadastrar</button>
	</form:form>
	</div>
</body>
</html>