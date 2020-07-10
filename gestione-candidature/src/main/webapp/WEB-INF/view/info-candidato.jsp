<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>

<head>
	<title>List Users</title>
	
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
		<div class="panel-heading"><h2>Info Candidato</h2></div>
		<div  class="panel-body">
		
			
				<!-- construct an "delete" link with customer id -->
				<c:url var="deleteLink"
					value="${pageContext.request.contextPath}/admin/deleteUser">
					<c:param name="userUsername" value="${candidato.codiceFiscale}" />
				</c:url>

					<table class="table table-striped">
						<tr>
							<th>Codice Fiscale</th>
							<td>${candidato.codiceFiscale}</td>
						</tr>
						<tr>
							<th>Nome</th>
							<td>${candidato.nome}</td>
						</tr>
						<tr>
							<th>Cognome</th>
							<td>${candidato.cognome}</td>
						</tr>
						<tr>
							<th>Stato candidatura</th>
							<td>${candidato.statoCandidatura}</td>
						</tr>
						<tr>
							<th>Telefono</th>
							<td>${candidato.telephone}</td>
						</tr>
						<tr>
							<th>Email</th>
							<td>${candidato.email}</td>
						</tr>
						<tr>
							<th>Data di nascita</th>
							<td>${candidato.dataNascita}</td>
						</tr>
						<tr>
							<th>Tipo di contratto</th>
							<td>${candidato.tipoContratto}</td>
						</tr>
						<tr>
							<th>Ral</th>
							<td>${candidato.ral}</td>
						</tr>
						<tr>
							<th>Giorni di preavviso</th>
							<td>${candidato.preavviso}</td>
						</tr>
						<tr>
							<th>Tipo di Offerta</th>
							<td>${candidato.offerta}</td>
						</tr>
						<tr>
							<th>Canale di Proveninenza</th>
							<td>${candidato.proveninenza}</td>
						</tr>
						<tr>
							<th>Aspettative</th>
							<td>${candidato.aspettative}</td>
						</tr>
						<tr>
							<th>Note</th>
							<td>${candidato.note}</td>
						</tr>
						<tr>
							<th>Curriculum</th>
							<td>
								<c:if test="${candidato.curriculum.length() != 0}">
									<input type="button" value="Download"
										onclick="window.location.href='${pageContext.request.contextPath}/hr/downloadCurriculum?codFiscale=${candidato.codiceFiscale}'; return false;"
										class="btn btn-primary" />
								</c:if>
							</td>
						</tr>
						<tr>
							<th>Supervisore</th>
							<td>Username: ${candidato.supervisore.userName} Ruolo: ${candidato.supervisore.roles}</td>
						</tr>
					</table>







		</div>
	
			<div class="panel-footer">

				<input type="button" value="Back"
							onclick="window.location.href='${pageContext.request.contextPath}/hr/showListCandidati'; return false;"
							class="btn btn-primary" />
							
				<div class="btn-group pull-right">			
					<input type="button" value="Delete"
											onclick="if((confirm('Sei sicuro di voler cancellare questo candidato'))) window.location.href='${pageContext.request.contextPath}/hr/deleteCandidato?codFiscale=${candidato.codiceFiscale}'; return false;"
											class="btn btn-danger" /> 
							
					<input type="button" value="Update"
								onclick="window.location.href='${updateLink}'; return false;"
								class="btn btn-success" />
				</div>
		</div>
	
	</div>
</body>

</html>