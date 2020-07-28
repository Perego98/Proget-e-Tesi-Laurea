<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<!DOCTYPE html>
<html>
<head>
	<title>Save Customer</title>
	
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	
	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	
	<!-- Add icon library -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
	
</head>
<body>

	<!-- Se l'account non è autorizzato viene rimandato alla home -->
	<c:if test="${user.qualified == false}">
		<meta http-equiv="refresh"
			content="0; url = http://localhost:8080/gestione-candidature/" />
	</c:if>

<!-- Start NAV BAR -->
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="${pageContext.request.contextPath}/">Gestione
					Candidature</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a
					href="${pageContext.request.contextPath}/">Home</a></li>

				<security:authorize access="hasRole('ADMIN')">
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">Admin<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a
								href="${pageContext.request.contextPath}/admin/showRegistrationForm">Registra
									Utente</a></li>
							<li><a
								href="${pageContext.request.contextPath}/admin/showListUsersPagination">Elenco
									Utenti</a></li>
						</ul></li>
				</security:authorize>


				<security:authorize access="hasRole('HR')">
					<li class="dropdown active"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">HR<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a
								href="${pageContext.request.contextPath}/hr/showCandidatoRegistrationForm">Registra
									Candidato</a></li>
							<li><a
								href="${pageContext.request.contextPath}/hr/showListCandidati">Elenco
									Candidati</a></li>
						</ul></li>
				</security:authorize>

				<security:authorize access="hasRole('MANAGER')">
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">Manager<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a
									href="${pageContext.request.contextPath}/manager/showListCandidati">Elenco
										Candidati associati</a></li>
							
						</ul></li>
				</security:authorize>

			</ul>

			<ul class="nav navbar-nav navbar-right">

				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Azioni<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="${pageContext.request.contextPath}/logout" >Logout</a></li>
					</ul></li>
			</ul>
		</div>
	</nav>
	<!-- END NAV BAR -->
	
	
	<!-- Check for registration error -->
<c:if test="${registrationError != null}">

	<div class="alert alert-danger col-xs-offset-1 col-xs-10">
		<strong>Attenzione! </strong>${registrationError}
	</div>
	<br>
</c:if>
	
<div class="panel panel-default">
		<div class="panel-heading">
		<h2>Aggiorna dati <b>${userUsername}</b></h2>
		<p>Ruolo Attuale: ${ruoloAttuale}</p>
		</div>
		<div  class="panel-body">

		
		<form:form action="${pageContext.request.contextPath}/admin/processUpdateUserRoleForm?userUsername=${userUsername}"  modelAttribute="crmRole" method="POST">
				
			
			<!-- Role -->
			<form:errors path="idRole" cssClass="error" />
			<div style="margin-bottom: 25px" class="input-group col-xs-5">
				<!-- <span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span> -->
				
				<label for="ruolo">Ruolo:</label>
				</br>
				
					<!-- Print the radiobutton role -->
					<c:forEach var="tempRole" items="${roles}">
							<form:radiobutton path="idRole" value="${tempRole.name}"/> ${tempRole.name}		
							<br>											
					</c:forEach>							
			</div>

				<!-- Update Button -->
			<div style="margin-top: 10px" class="form-group">						
				<div class="controls">
					<button type="submit" class="btn btn-success">Aggiorna</button>
				</div>
			</div>
			
			

		</form:form>

		</div>
			
		<div class="panel-footer">
			<input type="button" value="Indietro"
							onclick="window.location.href='${pageContext.request.contextPath}/admin/showListUsers'; return false;"
							class="btn btn-primary" />
		</div>
		

		
		
	</div>

</body>

</html>
