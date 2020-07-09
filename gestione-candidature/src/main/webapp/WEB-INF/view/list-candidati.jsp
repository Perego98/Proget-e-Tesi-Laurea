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
		
		<!-- Add buttun code 
	
		<input type="button" value="Add Customer"
				onclick="window.location.href='showFormForAdd'; return false;"
				class="add-button"/> -->
		
		<!--  add a search box 
            <form:form action="search" method="GET">
                Search customer: <input type="text" name="theSearchName" />
                
                <input type="submit" value="Search" class="add-button" />
            </form:form> -->
		
		<!-- add out html table here -->
		
			<table>
				<tr>
					<th>Codice Fiscale</th>
					<th>Nome</th>
					<th>Cognome</th>
					<th>Stato candidatura</th>
					<th>Telefono</th>
					<th>Email</th>
					<th>Data di nascita</th>
					<th>Tipo di contratto</th>
					<!-- <th>Ral</th> -->
					<!-- <th>Giorni di preavviso</th> -->
					<!-- <th>Tipo di Offerta</th> -->
					<!-- <th>Canale di Proveninenza</th> -->
					<!-- <th>Aspettative</th> -->
					<!-- <th>Note</th> -->
					<!-- <th>Curriculum</th> -->
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
						<td> ${tempCandidato.dataNascita}</td>
						<td> ${tempCandidato.tipoContratto}</td>
						<!--  <td> ${tempCandidato.ral}</td>
						<td> ${tempCandidato.preavviso}</td>
						<td> ${tempCandidato.offerta}</td>
						<td> ${tempCandidato.proveninenza}</td>
						<td> ${tempCandidato.aspettative}</td>
						<td> ${tempCandidato.note}</td>
						<td> ${tempCandidato.curriculum}</td>-->
						<td> ${tempCandidato.supervisore.userName}</td>				
						
						
						<td>
							<!--  <input type="button" value="Update"
											onclick="window.location.href='${updateLink}'; return false;"
											class="update-button"/>		
											
											
											<input type="button" value="Delete"
											onclick="if((confirm('Are you sure you want to delete this user?'))) window.location.href='${deleteLink}'; return false;"
											class="delete-button"/>
											
											-->
						
							<input type="button" value="Delete"
											onclick="if((confirm('Are you sure you want to delete this candidato'))) window.location.href='${pageContext.request.contextPath}/hr/deleteCandidato?codFiscale=${tempCandidato.codiceFiscale}'; return false;"
											class="delete-button"/>
						</td>
						
						<td>
							<input type="button" value="Show more info"
											onclick="window.location.href='${pageContext.request.contextPath}/hr/showMoreInfoCandidato?codFiscale=${tempCandidato.codiceFiscale}'; return false;"
											class="delete-button"/>
						</td>
					
					</tr>
				
				</c:forEach>
			
			</table>
		
		</div>
	
	</div>

	<div>
		
	<a href="${pageContext.request.contextPath}/">Back to Home Page</a>
	</div>
</body>

</html>