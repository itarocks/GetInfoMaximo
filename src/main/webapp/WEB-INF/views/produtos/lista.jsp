<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Livros Casa do Codigo</title>
<c:url value="/resources/css" var="cssPath" />
<link rel="stylesheet" href="${cssPath }/bootstrap.min.css">
<style type="text/css">
</style>

</head>
<body>

	 <nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="${s:mvcUrl('HC#index').build()}">Casa do Código</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#conteudoNavbarSuportado" aria-controls="conteudoNavbarSuportado" aria-expanded="false" aria-label="Alterna navegação">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="conteudoNavbarSuportado">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="${s:mvcUrl('PC#listar').build()}"> Lista de Produtos <span class="sr-only">(página atual)</span></a>
      </li>
       <li class="nav-item active">
        <a class="nav-link" href="${s:mvcUrl('PC#form').build()}">Cadastro de Produtos <span class="sr-only">(página atual)</span></a>
      </li>
    </ul>
    <ul class="nav navbar-nav nav-rigth">
    <li>
    <a href="#"> 
    	<security:authentication property="principal.username"/>
    </a>
    </li>
    </ul>
    
   <!--  <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="search" placeholder="Pesquisar" aria-label="Pesquisar">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Pesquisar</button>
    </form> -->
  </div>
</nav>

	<div class="container">
		<h1>Lista de Produtos</h1>

		<div>${sucesso }</div>
		<div>${falha }</div>
		<table class="table table-bordered table-striped table-hover">
			<tr>
				<th>Titulo</th>
				<th>Descricao</th>
				<th>Precos</th>
				<th>Páginas</th>
			</tr>
			<c:forEach items="${produtos }" var="produto">
				<tr>
					<td><a
						href="${s:mvcUrl('PC#detalhe').arg(0,produto.id).build() }">${produto.titulo }</a>
					</td>
					<td>${produto.descricao }</td>
					<td>${produto.precos }</td>
					<td>${produto.paginas }</td>

				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>