<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Creation new user</title>
<link rel="stylesheet" type="text/css" href="styleCSS/newUser.css">
</head>
<body>
	<h2 id="degrade">CREATE NEW USER</h2>
	<c:choose>
		<c:when test="${param.error=='1'}">
			<h3>Username est exist, choisissez un autre username ! </h3>
			<h3>Re-remplissez ...</h3>
		</c:when>
		<c:when test="${param.error=='2'}">
			<h3>Ne peut pas créer un nouveau compte ! </h3>
		</c:when>
		
		<c:when test="${param.created=='complete'}">
			<h3>La création de nouveau compte est réussite !</h3>
		</c:when>
	</c:choose>

	<form id="formulaire" action="/jEEVehicule/newuser" method="post">
		<div class="degrade">
			Nom d'utilisateur <input name="username" type="text">
		</div>
		<br>
		<div class="degrade">
			Mot de passe <input name="password" type="password" >
		</div>
		<br>
		<div class="degrade">
			Prénom <input name="name" type="text">
		</div>
		<br>
		<div class="degrade">
			Nom <input name="surname" type="text">
		</div>
		<br>
		<div class="degrade">
			Email <input name="email" type="text">
		</div>
		<br>
		<div class="degrade">
			Telephone <input name="telephone" type="text">
		</div>
		<br>

		<div>
			<button type="submit">Créer</button>
			<c:if test="${param.created=='complete'}">
			<a id="idContinue" href="/jEEVehicule/toto"> Continuez ... </a>
		</c:if>
		</div>
	</form>
</body>
</html>