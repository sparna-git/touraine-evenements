<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="data"
	value="${requestScope['fr.sparna.touraine.evenements.EvenementData']}" />
<c:set var="compteur" value="0" />
<html>
<head>
<title>Touraine événements</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="theme-paper/resource/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="theme-paper/resource/css/style.css">
<link rel="stylesheet" href="theme-paper/resource/css/jquery-ui.min.css">
<script src="theme-paper/resource/js/jquery-1.11.3.js"></script>
<script src="theme-paper/resource/bootstrap/js/bootstrap.min.js"></script>
<script src="theme-paper/resource/js/jquery-ui.min.js"></script>
<script>

	$(function() {
		$("#datefin").datepicker();
		$("#datefin").datepicker("option", "dateFormat", "yy-mm-dd");
		$("#datedeb").datepicker();
		$("#datedeb").datepicker("option", "dateFormat", "yy-mm-dd");

	});
	function concatParameters() {
		var checkedBoxCoches = "";
		
		var compteur = 0;
		for (i = 1; i < 18; i++) {
			if (eval("document.forms.general.evenement" + i
					+ ".checked == true")) {
				compteur++;
				if (compteur === 1) {
					checkedBoxCoches += document
							.getElementById('evenement' + i).value;
					
				} else if (compteur > 1) {
					checkedBoxCoches += "-"
							+ document.getElementById('evenement' + i).value;
					
					
				}
			}
		}
		document.forms.newform.evenement.value = checkedBoxCoches;
		document.forms.newform.deb.value = document.forms.general.datedeb.value;
		document.forms.newform.fin.value = document.forms.general.datefin.value;
		document.forms.newform.zonesearch.value = document.forms.general.search.value;
		document.forms.newform.submit();
		
	}
</script>
</head>
<body>
	<div class="container">
		<h3 style="text-align: center; margin-bottom: 50px;">Touraine
			événements</h3>
		<div class="row-content">

			<div class="col-sm-2 sidenav">
				<form name="general" method="get" onSubmit="return false">
					<div class="input-group search">
						<input type="text" class="form-control " value="${data.fullText}"
							name="search" placeholder="Je recherche..."> <span
							class="input-group-btn search">
							<button class="btn btn-default search"
								onClick="concatParameters()">
								<span class="glyphicon glyphicon-search"></span>
							</button>
						</span>
					</div>
					<table class="search">
						<tr>
							<td>A partir du :</td>
						</tr>
						<tr>
							<td><input type="text" name="datedeb"
								value="${data.startDate}" id="datedeb"></td>
						</tr>
						<tr>
							<td>Jusqu'au :</td>
						</tr>
						<tr>
							<td><input type="text" name="datefin"
								value="${data.endDate}" id="datefin"></td>
						</tr>
						<tr>
							<td>
								<label>	
										<input type="checkbox" name="evenement1"value="MusicEvent" id="evenement1">
											Musique		
								</label>
							</td>
						</tr>
						<tr>
							<td><label><input type="checkbox" id="evenement2"
									name="evenement2" value="DanceEvent">Dance</label></td>
						</tr>
						<tr>
							<td><label><input type="checkbox" id="evenement3"
									name="evenement3" value="ChildrensEvent">Enfants</label></td>
						</tr>
						<tr>
							<td><label><input type="checkbox" id="evenement4"
									name="evenement4" value="Festival">Festival</label></td>
						</tr>
						<tr>
							<td><label><input type="checkbox" id="evenement5"
									name="evenement5" value="TheaterEvent">Théatre</label></td>
						</tr>
						<tr>
							<td><label><input type="checkbox" id="evenement6"
									name="evenement6" value="ComedyEvent">Comédie</label></td>
						</tr>
						<tr>
							<td><label><input type="checkbox" id="evenement7"
									name="evenement7" value="SportsEvent">Sport</label></td>
						</tr>
						<tr>
							<td><label><input type="checkbox" id="evenement8"
									name="evenement8" value="SocialEvent">Social</label></td>
						</tr>
						<tr>
							<td><label><input type="checkbox" id="evenement9"
									name="evenement9" value="LiteraryEvent">Littérature</label></td>
						</tr>
						<tr>
							<td><label><input type="checkbox" id="evenement10"
									name="evenement10" value="BusinessEvent">Commerce</label></td>
						</tr>
						<tr>
							<td><label><input type="checkbox" id="evenement11"
									name="evenement11" value="DeliveryEvent">Livraison</label></td>
						</tr>
						<tr>
							<td><label><input type="checkbox" id="evenement12"
									name="evenement12" value="CourseInstance">Cours</label></td>
						</tr>
						<tr>
							<td><label><input type="checkbox" id="evenement13"
									name="evenement13" value="EducationEvent">Education</label></td>
						</tr>
						<tr>
							<td><label><input type="checkbox" id="evenement14"
									name="evenement14" value="ExhibitionEvent">Exposition</label></td>
						</tr>
						<tr>
							<td><label><input type="checkbox" id="evenement15"
									name="evenement15" value="FoodEvent">Alimentaire</label></td>
						</tr>
						<tr>
							<td><label><input type="checkbox" id="evenement16"
									name="evenement16" value="PublicationEvent">Publication</label></td>
						</tr>
						<tr>
							<td><label><input type="checkbox" id="evenement17"
									name="evenement17" value="SaleEvent">Vente</label></td>
						</tr>
						<tr>
							<td><button id="valid" onClick="concatParameters()">Filtrer</button></td>
						</tr>

					</table>
				</form>
				<form method="get" id="newform" name="newform" class="search">

					<input type="text" name="evenement" id="evenement" value="">
					<input type="text" name="deb" id="deb"> <input type="text"
						name="fin" id="fin"> <input type="text" name="zonesearch"
						id="zonesearch">
				</form>
			</div>
			<div class="col-sm-10">
				<c:if test="${data.resultLenght!=0}">
					<div class="list-group" data-offset="10">

						<c:forEach items="${data.dataList}" var="evenement">
							<div class="media ">
								<div class="media-left">

									<a href="#"> <img id="imagepath"
										class="media-object img-thumbnail"
										<c:if test="${evenement.imagePath!=null}"> onerror="this.src='theme-paper/resource/img/noimage.png';" src="${evenement.imagePath}"</c:if>
										<c:if test="${evenement.imagePath==null}"> src="theme-paper/resource/img/noimage.png"</c:if>
										style="width: 100px; height: 80px;">
									</a>
								</div>
								<div class="media-body">
									Le ${evenement.datedeb}
									<h6 class="media-heading">
										<strong> <font color="#2ECCFA"> <c:if
													test="${evenement.nom!=null}">${evenement.nom} </c:if>
										</font>
										</strong>
									</h6>
									${evenement.description}<br />
									<c:if test="${evenement.sources!=null}">
										<c:if test="${evenement.sourcesize >3}">
											<a href="${evenement.sources[0]}">${evenement.sources[0]}</a>
											<br />
											<select name="link"
												onChange="location.href=''+this.options[this.selectedIndex].value;">
												<c:forEach items="${evenement.sources}" var="source">
													<option value="${source}">${source}</option>
												</c:forEach>
											</select>
										</c:if>
										<c:if test="${evenement.sourcesize <=3}">
											<c:forEach items="${evenement.sources}" var="source">
												<a href="${source}">${source}</a>
												<br />
											</c:forEach>
										</c:if>

									</c:if>
								</div>
							</div>

						</c:forEach>

					</div>
					<table style="margin: auto;">
						<tr>
							<td><a
								href="?evenement=${data.evenement}&deb=${data.startDate}&fin=${data.endDate}&zonesearch=${data.fullText}&index=${data.index -10}">
									<button
										<c:if test="${data.index==0}">disabled="disabled"</c:if>>Précédent</button>
							</a></td>
							<td><a
								href="?evenement=${data.evenement}&deb=${data.startDate}&fin=${data.endDate}&zonesearch=${data.fullText}&index=${data.index +10}">
									<button
										<c:if test="${data.resultLenght <= 10}">disabled="disabled"</c:if>
										<c:if test="${data.index+10>=data.resultLenght}">disabled="disabled"</c:if>>
										Suivant</button>
							</a></td>
						</tr>
					</table>
					<hr>
					<div style="margin: auto;">
						<img src="theme-paper/resource/img/sparna.jpeg"
							class="img-thumbnail"
							style="width: 10%; height: 50px; margin-left: 10px;"> <img
							src="theme-paper/resource/img/mwebius.jpeg" class="img-thumbnail"
							style="width: 10%; height: 50px; margin-left: 10px;"> <img
							src="theme-paper/resource/img/sparna.jpeg" class="img-thumbnail"
							style="width: 10%; height: 50px; margin-left: 10px;">
					</div>
					<br />
					<br />
				</c:if>
				<c:if test="${data.resultLenght==0}">
					<div style="margin: auto;">
						<img src="theme-paper/resource/img/search.jpeg"
							class="img-thumbnail"
							style="width: 20%; height: 100px; margin: auto;">
						<p>Aucun résultat...</p>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		var newform = document.getElementById('newform');
		newform.style.visibility = "hidden";
		
	});
</script>
</html>