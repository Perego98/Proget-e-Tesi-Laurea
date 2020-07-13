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
								href="${pageContext.request.contextPath}/admin/showListUsers">Elenco
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
							<li><a href="#">Ruolo 1</a></li>
							<li><a href="#">Ruolo 2</a></li>
						</ul></li>
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
	
	
<div class="panel panel-default">
		<div class="panel-heading"><h2>Aggiorna dati <b>${user.userName}</b></h2></div>
		<div  class="panel-body">

		
		<form:form action="${pageContext.request.contextPath}/admin/processUpdateUserForm?userUsername=${user.userName}"  modelAttribute="user" method="POST">
		
			<!-- need to associate this data with customer id -->
			<form:hidden path="userName"/>
		
			<!-- Place for messages: error, alert etc ... -->
		    <div class="form-group">
		        <div class="col-xs-15">
		            <div>
					
						<!-- Check for registration error -->
						<c:if test="${registrationError != null}">
					
							<div class="alert alert-danger col-xs-offset-1 col-xs-10">
								${registrationError}
							</div>

						</c:if>
																
		            </div>
		        </div>
		    </div>
		    
		    <!-- First name -->
			<form:errors path="firstName" cssClass="error" />
			<label for="sede">Nome:</label>
			<div style="margin-bottom: 5px" class="input-group col-xs-5">
				<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
				<form:input path="firstName" placeholder="first name (*)" class="form-control" />
			</div>
			
			<!-- Last name -->
			<form:errors path="lastName" cssClass="error" />
			<label for="sede">Cognome:</label>
			<div style="margin-bottom: 5px" class="input-group col-xs-5">
				<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
				<form:input path="lastName" placeholder="last name (*)" class="form-control" />
			</div>
			
			<!-- Email -->
			<form:errors path="email" cssClass="error" />
			<label for="sede">Email:</label>
			<div style="margin-bottom: 5px" class="input-group col-xs-5">
				<span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span> 
				<form:input path="email" placeholder="email (*)" class="form-control" />
			</div>
			
			
			<!-- Telephone -->
			<form:errors path="telephone" cssClass="error" />
			<label for="sede">Telefono:</label>
			<div style="margin-bottom: 5px" class="input-group col-xs-5">
				<span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span> 
				<form:input path="telephone" placeholder="telephone number (*)" class="form-control" />
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
			<input type="button" value="Back"
							onclick="window.location.href='${pageContext.request.contextPath}/admin/showListUsers'; return false;"
							class="btn btn-primary" />
		</div>
		

		
		
	</div>

</body>

</html>
