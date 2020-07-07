<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<!doctype html>
<html lang="en">

<head>
	
	<title>Register New Candidato</title>
	

<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<!-- Reference Bootstrap files -->
	<link rel="stylesheet"
		 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
		<style>
		.error {color:red}
	</style>

</head>

<body>

	Qui si registrerà il nuovo candidato
	
	
	
	<!-- FIX -->
	
	<div>
		
		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">
			
			<div class="panel panel-primary">

				<div class="panel-heading">
					<div class="panel-title">Register New User</div>
				</div>

				<div style="padding-top: 30px" class="panel-body">

					<!-- Registration Form -->
					<form:form action="${pageContext.request.contextPath}/register/processRegistrationCandidatoForm" 
						  	   modelAttribute="crmCandidato"
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

						<!-- Codice Fiscale -->
						<form:errors path="codiceFiscale" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
							<form:input path="codiceFiscale" placeholder="codice fiscale(*)" class="form-control" />
						</div>
					
						
						<!-- First name -->
						<form:errors path="nome" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
							<form:input path="nome" placeholder="first name (*)" class="form-control" />
						</div>
						
						<!-- Last name -->
						<form:errors path="cognome" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
							<form:input path="cognome" placeholder="last name (*)" class="form-control" />
						</div>
						
						<!-- Email -->
						<form:errors path="email" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span> 
							<form:input path="email" placeholder="email (*)" class="form-control" />
						</div>
						
						
						<!-- Telephone -->
						<form:errors path="telephone" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span> 
							<form:input path="telephone" placeholder="telephone number (*)" class="form-control" />
						</div>
						
						<!-- Data di nascita -->
						<!--  TODO: mettere calendario-->
						<form:errors path="dataNascita" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span> 
							<form:input path="dataNascita" type="date" placeholder="data di nascita dd/mm/aaaa (*)" class="form-control" />
						</div>  

						
						<!-- ral -->
						<form:errors path="ral" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-usd"></i></span> 
							<form:input path="ral" placeholder="ral (*)" class="form-control" />
						</div>
						
						<!-- preavviso -->
						<form:errors path="ral" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span> 
							<form:input path="preavviso" placeholder="preavviso (*)" class="form-control" />
						</div>
						
						<!-- Tipo di contratto -->
						<form:errors path="tipoContratto" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group">
							<label>
								Tipo di contratto:
							</label>
							</br>
							<div>
								<!-- ENUM('tempo_indeterminato', 'tempo_determinato', 'somministrazione', 'a_chiamata', 'a_progetto', 'accessorio', 'apprendistato', 'tirocinio_formativo_e_orientamento', 'part-time', 'not_set') -->
								<form:radiobutton path="tipoContratto" value="tempo_indeterminato"/> Tempo Indeterminato
								</br>
								<form:radiobutton path="tipoContratto" value="somministrazione"/> Somministrazione
								</br>
								<form:radiobutton path="tipoContratto" value="a_chiamata"/> A Chiamata 
								</br>
								<form:radiobutton path="tipoContratto" value="a_progetto"/> A Progetto
								</br>
								<form:radiobutton path="tipoContratto" value="accessorio"/> Accessorio
								</br>
								<form:radiobutton path="tipoContratto" value="apprendistato"/> Apprendistato
								</br>
								<form:radiobutton path="tipoContratto" value="tirocinio_formativo_e_orientamento"/> Tirocinio Formativo e Orientamento
								</br>
								<form:radiobutton path="tipoContratto" value="part-time"/> part-time
							</div>
						</div>
						
						<!-- offerta -->
						<form:errors path="offerta" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group">
							<label>
							Offerta:
							</label>
							</br>
							<div>
								<form:radiobutton path="offerta" value="lavoro"/> Lavoro
								</br>
								<form:radiobutton path="offerta" value="stage"/> Stage
								</br>
								<form:radiobutton path="offerta" value="candidatura_spontanea"/> Candidatura Spontanea 
								</br>
							</div>
							
						</div>
						
						<!-- proveninenza -->
						<form:errors path="proveninenza" cssClass="error" />
						<label>
								Provenienza:
						</label>
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-comment"></i></span>
							<form:textarea path="proveninenza" rows = "5" cols = "80" placeholder="proveninenza (*)" class="form-control" />
						</div>
						
						<!-- aspettative -->
						<form:errors path="aspettative" cssClass="error" />
						<label>
							Aspettative:
						</label>
						<div style="margin-bottom: 25px" class="input-group">
							
							<span class="input-group-addon"><i class="glyphicon glyphicon-comment"></i></span>
							<form:textarea path="aspettative" rows = "5" cols = "80" placeholder="aspettative (*)" class="form-control" />
						</div>
						
						<!-- note -->
						<form:errors path="note" cssClass="error" />
						<label>
								Note:
						</label>
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-comment"></i></span> 
							<form:textarea path="note" rows = "10" cols = "180" placeholder="Note (*)" class="form-control" />
						</div>
						
						<!-- TODO: Caricamento del curriculum  glyphicon glyphicon-folder-open -->
						
					

						<!-- Register Button -->
						<div style="margin-top: 10px" class="form-group">						
							<div class="col-sm-6 controls">
								<button type="submit" class="btn btn-primary">Register</button>
							</div>
						</div>
						
					</form:form>

				</div>

			</div>

		</div>

	</div>
	
	<!-- FIX -->
	
	
	

</body>
</html>