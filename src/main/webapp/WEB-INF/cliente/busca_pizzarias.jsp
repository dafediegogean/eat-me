<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>E aí, qual Pizza hoje?</title>

<c:set var="path" value="${pageContext.request.contextPath }" scope="request"></c:set>
<style type="text/css">
	@import url("${path}/static/bootstrap/css/bootstrap.min.css");
	@import url("${path}/static/bootstrap/css/bootstrap-theme.min.css");
	
	#btn-buscar{
		margin-top: 23px
	}
</style>
</head>
<body>

	<div class="container">
		<jsp:include page="../menu-cliente.jsp" />
		<section>
			<div id="consulta-pizzarias" class="well">
				<div class="row">
					<div class="col-sm-4">
						<label for="pizza_pesquisa">Que Pizza você quer comer hoje?</label>
							<select id="pizza_pesquisa" class="form-control">
								<c:forEach items="${nomesPizzas}" var="nomePizza">
									<option value="${nomePizza}">${nomePizza}</option>
								</c:forEach>
						</select>
					</div>
					<div class="col-sm-4">
						<button id="btn-buscar" class="btn btn-primary">Buscar pizzarias</button>
					</div>
				</div>
			</div>
			<div id="secao-pizzarias"></div>
		</section>
	</div>
	<script type="text/javascript" src="${path}/static/js/jquery-2.1.3.min.js"></script>
	<script type="text/javascript" src="${path}/static/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${path}/static/js/pizzaria.js"></script>
</body>
</html>