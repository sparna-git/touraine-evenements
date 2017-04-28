<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="data"
	value="${requestScope['fr.sparna.touraine.evenements.EventData']}" />
<c:set var="compteur" scope="session" value="0" />
<html>
<head>
<title>Touraine événements</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="resources/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/style.css">
<link rel="stylesheet" href="resources/css/jquery-ui.min.css">
<script src="resources/js/jquery-1.11.3.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<script src="resources/js/jquery-ui.min.js"></script>
<script>

	function concatParameters() {
		var checkedBoxCoches = "";
		
		var compteur = 0;
		for (i = 1; i < ${data.eventTypeLength+1}; i++) {
			if (eval("document.forms.general.evenement" + i
					+ ".checked == true")) {
				compteur++;
				
				if (compteur === 1) {
					checkedBoxCoches += document
							.getElementById('evenement' + i).value;
					${check}
					
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
	
	function remove(eventToRemove){
		
		var evenement="${data.evenement}";
		var array1=["Musique","Dance","Théâtre","Commerce","Alimentaire",
		            "Vente","Festival","Exposition",
		            "Publication", "Art visuel", 
		            "Enfants", "Sport","Social",
		            "Littérature","Livraison",
		            "Education","Comédie"
		            ];
		var array2=["MusicEvent",
		            "DanceEvent","TheaterEvent",
		            "BusinessEvent","FoodEvent",
		            "SaleEvent","Festival","ExhibitionEvent",
		            "PublicationEvent", "VisualArtsEvent", 
		            "ChildrensEvent", "SportsEvent","SocialEvent",
		            "LiteraryEvent","DeliveryEvent",
		            "EducationEvent","ComedyEvent"
		            ];
		for(var i=0; i<array1.length;i++){
			if(eventToRemove===array1[i]){
				eventToRemove=array2[i];
				break;
			}
		}
		
		var n = evenement.indexOf(eventToRemove);
		
	    if(n!=0){
	    	st=evenement.replace("-"+eventToRemove,"");
	    }else{
	    	if(evenement===eventToRemove){
				st=evenement.replace(eventToRemove,"");
			}else{
				st=evenement.replace(eventToRemove+"-","");
			}
	    	
	    }
	    document.forms.newform.evenement.value=st;
	    document.forms.newform.deb.value ="${data.startDate}";
		document.forms.newform.fin.value ="${data.endDate}";
		document.forms.newform.zonesearch.value ="${data.fullText}";
		document.forms.newform.submit();
	}
</script>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"> TOURAINE EVENEMENTS </a>
			</div>
		</div>
	</nav>
	<div class="container">


		<div class="row-content">

			<div class="col-sm-3 sidenav jumbotron" id="critere">
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
							<td><font color="black">A partir du :</font></td>
						</tr>
						<tr>
							<td><input type="text" name="datedeb" id="datedeb" /></td>
						</tr>
						<tr>
							<td><font color="black">Jusqu'au :</font></td>
						</tr>
						<tr>
							<td><input type="text" name="datefin" id="datefin" /></td>
						</tr>

						<c:if test="${data.evenementListName!=null}">
							<c:forEach items="${data.evenementListName}" var="list">
								<tr>
									<td>
										<div class="alert alert-default alert-dismissible"
											role="alert" style="height: 10px;">
											<button type="button" class="close" data-dismiss="alert"
												value="${list}" onclick="remove(this.value)"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
											<strong><font color="black">${list}</font></strong>
										</div>
									</td>
								</tr>
							</c:forEach>
						</c:if>

						<c:if test="${data.evenementListName==null}">
							<c:forEach items="${data.mapType}" var="listtype">
								<tr>
									<td><label> <c:if test="${listtype!=null}">
												<c:set var="compteur" scope="session" value="${compteur+1}" />
												<input type="checkbox" name="evenement${compteur}"
													value="${listtype.mapType}" id="evenement${compteur}">
													<font color="black">${listtype.nom}	(${listtype.nombre})</font>
													
											</c:if>
									</label></td>
								</tr>
							</c:forEach>
						</c:if>

						<tr>
							<td>
								<button id="valid" class="btn btn-default"
									onClick="concatParameters()">Filtrer</button> <a
								class="btn btn-default" href="?">initialiser</a>
							</td>

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
			<div class="col-sm-9">
				<c:if test="${data.resultLenght!=0}">
					<div class="list-group" data-offset="10">

						<c:forEach items="${data.dataList}" var="evenement">
							<div class="media ">
								<div class="media-left">

									<a href="${evenement.sources[0]}"> <img id="imagepath"
										class="media-object img-thumbnail"
										<c:if test="${evenement.imagePath!=null}"> onerror="this.src='resources/img/noimage.png';" src="${evenement.imagePath}"</c:if>
										<c:if test="${evenement.imagePath==null}"> src="resources/img/noimage.png"</c:if>
										style="width: 100px; height: 80px;">
									</a>
								</div>
								<div class="media-body">
									Le ${evenement.datedeb}
									<h6 class="media-heading">
										<strong> <font color="#2ECCFA"> <c:if
													test="${evenement.nom!=null}">
													<a href="${evenement.sources[0]}">${evenement.nom}</a>
												</c:if>
										</font>
										</strong> (${evenement.type})
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
						<img src="resources/img/sparna.jpeg"
							class="img-thumbnail"
							style="width: 10%; height: 50px; margin-left: 10px;"> <img
							src="resources/img/mwebius.jpeg" class="img-thumbnail"
							style="width: 10%; height: 50px; margin-left: 10px;"> 
					</div>
					<br />
					<br />
				</c:if>
				<c:if test="${data.resultLenght==0}">
					<div style="margin: auto;">
						<img src="resources/img/search.jpeg"
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
		
		
		$("#datefin").datepicker();
		$("#datefin").datepicker("option", "dateFormat", "yy-mm-dd");
		$("#datefin").val('${data.endDate}');
		
		$("#datedeb").datepicker();
		$("#datedeb").datepicker("option", "dateFormat", "yy-mm-dd");
		$("#datedeb").val('${data.startDate}');
		
	});
</script>
</html>