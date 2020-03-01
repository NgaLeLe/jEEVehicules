<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="styleCSS/newUser.css">
</head>
<body>
	<div >
		<h2 id="degrade">BIENVENUE ... </h2>
	</div>
	<c:choose>
		<c:when test="${param.error=='1'}">
			<!--vérifie param error qui est égal 0 -->
			<h3>  Nom d'utilisateur
					<c:out value="${username}">
					</c:out>
					ou mot de pass n'est pas correct !
			</h3>
		</c:when>
		<c:when test="${param.created=='complete'}">
			<!--vérifie param error qui est égal 0 -->
			<h3>  La création de nouveau compte est réussit. Connectez - vous pour continuer...
			</h3>
		</c:when>
	</c:choose>
	<form id="formulaire" action="/jEEVehicule/login" method="post">
		<div class="degrade">
			Nom d'utilisateur <input name="username" type="text" value=""> 
			<br><br>
			Mot de passe <input name="password" type="password" value="">
		</div>
	<br>
		<div >
			<button type="submit">Login</button>
		</div>
	</form>
	<div >
	<a id="nvcompte" href="/jEEVehicule/newUser">Créer nouveau compte? </a></div>
	
</body>
</html>