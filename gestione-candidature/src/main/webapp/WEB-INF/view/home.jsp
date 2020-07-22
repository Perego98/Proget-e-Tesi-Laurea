<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<html>

<head>
	<title>Home Page</title>
	
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	
	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	
</head>

<body>
	
	<!-- Start NAV BAR -->
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="${pageContext.request.contextPath}/">Gestione
					Candidature</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a
					href="${pageContext.request.contextPath}/">Home</a></li>

				<security:authorize access="hasRole('ADMIN')">
					<c:if test="${user.qualified == true}">
						<li class="dropdown"><a class="dropdown-toggle"
							data-toggle="dropdown" href="#">Admin<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a
									href="${pageContext.request.contextPath}/admin/showRegistrationForm">Registra
										Utente</a></li>
								<li><a
									href="${pageContext.request.contextPath}/admin/showListUsers">Elenco
										Utenti</a></li>
							</ul></li>
					</c:if>
				</security:authorize>


				<security:authorize access="hasRole('HR')">
					<c:if test="${user.qualified == true}">
						<li class="dropdown"><a class="dropdown-toggle"
							data-toggle="dropdown" href="#">HR<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a
									href="${pageContext.request.contextPath}/hr/showCandidatoRegistrationForm">Registra
										Candidato</a></li>
								<li><a
									href="${pageContext.request.contextPath}/hr/showListCandidati">Elenco
										Candidati</a></li>
							</ul></li>
					</c:if>
				</security:authorize>

				<security:authorize access="hasRole('MANAGER')">
					<c:if test="${user.qualified == true}">
						<li class="dropdown"><a class="dropdown-toggle"
							data-toggle="dropdown" href="#">Manager<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a
									href="${pageContext.request.contextPath}/manager/showListCandidati">Elenco
										Candidati associati</a></li>
								<li><a href="#">Ruolo 2</a></li>
							</ul></li>
					</c:if>
				</security:authorize>

			</ul>

			<ul class="nav navbar-nav navbar-right">

				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Azioni<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<form:form action="${pageContext.request.contextPath}/logout"
							method="POST">
							<input class="btn btn-danger navbar-btn" type="submit"
								value="Logout" />
						</form:form>
					</ul></li>
			</ul>
		</div>
	</nav>
	<!-- END NAV BAR -->
	
	
	<!-- Avviso account disattivato -->
	<c:if test="${user.qualified == false}">
		<div class="alert alert-warning">
				<strong>Attenzione!</strong> Questo account è stato disattivato. Contatta l'amministratore per riattivarlo
		</div>
	</c:if>
	
	<!-- display Account info -->
	<div class="panel panel-default">
	
		<div class="panel-heading"><h4>Info Account</h4></div>
		
		<div  class="panel-body">
			
			<table class="table table-striped">
						<tr>
							<th>Username</th>
							<td><security:authentication property="principal.username" /></td>
						</tr>
						  
						<tr>
							<th>Ruolo</th>
							<td><security:authentication property="principal.authorities" /></td>
						</tr>
						<tr>
							<th>Nome</th>
							<td>${user.firstName}</td>
						</tr>
						<tr>
							<th>Cognome</th>
							<td> ${user.lastName}</td>
						</tr>
						<tr>
							<th>Email</th>
							<td>${user.email}</td>
						</tr>
						<tr>
							<th>Telefono</th>
							<td>${user.telephone}</td>
						</tr>
						<tr>
							<th>Sede</th>
							<td>${user.sedeAssegnamento}</td>
						</tr>


					</table>
		
		</div>
		
		
	</div>

</body>

</html>









