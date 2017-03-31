<div class="generic-container listForDeck" ng-class="{'displayListForDeck': (mainCtrl.displayListForDeck)}">
          <form>
    <div class="form-group">
      <div class="input-group">
        <div class="input-group-addon"><i class="fa fa-search"></i></div>

        <input type="text" class="form-control" placeholder="Chercher une carte par son nom, son type, son édition etc..." ng-model="searchCard">

      </div>      
    </div>
  </form>
          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead magicfont">Notre Collection - Toutes les cartes</span></div>
              <div class="tablecontainer">
              	
                  <table class="table table-hover">
                      <thead>
                          <tr>
                          	  <th>
                          	  	<a href="javascript:void(0)" ng-click="sortType = 'nameFr'; sortReverse = !sortReverse">
                          	  		<span class="magicfont">Nom Français</span>
                          	  		<i ng-show="sortType == 'nameFr' && !sortReverse" class="fa fa-caret-up" aria-hidden="true"></i>
                          	  		<i ng-show="sortType == 'nameFr' && sortReverse" class="fa fa-caret-down" aria-hidden="true"></i>
                          	  	</a> 
                          	  </th>
                              <th>
                              	<a href="javascript:void(0)" ng-click="sortType = 'nameEn'; sortReverse = !sortReverse">
                              		<span class="magicfont">Nom Anglais</span>
                              		<i ng-show="sortType == 'nameEn' && !sortReverse" class="fa fa-caret-up" aria-hidden="true"></i>
                          	  		<i ng-show="sortType == 'nameEn' && sortReverse" class="fa fa-caret-down" aria-hidden="true"></i>
                              	</a> 
                              </th>
                              <th>
                               <a href="javascript:void(0)" ng-click="sortType = 'type'; sortReverse = !sortReverse">
                               <span class="magicfont">Type</span>
                               <i ng-show="sortType == 'type' && !sortReverse" class="fa fa-caret-up" aria-hidden="true"></i>
                          	  		<i ng-show="sortType == 'type' && sortReverse" class="fa fa-caret-down" aria-hidden="true"></i>
                               </a> 
                               
                              </th>
                              <th>
                               <a href="javascript:void(0)" ng-click="sortType = 'edition'; sortReverse = !sortReverse">
                               	<span  class="magicfont">Edition</span>
                               	<i ng-show="sortType == 'edition' && !sortReverse" class="fa fa-caret-up" aria-hidden="true"></i>
                          	  		<i ng-show="sortType == 'edition' && sortReverse" class="fa fa-caret-down" aria-hidden="true"></i>
                               </a>
                              </th>
                              <th class="center"> 
                              	<a href="javascript:void(0)" ng-click="sortType = 'manaCost'; sortReverse = !sortReverse">
                              		<span class="center magicfont">Coût de mana</span>
                              		<i ng-show="sortType == 'manaCost' && !sortReverse" class="fa fa-caret-up" aria-hidden="true"></i>
                          	  		<i ng-show="sortType == 'manaCost' && sortReverse" class="fa fa-caret-down" aria-hidden="true"></i>
                              	</a>
                              
                              </th>
                              <th class="center"> 
                              	<a href="javascript:void(0)" ng-click="sortType = 'rarity'; sortReverse = !sortReverse">
                              		<span class="center magicfont">Rareté</span>
                              		<i ng-show="sortType == 'rarity' && !sortReverse" class="fa fa-caret-up" aria-hidden="true"></i>
                          	  		<i ng-show="sortType == 'rarity' && sortReverse" class="fa fa-caret-down" aria-hidden="true"></i>
                              	</a>
                              
                              </th>
                              <th class="center"> 
                              	<a href="javascript:void(0)" ng-click="sortType = 'price'; sortReverse = !sortReverse">
                              		<span class="center magicfont">Prix</span>
                              		<i ng-show="sortType == 'price' && !sortReverse" class="fa fa-caret-up" aria-hidden="true"></i>
                          	  		<i ng-show="sortType == 'price' && sortReverse" class="fa fa-caret-down" aria-hidden="true"></i>
                              	</a> 
                              
                              </th>
                              <th class="center"> 
                              	<a href="javascript:void(0)">
                              		<span class="center magicfont">Nb</span>
                              	</a> 
                              
                              </th>
                               <th class="center"> 
                              	<a href="javascript:void(0)">
                              		<span class="center magicfont">Nb Dispo</span>
                              	</a> 
                              
                              </th>
                              <th class="center width10"> 
                              	<a href="javascript:void(0)">
                              		<span class="center magicfont">Quantité à ajouter</span>
                              	</a> 
                              
                              </th>
                              <th></th>
                          
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="u in ctrl.cards | orderBy:sortType:sortReverse | filter:searchCard">
                         	 
                         	  <td class="magicfont"><a ng-href="https://www.magicbazar.fr/recherche/search.php?s={{u.nameFr}}" target="_blank"><span ng-bind="u.nameFr"></a></span></td>
                              <td class="magicfont"><span ng-bind="u.nameEn"></span></td>
                              <td class="magicfont"><span ng-bind="u.type.name"></span></td>
                              <td class="magicfont"><span ng-bind="u.edition.name"></span></td>
                              <td class="center magicfont">
                              	<mana-cost idcard="u.id" str="u.manaCost" url="'<c:url value='/static/img/'/>'"></mana-cost>
                              </td>
                              <td class="center magicfont"><img ng-src="<c:url value='/static/img/{{u.rarity.name}}.gif'/>"></td>
                              <td class="center small"><span ng-bind="u.price"></span>&euro;</td>
                              <td class="center small"><span ng-bind="u.nbItem"></span></td>
                              <td class="center small"><span ng-bind="u.nbDispo"></span></td>
                               <td class="center small">
                               	<input class="quantity{{u.id}} width50" type="number" min="1" max="{{u.nbDispo}}" />
                               </td>
                              <td class="center">
                               <input type="hidden" ng-model="ctrlCD.carddeck.id" />
                              <input type="hidden" ng-model="ctrlCD.carddeck.quantity" value="" />
                              <input type="hidden" ng-model="ctrlCD.carddeck.cardid" value="{{u.id}}" />
                  				<input type="hidden" ng-model="ctrlCD.carddeck.deckid" value="${deck.id}" />
                              <button type="submit" ng-click="ctrlCD.submit(${deck.id}, u)" value="{{!ctrlCD.carddeck.id ? 'Add' : 'Update'}}" class="btn btn-primary custom-width125">Ajouter au Deck</button>  <!-- <button type="button" ng-click="ctrl.remove(u.id)" class="btn btn-danger custom-width">Remove</button>-->
                             <input type="hidden" ng-model="ctrlS.sideboard.id" />
                              <input type="hidden" ng-model="ctrlS.sideboard.quantity" value="" />
                              <input type="hidden" ng-model="ctrlS.sideboard.cardid" value="{{u.id}}" />
                  				<input type="hidden" ng-model="ctrlS.sideboard.deckid" value="${deck.id}" />
                              <br><button type="submit" ng-click="ctrlS.submit(${deck.id}, u)" value="{{!ctrlS.sideboard.id ? 'Add' : 'Update'}}" class="btn btn-warning custom-width125">Ajouter à la réserve</button>  <!-- <button type="button" ng-click="ctrl.remove(u.id)" class="btn btn-danger custom-width">Remove</button>-->
                             
                               
                              </td>
                            
                          </tr>
                          
                      </tbody>
                  </table>
                    
                  
              </div>
          </div>		  
      </div>