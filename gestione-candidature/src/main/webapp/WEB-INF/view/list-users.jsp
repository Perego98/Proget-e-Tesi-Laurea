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
	
	<script>
	$(document).ready(function(){
	  $("#myInput").on("keyup", function() {
	    var value = $(this).val().toLowerCase();
	    $("#myTable tr").filter(function() {
	      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	    });
	  });
	});
	</script>
	
</head>


<body>
	
	<!-- Se l'account non � autorizzato viene rimandato alla home -->
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
			
			<!-- Aggiunta barra di ricerca 
			<security:authorize access="hasRole('ADMIN')">
				<form class="navbar-form navbar-left" action="search">
					<div class="form-group">
						<input type="text" class="form-control" name="theSearchName" placeholder="Cerca Utenti">
					</div>
					<button type="submit" class="btn btn-default">Cerca</button>
				</form>
			</security:authorize>-->
			
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

	<div class="panel panel-default">
		<div class="panel-heading"><h2>Elenco Utenti</h2></div>
		<div  class="panel-body">
		
		
		
		
		
		
			<!--  add a search box 
            <form:form action="search" method="GET">
                Search user: <input type="text" name="theSearchName" />
                
                <input type="submit" value="Search" class="add-button" />
            </form:form>
            -->
            
            
            <h4>Ricerca Utenti</h4>
 			<input class="form-control" id="myInput" type="text" placeholder="Cerca..">
            <br>
            
		
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
				<tbody id="myTable">
				
				<c:forEach var="tempUser" items="${users}">
				
						<!-- MOdal group -->
	
						<!-- Delete Modal -->
						<div id="Delete${tempUser.userName}" class="modal fade" role="dialog">
						  <div class="modal-dialog">
						
						    <!-- Modal content-->
						    <div class="modal-content">
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal">&times;</button>
						        <h4 class="modal-title">Eliminazione Account</h4>
						      </div>
						      <div class="modal-body">
						        <p>Attenzione! Cliccando Elimina questo account verr� Eliminato</p>
						        <br>
						        <p>Una volta eliminato non sar� pi� possibile recuperare i dati</p>
						      </div>
						      <div class="modal-footer">
						      	<button type="button" class="btn btn-danger" data-dismiss="modal"
						      	onclick="window.location.href='${pageContext.request.contextPath}/admin/deleteUser?userUsername=${tempUser.userName}'"
						      	>Elimina</button>
						        <button type="button" class="btn btn-default" data-dismiss="modal">Chiudi</button>
						      </div>
						    </div>
						
						  </div>
						</div>
						
						<!-- Deactivate Modal -->
						<div id="myModalDeactivate${tempUser.userName}" class="modal fade" role="dialog">
						  <div class="modal-dialog">
						
						    <!-- Modal content-->
						    <div class="modal-content">
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal">&times;</button>
						        <h4 class="modal-title">Attivazione Account</h4>
						      </div>
						      <div class="modal-body">
						        <p>Attenzione! Cliccando conferma questo account verr� attivato</p>
						        <br>
						        <p>L'utente che verr� attivato avr� nuovamente accesso alle operazioni consentite <br> in base al ruolo che ricopre</p>
						        <br>
						        <p>Ruolo: ${tempUser.roles}</p>
						        
						      </div>
						      <div class="modal-footer">
						      	<button type="button" class="btn btn-default" data-dismiss="modal"
						      	onclick="window.location.href='${pageContext.request.contextPath}/admin/activateUser?userUsername=${tempUser.userName}'"
						      	>Conferma</button>
						        <button type="button" class="btn btn-default" data-dismiss="modal">Chiudi</button>
						      </div>
						    </div>
						
						  </div>
						</div>
						
						<!-- Activate Modal -->
						<div id="myModalActivate${tempUser.userName}" class="modal fade" role="dialog">
						  <div class="modal-dialog">
						
						    <!-- Modal content-->
						    <div class="modal-content">
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal">&times;</button>
						        <h4 class="modal-title">Disattivazione Account</h4>
						      </div>
						      <div class="modal-body">
						        <p>Attenzione! Cliccando conferma questo account verr� disattivato</p>
						        <br>
						        <p>L'utente che verr� disattivato non potr� pi� svolgere alcuna azione</p>
						      </div>
						      <div class="modal-footer">
						      	<button type="button" class="btn btn-default" data-dismiss="modal"
						      	onclick="window.location.href='${pageContext.request.contextPath}/admin/deactivateUser?userUsername=${tempUser.userName}'"
						      	>Conferma</button>
						        <button type="button" class="btn btn-default" data-dismiss="modal">Chiudi</button>
						      </div>
						    </div>
						
						  </div>
						</div>
								
				
				
				

				
				
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
								
								<!-- Trigger the modal with a button -->
								<button type="button" class="btn btn-warning btn-xs" data-toggle="modal" data-target="#myModalDeactivate${tempUser.userName}">
									<span data-toggle="tooltip"
									title="Questo account � stato disattivato, clicca per attivare"
									class="label">Disattivato</span>
								</button>
								
								
							</c:if>
							
							<c:if test="${tempUser.qualified == true}">
								<c:if test="${tempUser.userName == adminAccount}">
								
									<!-- Trigger the modal with a button -->
									<button type="button" class="btn btn-success btn-xs" data-toggle="modal" data-target="#">
										<span data-toggle="tooltip"
										title="Questo account � attivo, non � possibile disattivarlo in quanto si � loggatti con questo account"
										class="label">Attivo</span>
									</button>
								
								</c:if>
								
								<c:if test="${tempUser.userName != adminAccount}">
								
									<!-- Trigger the modal with a button -->
									<button type="button" class="btn btn-success btn-xs" data-toggle="modal" data-target="#myModalActivate${tempUser.userName}">
										<span data-toggle="tooltip"
										title="Questo account � attivo, clicca per disattivare"
										class="label">Attivo</span>
									</button>
								
								</c:if>
								
								
								
								
							</c:if>
						
						</td>
						<td> ${tempUser.ruoli}</td>
						<td> ${tempUser.sedeAssegnamento.printFormatted2}</td>
						
						
						
						<td>
							
							<div class="btn-group-vertical btn-group-xs">

								<div class="dropdown">
									<button class="btn btn-primary dropdown-toggle" type="button"
										data-toggle="dropdown">
										Aggiorna/Elimina <span class="caret"></span>
									</button>
									<ul class="dropdown-menu">
										<li class="dropdown-header">Aggiorna</li>
										<li><a href="${pageContext.request.contextPath}/admin/showFormForUpdateUser?userUsername=${tempUser.userName}">Aggiorna Info</a></li>
										<li><a href="${pageContext.request.contextPath}/admin/showFormForUpdateUserSede?userUsername=${tempUser.userName}">Aggiorna Sede</a></li>
										<c:if test="${tempUser.userName == adminAccount}">
											<li class="disabled"><a href="#">Aggiorna Ruolo</a></li>
											
										</c:if>
										
										<c:if test="${tempUser.userName != adminAccount}">
											<li><a href="${pageContext.request.contextPath}/admin/showFormForUpdateUserRole?userUsername=${tempUser.userName}">Aggiorna Ruolo</a></li>
											
										</c:if>
										
										
										<li class="divider"></li>
										<li class="dropdown-header">Elimina</li>
										
											<c:if test="${tempUser.userName == adminAccount}">
												<li class="disabled">
													<a onclick="#" data-toggle="modal" data-target="#">Elimina Account
													</a>
												</li>
											</c:if>
											
											<c:if test="${tempUser.userName != adminAccount}">
												<li>
													<a onclick="#Delete${tempUser.userName}" data-toggle="modal" data-target="#Delete${tempUser.userName}">Elimina Account
													</a>
												</li>
											</c:if>
											
													
										
									</ul>
								</div>

									
							</div>						
						</td>
					</tr>
				
				</c:forEach>
			
				</tbody>
			</table>
		
		</div>

		<div class="panel-footer">
			<ul class="pagination">
				<c:forEach var="numPagina" items="${numeroPagineList}">
					<c:if test="${pageNumber == numPagina}">
						<li class="active"><a
							href="${pageContext.request.contextPath}/admin/showListUsersMinMax?firstPage=${(userPerPagina*numPagina)-(userPerPagina)}&maxPage=${userPerPagina}">${numPagina}</a></li>
					</c:if>
					<c:if test="${pageNumber != numPagina}">
						<li><a
							href="${pageContext.request.contextPath}/admin/showListUsersMinMax?firstPage=${(userPerPagina*numPagina)-(userPerPagina)}&maxPage=${userPerPagina}">${numPagina}</a></li>
					</c:if>
				</c:forEach>

			</ul>

		</div>
	</div>

</body>

</html>