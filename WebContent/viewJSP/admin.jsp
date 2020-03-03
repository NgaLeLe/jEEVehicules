<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Page of admin</title>
<link rel="stylesheet" type="text/css" href="styleCSS/admin.css">
</head>
<body>
	<div >
		<h1 id="degrade">GESTION DES UTILISATEURS</h2>
	</div>

	<c:set var="user_session" value="${sessionScope.username}" />
	<c:set var="listUser" value="${sessionScope.listUser }" />

	<c:if test="${not empty user_session}">
		<h3 >Vous êtes ${user_session} -</h3>
	</c:if>

	<table>
		<thead class="list">
			<tr>
				<th scope="col" >Nb</th>
				<th scope="col" >Username</th>
				<th scope="col" >Surname</th>
				<th scope="col" >Courriel</th>
				<th scope="col" >Role</th>

			</tr>
		</thead>
		<tbody>
		<c:set var="i" value="0" scope="page" />  
			<c:forEach var="user" items="${listUser}">
				<tr>
				<c:set var="i" value="${i+1}" scope="page" />
					<th scope="row">${i}</th>
					<td class="row_user">${user.getUserName()}</td>
					<td class="row_user">${user.getSurName()}</td>
					<td class="row_user">${user.getEmail()}</td>
					<td class="row_user">${user.getRole()}</td>


				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<fieldset>
		<form action="/jEEVehicule/toto" method="get">
			<button id="continue" type="submit">Continue</button>
		</form>
		<form action="/jEEVehicule/Disconnect" method="post">
			<button type="submit" name="Disconnect">Deconnexion</button>
		</form>

	</fieldset>
</body>
</html>