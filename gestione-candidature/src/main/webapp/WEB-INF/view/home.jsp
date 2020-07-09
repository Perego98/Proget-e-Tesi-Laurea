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
	
	

	<!-- Gestione della creazione / visualizzazione di nuovi utenti -->
	<security:authorize access="hasRole('ADMIN')">  
		<tr>
			<th>
					<input type="button" 
						value="Register New User"
						onclick="window.location.href='${pageContext.request.contextPath}/admin/showRegistrationForm'; return false;"
						class="delete-button" /> 
						<!-- <a href="${pageContext.request.contextPath}/admin/showRegistrationForm" class="btn btn-primary" role="button" aria-pressed="true">Register New User</a> -->
			</th>
			<th>
				<input type="button" 
						value="Show List of Users"
						onclick="window.location.href='${pageContext.request.contextPath}/admin/showListUsers'; return false;"
						class="delete-button" /> 
					<!--<a href="${pageContext.request.contextPath}/admin/showListUsers" class="btn btn-primary" role="button" aria-pressed="true">Show List of Users</a>  -->
			</th>
		</tr>
		</br>
	</security:authorize>
	
	
	
	<!-- Gestione della creazione / visualizzazione di nuovi Candidati -->
	<security:authorize access="hasRole('HR')">  
		<c:if test= "${user.qualified}">
			</br>
			
				<tr>
					<th>
						<input type="button" 
							value="Register New Candidato"
							onclick="window.location.href='${pageContext.request.contextPath}/hr/showCandidatoRegistrationForm'; return false;"
							class="delete-button" /> 
						 <!--<a href="${pageContext.request.contextPath}/hr/showCandidatoRegistrationForm" class="btn btn-primary" role="button" aria-pressed="true">Register New Candidato</a>-->
					</th>
					
					<th>
						<input type="button" 
							value="Show List of Candidato"
							onclick="window.location.href='${pageContext.request.contextPath}/hr/showListCandidati'; return false;"
							class="delete-button" /> 
						 <!--<a href="${pageContext.request.contextPath}/hr/showCandidatoRegistrationForm" class="btn btn-primary" role="button" aria-pressed="true">Register New Candidato</a>-->
					</th>
				</tr>
			</br>
		</c:if>
	</security:authorize>
	
	</br></br>
	
		<!-- Add a logout button -->
	<form:form action="${pageContext.request.contextPath}/logout" 
			   method="POST">
	
		<input type="submit" value="Logout" />
	
	</form:form>
	
</body>

</html>









