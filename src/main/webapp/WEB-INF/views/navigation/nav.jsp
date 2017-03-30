<div class="generic-container">
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="<c:url value='/'/>">ACCUEIL</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-left">
      	<li><a href="<c:url value='/cardList'/>">VOIR TOUTES LES CARTES</a></li>
      	<li><a href="<c:url value='/deckList'/>">VOIR TOUS LES DECKS</a></li>
      	<li><a href="<c:url value='/listOther'/>">VOIR TOUS LES TYPE/EDITION/RARETE</a></li>
      </ul>
      
      <ul class="nav navbar-nav navbar-right">
        <li class="addCardBtn" ng-click="displayCardFct();" ><a href="javascript:void(0);">AJOUTER UNE CARTE</a></li>
        <li ng-click="displayDeckFct();"><a href="javascript:void(0);">AJOUTER UN DECK</a></li>
        <li class="dropdown">
        	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">AJOUTER ...<span class="caret"></span></a>
	        <ul class="dropdown-menu">
	            <li><a ng-click="displayTypeFct();" href="javascript:void(0);">AJOUTER UN TYPE</a></li>
	            <li><a ng-click="displayEditionFct();" href="javascript:void(0);">AJOUTER UNE EDITION</a></li>
	            <li><a ng-click="displayRarityFct();" href="javascript:void(0);">AJOUTER UNE RARETE</a></li>
	        </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
</div>