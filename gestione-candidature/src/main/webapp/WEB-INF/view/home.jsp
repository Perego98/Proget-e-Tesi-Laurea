<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<html>

<head>
	<title>Company Home Page</title>
</head>

<body>
	<h2>Company Home Page</h2>
	<hr>
	
	<p>
	Welcome to the company home page!
	</p>
	
	<hr>
	
	<!-- display user name and role -->
	
	<p>
		User: <security:authentication property="principal.username" />
		<br><br>
		Role(s): <security:authentication property="principal.authorities" />
		<br><br> 
		First name: ${user.firstName}, Last name: ${user.lastName},
		Email: ${user.email}, Telephone: ${user.telephone}, 
		Sede: ${user.sedeAssegnamento}

	</p>
	
	<security:authorize access="hasRole('MANAGER')">
	
		<!-- Add a link to point to /leaders ... this is for the managers -->
		
		<p>
			<a href="${pageContext.request.contextPath}/leaders">Leadership Meeting</a>
			(Only for Manager peeps)
		</p>

	</security:authorize>	
	
	
	<security:authorize access="hasRole('ADMIN')">  
	
		Qualified: ${user.qualified}
		<c:if test= "${user.qualified}">
		
			<!-- Add a link to point to /systems ... this is for the admins -->
			
			<p>
				<a href="${pageContext.request.contextPath}/systems">IT Systems Meeting</a>
				(Only for Admin peeps)
			</p>
		
		</c:if>
		
	
	</security:authorize>
	
	<hr>
	
	
	<!-- Add a logout button -->
	<form:form action="${pageContext.request.contextPath}/logout" 
			   method="POST">
	
		<input type="submit" value="Logout" />
	
	</form:form>
	
	<security:authorize access="hasRole('ADMIN')">  
		<div>
			<a href="${pageContext.request.contextPath}/register/showRegistrationForm" class="btn btn-primary" role="button" aria-pressed="true">Register New User</a>
		</div>
		</br>
		<div>
			<a href="${pageContext.request.contextPath}/admin/showListUsers" class="btn btn-primary" role="button" aria-pressed="true">Show List of Users</a>
		</div>
	</security:authorize>
	
	<security:authorize access="hasRole('HR')">  
		<c:if test= "${user.qualified}">
			<div>
			
			 <a href="${pageContext.request.contextPath}/registerHr/showCandidatoRegistrationForm" class="btn btn-primary" role="button" aria-pressed="true">Register New Candidato</a>
	
			</div>
		</c:if>
	</security:authorize>
	
</body>

</html>









