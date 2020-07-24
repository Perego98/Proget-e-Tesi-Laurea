<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<!doctype html>
<html lang="en" xmlns:th="http://www.thymeleaf.org"
	xmlns:sec="http://www.thymeleaf.org/extras/spring-security">

<head>

<title>Register New Candidato</title>

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

	<c:url var="sendLink"
		value="/hr/addSchedaValutazione">
		<c:param name="userUsername" value="${user.userName}" />
		<c:param name="codFiscale" value="${candidato.codiceFiscale}" />
	</c:url>


	<div class="mainbox" id="loginbox">
	<div class="panel panel-default">
		<div class="panel-heading"><div class="panel-title">Compilazione Scheda di Valutazione</div>	
	</div>
	
	<div  class="panel-body">


					<!-- Registration Form -->
					<h4><span class="label label-default">Intervistatore ${user.userName}</span></h4>
					<h4><span class="label label-default">Candidato ${candidato.codiceFiscale} - ${candidato.nome} - ${candidato.cognome}</span></h4>
					
					<!-- Elenco di dati del candidato
					
					action="${pageContext.request.contextPath}/hr/addSchedaValutazione"
					
					action="${sendLink}"
					
					
					 -->
					
					<form:form
						action="${pageContext.request.contextPath}/manager/addSchedaValutazione?userUsername=${user.userName}&codFiscale=${candidato.codiceFiscale}"
						modelAttribute="crmSchedaValutazione" class="form-horizontal">

						<!-- Place for messages: error, alert etc ... -->
						<div class="form-group">
							<div class="col-xs-15">
								<div>

									<!-- Check for registration error -->
									<c:if test="${registrationError != null}">

										<div class="alert alert-danger col-xs-offset-1 col-xs-10">
											${registrationError}</div>

									</c:if>

								</div>
							</div>
						</div>
						
						<!-- Data colloquio -->	
						<form:errors path="dataColloquio" cssClass="error" /> <br>
						<label> Data colloquio: </label>
						<div style="margin-bottom: 25px" class="input-group col-xs-5">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-calendar"></i></span>
							<form:input path="dataColloquio" type="date"
								placeholder="data del colloquio gg/mm/aaaa"
								class="form-control" />
						</div>

						<!-- presenza -->
						<form:errors path="presenza" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group col-xs-5">
							<label> Presenza: </label> <br>
							<div>
								<!-- ENUM('insuff', 'suff', 'discreto', 'buono', 'ottimo') -->
								<form:radiobutton path="presenza" value="insuff" />
								insuff. &nbsp;
								<form:radiobutton path="presenza" value="suff" />
								suff. &nbsp;
								<form:radiobutton path="presenza" value="discreto" />
								discreto &nbsp;
								<form:radiobutton path="presenza" value="buono" />
								buono &nbsp;
								<form:radiobutton path="presenza" value="ottimo" />
								ottimo &nbsp;
								<br>
							</div>
						</div>
					
						<!-- comunicativita -->
						<form:errors path="comunicativita" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group col-xs-5">
							<label> Comunicatività: </label> <br>
							<div>
								<!-- ENUM('insuff', 'suff', 'discreto', 'buono', 'ottimo') -->
								<form:radiobutton path="comunicativita" value="insuff" />
								insuff. &nbsp;
								<form:radiobutton path="comunicativita" value="suff" />
								suff. &nbsp;
								<form:radiobutton path="comunicativita" value="discreto" />
								discreto &nbsp;
								<form:radiobutton path="comunicativita" value="buono" />
								buono &nbsp;
								<form:radiobutton path="comunicativita" value="ottimo" />
								ottimo &nbsp;
								<br>
							</div>
						</div>
						
						<!-- Dinamicità -->
						<form:errors path="dinamicita" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group col-xs-5">
							<label> Dinamicità: </label> <br>
							<div>
								<!-- ENUM('insuff', 'suff', 'discreto', 'buono', 'ottimo') -->
								<form:radiobutton path="dinamicita" value="insuff" />
								insuff. &nbsp;
								<form:radiobutton path="dinamicita" value="suff" />
								suff. &nbsp;
								<form:radiobutton path="dinamicita" value="discreto" />
								discreto &nbsp;
								<form:radiobutton path="dinamicita" value="buono" />
								buono &nbsp;
								<form:radiobutton path="dinamicita" value="ottimo" />
								ottimo &nbsp;
								<br>
							</div>
						</div>

						<!-- Sede -->
						<form:errors path="idSedePreferita" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group">
							<label for="sede">Sede:</label>
							<br>
							<!-- Print the radiobutton sedi -->
							<c:forEach var="tempSedi" items="${sedi}">
								<form:radiobutton path="idSedePreferita" value="${tempSedi.id}"/> ${tempSedi.nameCity}, ${tempSedi.via},  ${tempSedi.civicNumber}, ${tempSedi.cap}.
								<br>																
							</c:forEach>
														
						</div>
						
								
						<!-- Disponibilità a trasferimenti e Spostamenti -->
						<form:errors path="dispSpostamentiTrasferimenti" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group col-xs-5">
							<label> Disponibilità a Spostamenti e Trasferimenti: </label> <br>
							<div>
								<!-- ENUM(si, no) -->
								<form:radiobutton path="dispSpostamentiTrasferimenti" value="si" />
								Si &nbsp;
								<form:radiobutton path="dispSpostamentiTrasferimenti" value="no" />
								No <br>
							</div>
						</div>

						<!-- noteSpostamenti -->
						<form:errors path="noteSpostamenti" cssClass="error" />
						<label> Note spostamenti: </label>
						<div style="margin-bottom: 25px" class="input-group col-xs-5">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-comment"></i></span>
							<form:textarea path="noteSpostamenti" rows="5" cols="60"
								placeholder="Note spostamenti" class="form-control" />
						</div>
						
						<!-- motivazioneProfessionale -->
						<form:errors path="motivazioneProfessionale" cssClass="error" />
						<label> Motivazione professionale: </label>
						<div style="margin-bottom: 25px" class="input-group col-xs-5">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-comment"></i></span>
							<form:textarea path="motivazioneProfessionale" rows="5" cols="60"
								placeholder="Motivazione professionale" class="form-control" />
						</div>
						
						<!-- motivazioneCambiamento -->
						<form:errors path="motivazioneCambiamento" cssClass="error" />
						<label> Motivazione del cambiamento: </label>
						<div style="margin-bottom: 25px" class="input-group col-xs-5">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-comment"></i></span>
							<form:textarea path="motivazioneCambiamento" rows="5" cols="60"
								placeholder="Motivazione del cambiamento" class="form-control" />
						</div>
						
						<!-- esperienzeGenerali -->
						<form:errors path="esperienzeGenerali" cssClass="error" />
						<label> Esperienze generali: </label>
						<div style="margin-bottom: 25px" class="input-group col-xs-5">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-comment"></i></span>
							<form:textarea path="esperienzeGenerali" rows="5" cols="80"
								placeholder="esperienze generali" class="form-control" />
						</div>
						
						<!-- esperienzePosizione -->
						<form:errors path="esperienzePosizione" cssClass="error" />
						<label> Esperienze in questa posizione: </label>
						<div style="margin-bottom: 25px" class="input-group col-xs-5">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-comment"></i></span>
							<form:textarea path="esperienzePosizione" rows="5" cols="80"
								placeholder="esperienze in questa posizione" class="form-control" />
						</div>

						<!-- lingue -->
						<form:errors path="noteSpostamenti" cssClass="error" />
						<label> Lingue: </label>
						<div style="margin-bottom: 25px" class="input-group col-xs-5">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-comment"></i></span>
							<form:textarea path="lingue" rows="5" cols="80"
								placeholder="Lingue" class="form-control" />
						</div>
						
						<!-- competenza -->
						<form:errors path="competenza" cssClass="error" />
						<label> Competenze: </label>
						<div style="margin-bottom: 25px" class="input-group col-xs-5">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-comment"></i></span>
							<form:textarea path="competenza" rows="10" cols="180"
								placeholder="Competenze" class="form-control" />
						</div>
						
						<!-- note -->
						<form:errors path="note" cssClass="error" />
						<label> Note: </label>
						<div style="margin-bottom: 25px" class="input-group col-xs-5">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-comment"></i></span>
							<form:textarea path="note" rows="5" cols="40"
								placeholder="Note" class="form-control" />
						</div>
						
						
						<!-- retribuzioneAttuale -->
						<form:errors path="retribuzioneAttuale" cssClass="error" />
						<label> Retribuzione attuale: </label>
						<div style="margin-bottom: 25px" class="input-group col-xs-5">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-usd"></i></span>
							<form:input path="retribuzioneAttuale" placeholder="Retribuzione attuale" class="form-control" />
						</div>
						
						<!-- retribuzioneRichiesta -->
						<label> Retribuzione richiesta: </label>
						<form:errors path="retribuzioneRichiesta" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group col-xs-5">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-usd"></i></span>
							<form:input path="retribuzioneRichiesta" placeholder="Retribuzione richiesta" class="form-control" />
						</div>
						
						<!-- inquadramentoAttuale -->
						<form:errors path="inquadramentoAttuale" cssClass="error" />
						<label> Inquadramento attuale: </label>
						<div style="margin-bottom: 25px" class="input-group col-xs-5">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-comment"></i></span>
							<form:input path="inquadramentoAttuale"
								placeholder="Inquadramento attuale" class="form-control" />
						</div>
						
						<!-- inquadramentoRichiesto -->
						<form:errors path="inquadramentoRichiesto" cssClass="error" />
						<label> Inquadramento richiesto: </label>
						<div style="margin-bottom: 25px" class="input-group col-xs-5">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-comment"></i></span>
							<form:input path="inquadramentoRichiesto"
								placeholder="Inquadramento richiesto" class="form-control" />
						</div>
						
						
						
						<!-- Il periodo di preavviso lo prendo da Candidato nel Service-->
						
						<!-- Il CVAllegato lo prendo da Candidato nel Service-->
						


						<!-- Register Button -->
						<div style="margin-top: 10px" class="form-group ">
							<div class="col-sm-6 controls">
								<button type="submit" class="btn btn-primary">Register</button>
							</div>
						</div>

					</form:form>

				</div>

		<div class="panel-footer">
			<input type="button" value="Back"
							onclick="window.location.href='${pageContext.request.contextPath}/manager/showListCandidati'; return false;"
							class="btn btn-primary" />
		</div>

			</div>

	</div>



</body>
</html>