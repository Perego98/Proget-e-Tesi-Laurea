<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<!doctype html>
<html>

<head>
	
	
	
	<title>Login Page</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<!-- Reference Bootstrap files -->
	<link rel="stylesheet"
		 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<style>
		body {font-family: Arial, Helvetica, sans-serif;}
		form {border: 3px solid #f1f1f1;}
		
		input[type=text], input[type=password] {
		  width: 100%;
		  padding: 12px 20px;
		  margin: 8px 0;
		  display: inline-block;
		  border: 1px solid #ccc;
		  box-sizing: border-box;
		}
		
		button {
		  background-color: #4CAF50;
		  color: white;
		  padding: 14px 20px;
		  margin: 8px 0;
		  border: none;
		  cursor: pointer;
		  width: 100%;
		}
		
		button:hover {
		  opacity: 0.8;
		}
		
		.cancelbtn {
		  width: auto;
		  padding: 10px 18px;
		  background-color: #f44336;
		}
		
		.imgcontainer {
		  text-align: center;
		  margin: 24px 0 12px 0;
		}
		
		img.avatar {
		  width: 40%;
		  border-radius: 50%;
		}
		
		.container {
		  padding: 16px;
		}
		
		span.psw {
		  float: right;
		  padding-top: 16px;
		}
		
		/* Change styles for span and cancel button on extra small screens */
		@media screen and (max-width: 300px) {
		  span.psw {
		     display: block;
		     float: none;
		  }
		  .cancelbtn {
		     width: 100%;
		  }
		}
	</style>


</head>

<body>

	<h2>Accedi a Gestione Candidature</h2>

	<!-- Check for login error -->
								
	<c:if test="${param.error != null}">
		
		<div class="alert alert-danger col-xs-offset-1 col-xs-10">
			Username o Password non validi.
		</div>

	</c:if>
		
	<!-- Check for logout -->

	<c:if test="${param.logout != null}">
		            
		<div class="alert alert-success col-xs-offset-1 col-xs-10">
			Ti sei sloggato con successo.
		</div>
    
	</c:if>

	<form action="${pageContext.request.contextPath}/authenticateTheUser"
		method="POST" class="form-horizontal">

		<div class="container">
			<label for="username"><b>Username</b></label> <input type="text"
				placeholder="Inserisci l'Username" name="username" required> 
				
				<label
				for="username"><b>Password</b></label> <input type="password"
				placeholder="Inserisci la Password" name="password" required>

			<button type="submit">Accedi</button>
			
		</div>

		<!-- I'm manually adding tokens -->

		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />

	</form>












</body>
</html>