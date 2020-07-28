<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<!doctype html>
<html>

<head>
	
	<title>Register New User Form</title>
	
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	
	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	
	<style>
		.error {color:red}
	</style>
	
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
					<li class="dropdown active"><a class="dropdown-toggle"
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

<div class="mainbox" id="loginbox">
	<div class="panel panel-default">
		<div class="panel-heading"><div class="panel-title">Registrazione Nuovo utente</div>
	</div>
	
	<div  class="panel-body">
		
					<!-- Registration Form -->
					<form:form action="${pageContext.request.contextPath}/admin/processRegistrationForm" 
						  	   modelAttribute="crmUser"
						  	   class="form-horizontal">

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

						<!-- User name -->
						<form:errors path="userName" cssClass="error" color="red"/>
						<div style="margin-bottom: 25px" class="input-group col-xs-5">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
							<form:input path="userName" placeholder="username (*)" class="form-control" />
						</div>

						<!-- Password -->
						<form:errors path="password" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group col-xs-5">
							<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span> 
							<form:password path="password" placeholder="password (*)" class="form-control" />
						</div>
						
						<!-- Confirm Password -->
						<form:errors path="matchingPassword" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group col-xs-5" >
							<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span> 
							<form:password path="matchingPassword" placeholder="conferma password (*)" class="form-control" />
						</div>
					
						
						<!-- First name -->
						<form:errors path="firstName" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group col-xs-5">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
							<form:input path="firstName" placeholder="nome (*)" class="form-control" />
						</div>
						
						<!-- Last name -->
						<form:errors path="lastName" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group col-xs-5">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
							<form:input path="lastName" placeholder="cognome (*)" class="form-control" />
						</div>
						
						<!-- Email -->
						<form:errors path="email" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group col-xs-5">
							<span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span> 
							<form:input path="email" placeholder="email (*)" class="form-control" />
						</div>
						
						
						<!-- Telephone -->
						<form:errors path="telephone" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group col-xs-5">
							<span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span> 
							<form:input path="telephone" placeholder="numero di telefono (*)" class="form-control" />
						</div>
						
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
						
						
						
						
						<!-- Sede -->
						<form:errors path="Sedeid" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group">
							<label for="sede">Sede:</label>
							</br>
							<!-- Print the radiobutton sedi -->
							<c:forEach var="tempSedi" items="${sedi}">
								<form:radiobutton path="Sedeid" value="${tempSedi.id}"/> ${tempSedi.nameCity}, ${tempSedi.via},  ${tempSedi.civicNumber}, ${tempSedi.cap}.
								</br>																
							</c:forEach>
							
						</div>
						

						<!-- Register Button -->
						<div style="margin-top: 10px" class="form-group">						
							<div class="col-sm-6 controls">
								<button type="submit" class="btn btn-primary">Registra</button>
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
			
		
</div>

</body>
</html>