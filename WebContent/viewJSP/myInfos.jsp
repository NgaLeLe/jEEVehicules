<link href="style/main.css" rel="stylesheet"> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>About me</title>
</head>

<body>
<div> CV</div>
<table>
<tbody>
	<tr>
		<td>
			<h1><c:set var="prenom" value="${prenom}" />
				<c:set var="nom" value="${nom}" />
					${prenom} ${nom}
 			</h1>
 			<h2> Developpeuse Java 
 			</h2>
		</td>
		<td>
			<img src="image/maPhoto.jpg" align="middle">
		</td>
	</tr>
	<tr>
		<td>
			<h2>COMPETENCES</h2>
				<c:set var="list" value="${competences}" />
				<strong>Savoir-faire</strong>
				<c:forEach items="${list}" var="competence">
					<c:if test="${competence.getType() eq 'Savoir-faire'}">
						<br>${competence.getNomCompetence()}</c:if>
				</c:forEach>
			<br>
				<strong>Savoir-être </strong>
				<c:forEach items="${list}" var="competence">
					<c:if test="${competence.getType() eq 'Savoir-être'}">
						<br>${competence.getNomCompetence()}</c:if>
				</c:forEach>
		</td>
		<td>CONTACT
			<br><c:set var="adress" value="${adress}" />${adress}		
			<br><c:set var="codePostal" value="${codePostal}" />${codePostal}
			<c:set var="ville" value="${ville}" />, ${ville}
			<c:set var="email" value="${email}" />${email}
		</td>
	</tr>
	<tr>
		<td><h2>EXPERIENCES</h2>
			<c:set var="list2" value="${experiences}" />
			<c:forEach items="${list2}" var="experience">
				<strong>${experience.getNomExperience()} </strong>
				<br>${experience.getPeriode()}: ${experience.getLieu()}
				<br>     ${experience.getDetail()}
				<br>
			</c:forEach>
			
		</td>
		<td>
			<c:set var="presentation" value="${presentation}" />${presentation}
			<br><c:set var="demande" value="${demande}" />${demande}
		</td>
	</tr>
	<tr>
		<td><h2>FORMATION</h2>
			<c:set var="list3" value="${formations}" />
			<c:forEach items="${list3}" var="formation">
				<strong>${formation.getNomFormation()} </strong>
				<br>${formation.getPeriod()}: ${formation.getLieu()}
				<br>
			</c:forEach>
			
		</td><td>
		</td>
	</tr>
</tbody>
</table>
</body>
</html>