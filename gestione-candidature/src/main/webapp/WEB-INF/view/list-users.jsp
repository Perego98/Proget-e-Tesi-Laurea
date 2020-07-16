<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>

<head>
	<title>Elenco Utenti</title>
	
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	
	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	
	<script>
		$(document).ready(function() {
			$('[data-toggle="tooltip"]').tooltip();
		});
	</script>
	
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
								href="${pageContext.request.contextPath}/admin/showListUsers">Elenco
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

	<!-- Check for registration success -->
	<c:if test="${registrationSucces != null}">
		<div class="alert alert-success">
				<strong>Successo!</strong> ${registrationSucces}
		</div>
	</c:if>

	<div class="panel panel-default">
		<div class="panel-heading"><h2>Elenco Utenti</h2></div>
		<div  class="panel-body">

		
			<table class="table table-striped">
				<tr>
					<th>Username</th>
					<th>Nome</th>
					<th>Cognome</th>
					<th>Email</th>
					<th>Telefono</th>
					<th>Stato</th>
					<th>Ruolo</th>
					<th>Sede Assegnamento</th>
					<th>Azioni</th>
				</tr>
			
				<!-- loop over and print our customers -->
				
				<c:forEach var="tempUser" items="${users}">
				
					<!-- construct an "update" link with customer id -->
					<c:url var="updateLink" value="${pageContext.request.contextPath}/admin/showFormForUpdateUser">
						<c:param name="userUsername" value="${tempUser.userName}"/>
					</c:url> 
					
					<!-- construct an "delete" link with customer id -->
					<c:url var="deleteLink" value="${pageContext.request.contextPath}/admin/deleteUser">
						<c:param name="userUsername" value="${tempUser.userName}"/>
					</c:url> 
						
				
					<tr>
						<td> ${tempUser.userName}</td>
						<td> ${tempUser.firstName}</td>
						<td> ${tempUser.lastName}</td>
						<td> ${tempUser.email}</td>
						<td> ${tempUser.telephone}</td>
						<!-- <td> ${tempUser.qualified}</td> -->
						<td> 
							<c:if test="${tempUser.qualified == false}">
								<!-- Account disattivato -->
								<span data-toggle="tooltip" title="Questo account è stato disattivato, clicca per attivare" class="label label-warning" 
								onclick="if((confirm('Sei sicuro di voler attivare questo utente?'))) window.location.href='${pageContext.request.contextPath}/admin/activateUser?userUsername=${tempUser.userName}'" >Disattivato</span>
							</c:if>
							
							<c:if test="${tempUser.qualified == true}">
								<!-- Account attivato -->
								<span data-toggle="tooltip" title="Questo account è attivo, clicca per disattivare" class="label label-success" 
								onclick="if((confirm('Sei sicuro di voler disattivare questo utente?'))) window.location.href='${pageContext.request.contextPath}/admin/deactivateUser?userUsername=${tempUser.userName}'">Attivo</span>
							</c:if>
						
						</td>
						<td> ${tempUser.roles}</td>
						<td> ${tempUser.sedeAssegnamento}</td>
						
						
						
						<td>
							
							<div class="btn-group-vertical btn-group-xs">

								<div class="dropdown">
									<button class="btn btn-primary dropdown-toggle" type="button"
										data-toggle="dropdown">
										Update/Delete <span class="caret"></span>
									</button>
									<ul class="dropdown-menu">
										<li class="dropdown-header">Update</li>
										<li><a href="${pageContext.request.contextPath}/admin/showFormForUpdateUser?userUsername=${tempUser.userName}">Aggiorna Info</a></li>
										<li><a href="${pageContext.request.contextPath}/admin/showFormForUpdateUserSede?userUsername=${tempUser.userName}">Aggiorna Sede</a></li>
										<c:if test="${tempUser.userName == adminAccount}">
											<li class="disabled"><a href="#">Aggiorna Ruolo</a></li>
										</c:if>
										<c:if test="${tempUser.userName != adminAccount}">
											<li><a href="${pageContext.request.contextPath}/admin/showFormForUpdateUserRole?userUsername=${tempUser.userName}">Aggiorna Ruolo</a></li>
										</c:if>
										
										
										<li class="divider"></li>
										<li class="dropdown-header">Delete</li>
										<li><a onclick="if((confirm('Sei sicuro di voler eliminare questo utente?'))) window.location.href='${pageContext.request.contextPath}/admin/deleteUser?userUsername=${tempUser.userName}'" >Delete</a></li>
									</ul>
								</div>

								<!--  
								<input type="button" value="Aggiorna Info"
												onclick="window.location.href='${pageContext.request.contextPath}/admin/showFormForUpdateUser?userUsername=${tempUser.userName}'; return false;"
												class="btn btn-success"/>
												
								<input type="button" value="Aggiorna Sede"
												onclick="window.location.href='${pageContext.request.contextPath}/admin/showFormForUpdateUserSede?userUsername=${tempUser.userName}'; return false;"
												class="btn btn-primary"/>
																						
								<input type="button" value="Aggiorna Ruolo"
												onclick="window.location.href='${pageContext.request.contextPath}/admin/showFormForUpdateUserRole?userUsername=${tempUser.userName}'; return false;"
												class="btn btn-warning"/>
												
								<input type="button" value="Delete"
												onclick="if((confirm('Sei sicuro di voler eliminare questo utente?'))) window.location.href='${pageContext.request.contextPath}/admin/deleteUser?userUsername=${tempUser.userName}'; return false;"
												class="btn btn-danger"/>
												
								-->
							</div>						
						</td>
					</tr>
				
				</c:forEach>
			
			</table>
		
		</div>
		
		<!--<div class="panel-footer">
			<input type="button" value="Torna alla Home"
				onclick="window.location.href='${pageContext.request.contextPath}/'; return false;"
				class="btn btn-primary" />
		</div>-->
	</div>

</body>

</html>