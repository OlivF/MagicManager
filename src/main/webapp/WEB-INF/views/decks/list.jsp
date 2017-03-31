<div class="generic-container">
          <form>
    <div class="form-group">
      <div class="input-group">
        <div class="input-group-addon"><i class="fa fa-search"></i></div>

        <input type="text" class="form-control" placeholder="Chercher un Deck par son nom" ng-model="searchCard">

      </div>      
    </div>
  </form>
          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead magicfont">Notre Collection - Liste de tous les Decks</span> <button type="button" ng-click="displayDeckFct();" class="btn btn-success btnAdd"><i class="fa fa-plus-circle fa-1x" aria-hidden="true"></i> Ajouter un Deck</button></div>
              <div class="tablecontainer">
                  <table class="table table-hover">
                      <thead>
                          <tr>
                           	<th class="width20 center"> 
                              	<a href="javascript:void(0)" ng-click="sortType = 'manaCost'; sortReverse = !sortReverse">
                              		<span class="center magicfont">Couleur</span>
                              		<i ng-show="sortType == 'manaCost' && !sortReverse" class="fa fa-caret-up" aria-hidden="true"></i>
                          	  		<i ng-show="sortType == 'manaCost' && sortReverse" class="fa fa-caret-down" aria-hidden="true"></i>
                              	</a>
                             </th>
                          	  <th class="width60">
                          	  	<a href="javascript:void(0)" ng-click="sortType = 'nameFr'; sortReverse = !sortReverse">
                          	  		<span class="magicfont">Nom</span>
                          	  		<i ng-show="sortType == 'nameFr' && !sortReverse" class="fa fa-caret-up" aria-hidden="true"></i>
                          	  		<i ng-show="sortType == 'nameFr' && sortReverse" class="fa fa-caret-down" aria-hidden="true"></i>
                          	  	</a> 
                          	  </th>
                             <th class="width10 center">
                             <a href="javascript:void(0)" ng-click="sortType = 'prix'; sortReverse = !sortReverse">
                          	  		<span class="magicfont">Prix</span>
                          	  		<i ng-show="sortType == 'prix' && !sortReverse" class="fa fa-caret-up" aria-hidden="true"></i>
                          	  		<i ng-show="sortType == 'prix' && sortReverse" class="fa fa-caret-down" aria-hidden="true"></i>
                          	  	</a> </th>
                          	 <th class="width10"></th>
                             <th class="width10"></th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="u in ctrlD.decks | orderBy:sortType:sortReverse | filter:searchCard">
                         	   <td class="center magicfont">
                              	<deck-color color="u.color" url="'<c:url value='/static/img/'/>'"></deck-color>
                              </td>
                         	  <td class="magicfont"><a href="/MagicManagerSpringWebMVC/deckInfo/{{u.id}}"><span ng-bind="u.name"></span></a></td>
                              <td class="center"><price-deck deckid="u.id"></price-deck></td>
                               <td class="center">
                               	<button type="button" ng-click="displayDeckFct();ctrlD.edit(u.id)" class="btn btn-warning custom-width125">Mettre &agrave; jour</button>
                               </td>
                              <td class="center">
                              <a href="/MagicManagerSpringWebMVC/deckInfo/{{u.id}}"><button type="button" class="btn btn-primary custom-width">Voir</button></a>  <!-- <button type="button" ng-click="ctrl.remove(u.id)" class="btn btn-danger custom-width">Remove</button>-->
                              </td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>		  
      </div>