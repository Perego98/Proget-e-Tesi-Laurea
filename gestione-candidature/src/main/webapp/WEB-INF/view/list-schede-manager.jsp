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
					<li class="dropdown "><a class="dropdown-toggle"
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
					<li class="dropdown active"><a class="dropdown-toggle"
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
		<div class="panel-heading"><h2>Elenco schede</h2></div>
		<div  class="panel-body">
		
		<div class="panel-group" id="accordion">
			<c:forEach var="tempSchede" items="${schede}">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapse${tempSchede.id}"> ${tempSchede.dataColloquio.getTime()}</a>
							</h4>
						</div>
						<div id="collapse${tempSchede.id}" class="panel-collapse collapse">
							<div class="panel-body">
								<table class="table table-striped">
									<tr>
										<th>Intervistatore:</th>
										<td>${tempSchede.utenteRelatore.userName}</td>
									</tr>									
									<tr>
										<th>Sede preferita:</th>
										<td>${tempSchede.sedePreferita.toString()}</td>
									</tr>									
									<tr>
										<th>Presenza:</th>
										<td>${tempSchede.presenza}</td>
									</tr>
									<tr>
										<th>Comunicatività:</th>
										<td>${tempSchede.comunicativita}</td>
									</tr>
									<tr>
										<th>Dinamicità:</th>
										<td>${tempSchede.dinamicita}</td>
									</tr>
									<tr>
										<th>Disponibilità spostamneti / trasferimenti:</th>
										<td>${tempSchede.dispSpostamentiTrasferimenti}</td>
									</tr>
									
									<tr>
										<th>Note spostamenti / trasferimenti:</th>
										<td>${tempSchede.noteSpostamenti}</td>
									</tr>
									<tr>
										<th>Motivazioni professionali:</th>
										<td>${tempSchede.motivazioneProfessionale}</td>
									</tr>
									<tr>
										<th>Motivazioni al cambiamento:</th>
										<td>${tempSchede.motivazioneCambiamento}</td>
									</tr>
									<tr>
										<th>Esperienze in generale:</th>
										<td>${tempSchede.esperienzeGenerali}</td>
									</tr>
									<tr>
										<th>Esperienze nella posizione:</th>
										<td>${tempSchede.esperienzePosizione}</td>
									</tr>
									
									<tr>
										<th>Lingue:</th>
										<td>${tempSchede.lingue}</td>
									</tr>
									
									<tr>
										<th>Competenza:</th>
										<td>${tempSchede.competenza}</td>
									</tr>
									
									<tr>
										<th>Note:</th>
										<td>${tempSchede.note}</td>
									</tr>
									
									
									<tr>
										<th>Retribuzione attuale:</th>
										<td>${tempSchede.retribuzioneAttuale}</td>
									</tr>

									<tr>
										<th>Retribuzione richiesta:</th>
										<td>${tempSchede.retribuzioneRichiesta}</td>
									</tr>

									
									<tr>
										<th>Inquadramento Attuale:</th>
										<td>${tempSchede.inquadramentoAttuale}</td>
									</tr>

									<tr>
										<th>Inquadramento richiesto:</th>
										<td>${tempSchede.inquadramentoRichiesto}</td>
									</tr>

									<tr>
										<th>Periodo di preavviso:</th>
										<td>${tempSchede.periodoPreavviso}</td>
									</tr>

									<tr>
										<th>Curriculum allegato:</th>
										<td>${tempSchede.CVAllegato}</td>
									</tr>




								</table>
								<c:if
									test="${tempSchede.utenteRelatore.userName == accountAttuale}">
									<input type="button" value="Elimina"
										onclick="window.location.href='${pageContext.request.contextPath}/manager/deleteScheda?codScheda=${tempSchede.id}&codFiscale=${codiceFiscale}'; return false;"
										class="btn btn-danger btn-sm" />
								</c:if>


							</div>
						</div>
					</div>


				</c:forEach>
		</div>


		</div>
			
			<div class="panel-footer">

				<input type="button" value="Indietro"
							onclick="window.location.href='${pageContext.request.contextPath}/manager/showListCandidati'; return false;"
							class="btn btn-primary" />
						
		</div>
	
	</div>
</body>

</html>