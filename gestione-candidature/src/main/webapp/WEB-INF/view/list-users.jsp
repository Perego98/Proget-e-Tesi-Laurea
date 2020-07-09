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
			<h2>List Users</h2>
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
					<th>Username</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>telephone</th>
					<th>qualified</th>
					<th>roles</th>
					<th>sede Assegnamento</th>
					<th>Action</th>
				</tr>
			
				<!-- loop over and print our customers -->
				
				<c:forEach var="tempUser" items="${users}">
				
					<!-- construct an "update" link with customer id -->
					<c:url var="updateLink" value="${pageContext.request.contextPath}/admin/showFormforUpdateUser">
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
						<td> ${tempUser.qualified}</td>
						<td> ${tempUser.roles}</td>
						<td> ${tempUser.sedeAssegnamento}</td>
						
						
						
						<td>
							<!--  <input type="button" value="Update"
											onclick="window.location.href='${updateLink}'; return false;"
											class="update-button"/>		
											
											
											<input type="button" value="Delete"
											onclick="if((confirm('Are you sure you want to delete this user?'))) window.location.href='${deleteLink}'; return false;"
											class="delete-button"/>
											
											-->
						
							<input type="button" value="Update"
											onclick="window.location.href='${updateLink}'; return false;"
											class="update-button"/>
						</td>
						
						<td>				
							<input type="button" value="Delete"
											onclick="if((confirm('Are you sure you want to delete this user?'))) window.location.href='${pageContext.request.contextPath}/admin/deleteUser?userUsername=${tempUser.userName}'; return false;"
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