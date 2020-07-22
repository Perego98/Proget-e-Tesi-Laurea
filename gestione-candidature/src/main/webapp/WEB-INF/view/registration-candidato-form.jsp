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
							<li><a
									href="${pageContext.request.contextPath}/manager/showListCandidati">Elenco
										Candidati associati</a></li>
							<li><a href="#">Ruolo 2</a></li>
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


<div class="mainbox" id="loginbox">
	<div class="panel panel-default">
		<div class="panel-heading"><div class="panel-title">Registrazione Nuovo Candidato</div>	
	</div>
	
	<div  class="panel-body">


					<!-- Registration Form -->
					<h4><span class="label label-default">Collegato a <security:authentication property="principal.username" /></span></h4>
					
					<form:form
						action="${pageContext.request.contextPath}/hr/processRegistrationCandidatoForm"
						modelAttribute="crmCandidato" class="form-horizontal">

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

						<!-- Codice Fiscale -->
						<form:errors path="codiceFiscale" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group col-xs-5">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span>
							<form:input path="codiceFiscale" placeholder="codice fiscale(*)"
								class="form-control" />
						</div>


						<!-- First name -->
						<form:errors path="nome" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group col-xs-5">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span>
							<form:input path="nome" placeholder="first name (*)"
								class="form-control" />
						</div>

						<!-- Last name -->
						<form:errors path="cognome" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group col-xs-5">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span>
							<form:input path="cognome" placeholder="last name (*)"
								class="form-control" />
						</div>

						<!-- Email -->
						<form:errors path="email" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group col-xs-5">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-envelope"></i></span>
							<form:input path="email" placeholder="email (*)"
								class="form-control" />
						</div>


						<!-- Telephone -->
						<form:errors path="telephone" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group col-xs-5">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-earphone"></i></span>
							<form:input path="telephone" placeholder="telephone number (*)"
								class="form-control" />
						</div>

						<!-- Data di nascita -->
						<form:errors path="dataNascita" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group col-xs-5">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-calendar"></i></span>
							<form:input path="dataNascita" type="date"
								placeholder="data di nascita dd/mm/aaaa (*)"
								class="form-control" />
						</div>


						<!-- ral -->
						<form:errors path="ral" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group col-xs-5">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-usd"></i></span>
							<form:input path="ral" placeholder="ral (*)" class="form-control" />
						</div>

						<!-- preavviso -->
						<form:errors path="ral" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group col-xs-5">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-calendar"></i></span>
							<form:input path="preavviso" placeholder="preavviso (*)"
								class="form-control" />
						</div>

						<!-- Tipo di contratto -->
						<form:errors path="tipoContratto" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group col-xs-5">
							<label> Tipo di contratto: </label> </br>
							<div>
								<!-- ENUM('tempo_indeterminato', 'tempo_determinato', 'somministrazione', 'a_chiamata', 'a_progetto', 'accessorio', 'apprendistato', 'tirocinio_formativo_e_orientamento', 'part-time', 'not_set') -->
								<form:radiobutton path="tipoContratto"
									value="tempo_indeterminato" />
								Tempo Indeterminato </br>
								<form:radiobutton path="tipoContratto" value="somministrazione" />
								Somministrazione </br>
								<form:radiobutton path="tipoContratto" value="a_chiamata" />
								A Chiamata </br>
								<form:radiobutton path="tipoContratto" value="a_progetto" />
								A Progetto </br>
								<form:radiobutton path="tipoContratto" value="accessorio" />
								Accessorio </br>
								<form:radiobutton path="tipoContratto" value="apprendistato" />
								Apprendistato </br>
								<form:radiobutton path="tipoContratto"
									value="tirocinio_formativo_e_orientamento" />
								Tirocinio Formativo e Orientamento </br>
								<form:radiobutton path="tipoContratto" value="part-time" />
								part-time
							</div>
						</div>

						<!-- offerta -->
						<form:errors path="offerta" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group col-xs-5">
							<label> Offerta: </label> </br>
							<div>
								<form:radiobutton path="offerta" value="lavoro" />
								Lavoro </br>
								<form:radiobutton path="offerta" value="stage" />
								Stage </br>
								<form:radiobutton path="offerta" value="candidatura_spontanea" />
								Candidatura Spontanea </br>
							</div>

						</div>

						<!-- proveninenza -->
						<form:errors path="proveninenza" cssClass="error" />
						<label> Provenienza: </label>
						<div style="margin-bottom: 25px" class="input-group col-xs-5">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-comment"></i></span>
							<form:textarea path="proveninenza" rows="5" cols="80"
								placeholder="proveninenza (*)" class="form-control" />
						</div>

						<!-- aspettative -->
						<form:errors path="aspettative" cssClass="error" />
						<label> Aspettative: </label>
						<div style="margin-bottom: 25px" class="input-group col-xs-5">

							<span class="input-group-addon"><i
								class="glyphicon glyphicon-comment"></i></span>
							<form:textarea path="aspettative" rows="5"
								placeholder="aspettative (*)" class="form-control" ></form:textarea>
						</div>

						<!-- note -->
						<form:errors path="note" cssClass="error" />
						<label> Note: </label>
						<div style="margin-bottom: 25px" class="input-group col-xs-5">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-comment"></i></span>
							<form:textarea path="note" rows="10" cols="180"
								placeholder="Note (*)" class="form-control" />
						</div>

						<!-- TODO: Caricamento del curriculum  glyphicon glyphicon-folder-open -->
						<!-- Curriculum 
						<form:errors path="curriculum" cssClass="error" />
						<label> Curriculum: </label>
						<div style="margin-bottom: 25px" class="input-group col-xs-5">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-folder-open"></i></span>
							<form:input path="curriculum" type="file"
								placeholder="Curriculum" class="form-control" />
						</div> -->



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
					onclick="window.location.href='${pageContext.request.contextPath}/hr/showListCandidati'; return false;"
					class="btn btn-primary" />
			</div>
		</div>

	</div>



</body>
</html>