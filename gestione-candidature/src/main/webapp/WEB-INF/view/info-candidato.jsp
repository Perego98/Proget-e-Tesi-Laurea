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
	
	
	<!-- Check for registration success -->
	<c:if test="${registrationSucces != null}">
		<div class="alert alert-success">
				<strong>Successo!</strong> ${registrationSucces}
		</div>
	</c:if>
	
	<c:if test="${registrationError != null}">
		<div class="alert alert-danger">
				<strong>Attenzione!</strong> ${registrationError}
		</div>
	</c:if>

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
							<td>${dataN}</td>
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
								<c:if test="${candidato.curriculum != null}">
									<input type="button" value="Download"
										onclick="window.location.href='${pageContext.request.contextPath}/hr/downloadCurriculum?codFiscale=${candidato.codiceFiscale}'; return false;"
										class="btn btn-primary" />
								</c:if>
								
								<c:if test="${candidato.curriculum == null}">
									<h4><span data-toggle="tooltip" title="Non è stato caricato nessun curriculum" class="label label-info">Nessun Curriculum</span></h4>
								</c:if>
								
							</td>
						</tr>
						
						<tr>
							<th>Scheda/e di valutazione</th>
							<td>
								<c:if test="${schedaVal != null}">
									<input type="button" value="Mostra"
										onclick="window.location.href='${pageContext.request.contextPath}/hr/showSchedeValutazione?codFiscale=${candidato.codiceFiscale}'; return false;"
										class="btn btn-primary" />
								</c:if>
								
								<c:if test="${schedaVal == null}">
									<h4><span data-toggle="tooltip" title="Non è stato caricata nessuna scheda di valutazione" class="label label-info">Nessua Scheda di valutazione</span></h4>
								</c:if>
								
							</td>
						</tr>
						
						<tr>
							<th>Supervisore</th>
								<c:if test="${candidato.supervisore != null}">
									<td>Username: ${candidato.supervisore.userName} - Ruolo: ${candidato.supervisore.roles}</td>
								</c:if>
								
								<c:if test="${candidato.supervisore == null}">
									<td><h4><span data-toggle="tooltip" title="Nessun Supervisore Associato" class="label label-warning">Attenzione</span></h4></td>
								</c:if>
									
						</tr>
					</table>







		</div>
	
			<div class="panel-footer">

				<input type="button" value="Indietro"
							onclick="window.location.href='${pageContext.request.contextPath}/hr/showListCandidati'; return false;"
							class="btn btn-primary" />
							
			</div>
	
	</div>
</body>

</html>