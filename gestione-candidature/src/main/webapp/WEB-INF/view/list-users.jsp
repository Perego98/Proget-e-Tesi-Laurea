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
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
			
				<!-- loop over and print our customers -->
				
				<c:forEach var="tempUser" items="${users}">
				
					<!-- construct an "update" link with customer id 
					<c:url var="updateLink" value="/customer/showFormForUpdate">
						<c:param name="customerId" value="${tempCustomer.id}"/>
					</c:url> -->
					
					<!-- construct an "delete" link with customer id 
					<c:url var="deleteLink" value="/customer/delete">
						<c:param name="customerId" value="${tempCustomer.id}"/>
					</c:url> -->
						
				
					<tr>
						<td> ${tempUser.userName}</td>
						<td> ${tempUser.firstName}</td>
						<td> ${tempUser.lastName}</td>
						<td> ${tempUser.email}</td>
						
						<td>
							<input type="button" value="Update"
											onclick="window.location.href='${updateLink}'; return false;"
											class="update-button"/>		
						
							<input type="button" value="Delete"
											onclick="if((confirm('Are you sure you want to delete this customer?'))) window.location.href='${deleteLink}'; return false;"
											class="delete-button"/>
						</td>
					</tr>
				
				</c:forEach>
			
			</table>
		
		</div>
	
	</div>

</body>

</html>