<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>

<head>
	<title>Elenco Candidati</title>
	
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
	
		<!-- Check for registration success -->
	<c:if test="${registrationSucces != null}">
		<div class="alert alert-success">
				<strong>Successo!</strong> ${registrationSucces}
		</div>
	</c:if>

	<div class="panel panel-default">
		<div class="panel-heading"><h2>Elenco Candidati</h2></div>
		<div  class="panel-body">
		
			<table class="table table-striped">
				<tr>
					<th>Codice Fiscale</th>
					<th>Nome</th>
					<th>Cognome</th>
					<th>Stato candidatura</th>
					<th>Telefono</th>
					<th>Email</th>
					<th>Tipo di contratto</th>
					<th>Supervisore</th>
					<th>Action</th>
				</tr>
			
				<!-- loop over and print our customers -->
				
				<c:forEach var="tempCandidato" items="${candidati}">
				
					<!-- construct an "update" link with customer id 
					<c:url var="updateLink" value="/customer/showFormForUpdate">
						<c:param name="customerId" value="${tempCustomer.id}"/>
					</c:url> -->
					
					<!-- construct an "delete" link with customer id -->
					<c:url var="deleteLink" value="${pageContext.request.contextPath}/admin/deleteUser">
						<c:param name="userUsername" value="${tempCandidato.codiceFiscale}"/>
					</c:url> 
						
				
					<tr>
						<td> ${tempCandidato.codiceFiscale}</td>
						<td> ${tempCandidato.nome}</td>
						<td> ${tempCandidato.cognome}</td>
						<td> ${tempCandidato.statoCandidatura}</td>
						<td> ${tempCandidato.telephone}</td>
						<td> ${tempCandidato.email}</td>
						<td> ${tempCandidato.tipoContratto}</td>
						<c:if test="${tempCandidato.supervisore.userName != null}">
							<td> ${tempCandidato.supervisore.userName}</td>		
						</c:if >
						<c:if test="${tempCandidato.supervisore.userName == null}">
							<td> <h5><span data-toggle="tooltip" title="Nessun Supervisore Associato" class="label label-warning">Attenzione</span></h5></td>		
						</c:if >							
						
						
						<td>
							
							<div class="dropdown">
									<button class="btn btn-primary dropdown-toggle" type="button"
										data-toggle="dropdown">
										Update/Info/Delete <span class="caret"></span>
									</button>
									<ul class="dropdown-menu">
										<li class="dropdown-header">Update</li>
										<li><a href="${pageContext.request.contextPath}/hr/showCandidatoUpdateForm?codFiscale=${tempCandidato.codiceFiscale}">Aggiorna Info</a></li>
										<li><a href="${pageContext.request.contextPath}/hr/showCandidatoUpdateStatoForm?codFiscale=${tempCandidato.codiceFiscale}">Aggiorna Stato</a></li>
										<li><a href="${pageContext.request.contextPath}/hr/showCandidatoSetManagerForm?codFiscale=${tempCandidato.codiceFiscale}">Assegna a Manager</a></li>
										<li><a href="${pageContext.request.contextPath}/hr/showCandidatoUploadCVForm?codFiscale=${tempCandidato.codiceFiscale}">Upload CV</a></li>										
										<li class="divider"></li>
										<li class="dropdown-header">Info</li>
										<li><a href="${pageContext.request.contextPath}/hr/showMoreInfoCandidato?codFiscale=${tempCandidato.codiceFiscale}">Mostra più Info</a></li>
										<li class="divider"></li>
										<li class="dropdown-header">Delete</li>
										<li><a onclick="if((confirm('Sei sicuro di voler cancellare questo candidato?'))) window.location.href='${pageContext.request.contextPath}/hr/deleteCandidato?codFiscale=${tempCandidato.codiceFiscale}'; return false;" >Delete</a></li>
									</ul>
								</div>
							<!-- 
							<div class="btn-group-vertical btn-group-xs">
								<input type="button" value="Update"
												onclick="window.location.href='${updateLink}'; return false;"
												class="btn btn-success"/>
						
								<input type="button" value="Show more info"
												onclick="window.location.href='${pageContext.request.contextPath}/hr/showMoreInfoCandidato?codFiscale=${tempCandidato.codiceFiscale}'; return false;"
												class="btn btn-info"/>
												
								<input type="button" value="Delete"
												onclick="if((confirm('Sei sicuro di voler cancellare questo candidato?'))) window.location.href='${pageContext.request.contextPath}/hr/deleteCandidato?codFiscale=${tempCandidato.codiceFiscale}'; return false;"
												class="btn btn-danger"/>
							</div>
							 -->
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