<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ajouter la librarie pour travailler JSTL -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List, fr.test.java.modele.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" href="styleCSS/vehicule.css">
		<title>List de véhicule</title>
	<script type="text/javascript">
		function selectTypeVehicule(e) {
			let nameVehicule = e.value.toString();
			if (nameVehicule == "Voiture") {
				document.getElementById("voitureInfo").classList.remove('hide');
				document.getElementById("motoInfo").classList.add('hide');
			}
			if (nameVehicule == "Motocyclette") {
				document.getElementById("voitureInfo").classList.add('hide');
				document.getElementById("motoInfo").classList.remove('hide');
			}
		}
		
		function checkVehicule(e) {
			let val = e.parentElement.parentElement.id;
			let row = document.getElementById(val);
		/* 		let buttonSupprimer = document.getElementsByName("supprimer");
		 */	if (e.checked) {
				e.checked = true;
				row.style.backgroundColor = "#D0D01A";
				document.getElementById('idVehSupUpd').value = e.value;
			}
			if (!e.checked) {
				e.checked = false;
				row.style.removeProperty("background-color");
				document.getElementById('idVehSupUpd').value = "";
			}

		}
		</script>
	</head>
<body>
	<h1 id="degrade">LISTE DE VEHICULES</h1>
	<c:set var="user" value="${username}">
	</c:set>
	<h3>Bonjour ${user}</h3>
	<form id="formulaire" method="post" action="/jEEVehicule/toto"
		name="formulaire">

		<fieldset id="typeVehicule">
			<legend>Type de vehicule</legend>
			Voiture 	<input id="voiture" type="radio" name="ftypeVeh"
				onclick="selectTypeVehicule(this)" value="Voiture" /> Motocyclette	<input
				id="moto" type="radio" name="ftypeVeh"
				onclick="selectTypeVehicule(this)" value="Motocyclette" />
		</fieldset>
		<fieldset id="commun">
			<legend>Informations communnes</legend>
			<div>
				Immatriculation <input classe="ctext" type="text" name="fImma" />
			</div>
			<div>
				Marque <input classe="ctext" type="text" name="fMarque" />
			</div>
			<div>
				Modèle <input classe="ctext" type="text" name="fModele" />
			</div>
		</fieldset>
		<fieldset id="voitureInfo" classe="hide">
			<legend>Voiture</legend>
			Couleur<input classe="ctext" type="text" name="fCouleur" /> <br>Année<input classe="ctext"
				type="number" name="fAnnee" />
		</fieldset>
		<fieldset id="motoInfo" classe="hide">
			<legend>Motocyclette</legend>
			Puissance<input classe="ctext" type="number" name="fPuissance" />
		</fieldset>
		<br>
		<input classe="cButton" id="ajouter" type="submit" name="submit"
			value="Ajouter" /> <input classe="cButton" id="supprimer"
			type="submit" name="submit" value="Supprimer" /> <input
			id="idVehSupUpd" type="hidden" name="VehSupUpd" value="" />
	</form>


	<br />
	<table id="idtable">
		<thead class="list">
			<tr>
				<th>Nb</th>
				<th>Type</th>
				<th>Immatriculation</th>
				<th>Marque</th>
				<th>Modèle</th>
				<th>Nb roues</th>
				<th>Couleur</th>
				<th>Année</th>
				<th>Puissance</th>
			</tr>
		<tbody>
			<c:set var="list" value="${vehicules}" scope="session" />
			<!--déclare un variable nommé list qui va attrape la value dans l'attribut vehicules de parametre request -->
			<c:set var="idVehicule" value="0" scope="page" />
			<c:forEach items="${list}" var="vehicule">
				<c:set var="idVehicule" value="${idVehicule + 1}" scope="page" />
				<!--  -->
				<tr id="idr${idVehicule}">
					<td><input type="checkbox" onclick="checkVehicule(this)"
						name="immatricule" value="${vehicule.getImmatriculation()}"></td>
					<td>${vehicule.getTypeVehicule()}
					<td>${vehicule.getImmatriculation()}</td>
					<td>${vehicule.getMarque()}</td>
					<td>${vehicule.getModele()}</td>
					<td>${vehicule.getNombreRoues()}</td>
					<c:if test="${vehicule.getTypeVehicule() eq 'Voiture'}">
						<td>${vehicule.getCouleur()}</td>
						<td>${vehicule.getAnnee()}</td>
						<td></td>
					</c:if>
					<c:if test="${vehicule.getTypeVehicule() eq 'Motocyclette'}">
						<td></td>
						<td></td>
						<td>${vehicule.getPuissance()}</td>
					</c:if>					
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<form action="/jEEVehicule/Disconnect" method="post">
		<input classe="cButton" type="submit" name="Disconnect"
			value="Deconnexion" />
	</form>
	<!-- 	<form action="get" action= "/jEEVehicule/toto?supprimer=1&idVehicule=">
	<button name="supprimer" value="Supprimer" > Supprimer</button></form>  -->
</body>

</html>