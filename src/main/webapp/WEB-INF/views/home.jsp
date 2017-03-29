<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>Magic the Gathering - Accueil</title> 
   	
   	<!-- Load Bootstrap CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		
	<!-- Load Static CSS -->
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
		
	<!-- Load Font Awesome CSS -->
	<link href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css' rel='stylesheet' type='text/css'>
  </head>
    
  <body class="home" ng-app="myApp" ng-controller="MainController as mainCtrl">
  	<div ng-controller="CardController as ctrl">
  		<div ng-controller="DeckController as ctrlD">
		  	<div id="logo"><img src="<c:url value='/static/img/magic-logo.png' />" /></div>
		  	<div id="locale"></div>
		  	<div id="nbCard"><span>Nombre de cartes : </span><span class="result" ng-bind="ctrl.cards.length"></span></div>
		  	<div id="nbDeck"><span>Nombre de decks : </span><span class="result" ng-bind="ctrlD.decks.length"></span></div>
		  	<div id="price" class="bold"><span>Prix total de la collection : </span><price-collection></price-collection></div>
		  	
		  	<div class="container">
		  	<div class="btn-group btn-group-justified" role="group" aria-label="...">
			  <div class="btn-group btn-group-lg" role="group">
			    <a href="/MagicManagerSpringWebMVC/cardList"><button type="button" class="btn btn-danger">Liste des cartes</button></a>
			  </div>
			  <div class="btn-group" role="group">
			    
			  </div>
			  <div class="btn-group btn-group-lg" role="group">
			    <a href="/MagicManagerSpringWebMVC/deckList"><button type="button" class="btn btn-danger">Liste des decks</button></a>
			  </div>
			</div>
			</div>
	  	</div>
  	</div>
  	
  	<!-- Load jQuery  -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	
	<!-- Load Bootstrap JS -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	<!-- Load Angular JS -->
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
	
	<!-- Load Controllers and Services and Directives-->
	<script src="<c:url value='/static/js/app.js' />"></script>
	<script src="<c:url value='/static/js/controller/main_controller.js' />"></script>
	<script src="<c:url value='/static/js/service/card_service.js' />"></script>
	<script src="<c:url value='/static/js/controller/card_controller.js' />"></script>
	<script src="<c:url value='/static/js/service/edition_service.js' />"></script>
	<script src="<c:url value='/static/js/controller/edition_controller.js' />"></script>
	<script src="<c:url value='/static/js/service/rarity_service.js' />"></script>
	<script src="<c:url value='/static/js/controller/rarity_controller.js' />"></script>
	<script src="<c:url value='/static/js/service/type_service.js' />"></script>
	<script src="<c:url value='/static/js/controller/type_controller.js' />"></script>
	<script src="<c:url value='/static/js/service/deck_service.js' />"></script>
	<script src="<c:url value='/static/js/controller/deck_controller.js' />"></script>
	<script src="<c:url value='/static/js/service/carddeck_service.js' />"></script>
	<script src="<c:url value='/static/js/controller/carddeck_controller.js' />"></script>
	<script src="<c:url value='/static/js/directive/deckByCardId_directive.js' />"></script>
	<script src="<c:url value='/static/js/directive/manaCostByCardId_directive.js' />"></script>
	<script src="<c:url value='/static/js/directive/cardsByDeckId_directive.js' />"></script>
	<script src="<c:url value='/static/js/directive/deckColor_directive.js' />"></script>
	<script src="<c:url value='/static/js/directive/priceCollection_directive.js' />"></script>
	<!-- Load Additional script JS -->
	<script src="<c:url value='/static/js/script.js' />"></script>
	
  	<script type="text/javascript">
		function afficher() {
		  var lD = new Date();
		  document.getElementById('locale').innerHTML = lD.toLocaleString();
		}
		 
		window.onload=function() {
		  afficher();
		  setInterval(afficher,1000);
		}
	</script>
  </body>  
</html>