<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<title>List Users</title>
	
	<!-- link css -->
	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css"/>
	
</head>


<body>

	<div id="wrapper">
		<div id="header">
			<h2>List Candidati</h2>
		</div>
	</div>

	<div id="container">
	
		<div id="content">
		
			
				<!-- construct an "delete" link with customer id -->
				<c:url var="deleteLink"
					value="${pageContext.request.contextPath}/admin/deleteUser">
					<c:param name="userUsername" value="${candidato.codiceFiscale}" />
				</c:url>

					<table>
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
							<td>${candidato.curriculum}</td>
						</tr>
						<tr>
							<th>Supervisore</th>
							<td>Username: ${candidato.supervisore.userName} Ruolo: ${candidato.supervisore.roles}</td>
						</tr>
					</table>
	
					<div>
						<input type="button" value="Delete"
							onclick="if((confirm('Are you sure you want to delete this candidato'))) window.location.href='${pageContext.request.contextPath}/hr/deleteCandidato?codFiscale=${candidato.codiceFiscale}'; return false;"
							class="delete-button" />
					</div>






		</div>
	
	</div>

	<div>
		
	<a href="${pageContext.request.contextPath}/hr/showListCandidati">Back to List</a>
	</div>
</body>

</html>