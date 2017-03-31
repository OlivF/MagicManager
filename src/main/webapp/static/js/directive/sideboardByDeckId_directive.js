/*
 * Create an html balise who generate a dropdown list containing
 * decks associate to the card id in parameter
 */
angular.module('app.directives.listSideboard',[])
	.directive('listSideboard', function (CardService, SideboardService){
		return {
			restrict: 'E',
			scope: {
				deckid: '=',
				deckname: '=',
				url: '=',
			},
			template: '<div class="generic-container">'+
	          			'<form>'+
	          				'<div class="form-group">'+
	          					'<div class="input-group">'+
	          						'<div class="input-group-addon"><i class="fa fa-search"></i></div>'+
	          						'<input type="text" class="form-control" placeholder="Chercher une carte" ng-model="searchCard">'+
	          					'</div>'+      
	          				'</div>'+
	          			'</form>'+
	          			'<div class="panel panel-default">'+
	          				'<div class="panel-heading"><span class="lead magicfont">Liste de la r&eacute;serve du deck : {{deckname}}</span></div>'+
	          					'<div class="tablecontainer">'+
	          						'<table class="table table-hover">'+
	          							'<thead>'+
	          								'<tr>'+
	          									'<th>'+
	          										'<a href="javascript:void(0)" ng-click="sortType = \'nameFr\'; sortReverse = !sortReverse">'+
	          										'<span class="magicfont">Nom Fran&ccedil;ais</span>'+
	          										'<i ng-show="sortType == \'nameFr\' && !sortReverse" class="fa fa-caret-up" aria-hidden="true"></i>'+
	          										'<i ng-show="sortType == \'nameFr\' && sortReverse" class="fa fa-caret-down" aria-hidden="true"></i>'+
	          										'</a>'+ 
	          									'</th>'+
	          									'<th>'+
	          	                              		'<a href="javascript:void(0)" ng-click="sortType = \'nameEn\'; sortReverse = !sortReverse">'+
	          	                              		'<span class="magicfont">Nom Anglais</span>'+
	          	                              		'<i ng-show="sortType == \'nameEn\' && !sortReverse" class="fa fa-caret-up" aria-hidden="true"></i>'+
	          	                          	  		'<i ng-show="sortType == \'nameEn\' && sortReverse" class="fa fa-caret-down" aria-hidden="true"></i>'+
	          	                          	  		'</a>'+ 
	          	                          	  	'</th>'+
	          	                          	  	'<th>'+
	          	                          	  		'<a href="javascript:void(0)" ng-click="sortType = \'type\'; sortReverse = !sortReverse">'+
	          	                          	  		'<span class="magicfont">Type</span>'+
	          	                          	  		'<i ng-show="sortType == \'type\' && !sortReverse" class="fa fa-caret-up" aria-hidden="true"></i>'+
	          	                          	  		'<i ng-show="sortType == \'type\' && sortReverse" class="fa fa-caret-down" aria-hidden="true"></i>'+
	          	                          	  		'</a>'+ 
	          	                                '</th>'+
	          	                                '<th>'+
	          	                                	'<a href="javascript:void(0)" ng-click="sortType = \'edition\'; sortReverse = !sortReverse">'+
	          	                                	'<span  class="magicfont">Edition</span>'+
	          	                                	'<i ng-show="sortType == \'edition\' && !sortReverse" class="fa fa-caret-up" aria-hidden="true"></i>'+
	          	                          	  		'<i ng-show="sortType == \'edition\' && sortReverse" class="fa fa-caret-down" aria-hidden="true"></i>'+
	          	                          	  		'</a>'+
	          	                          	  	'</th>'+
	          	                          	  	'<th class="center">'+ 
	          	                          	  		'<a href="javascript:void(0)" ng-click="sortType = \'manaCost\'; sortReverse = !sortReverse">'+
	          	                              		'<span class="center magicfont">Cout de mana</span>'+
	          	                              		'<i ng-show="sortType == \'manaCost\' && !sortReverse" class="fa fa-caret-up" aria-hidden="true"></i>'+
	          	                          	  		'<i ng-show="sortType == \'manaCost\' && sortReverse" class="fa fa-caret-down" aria-hidden="true"></i>'+
	          	                          	  		'</a>'+
	          	                                '</th>'+
	          	                                '<th class="center">'+ 
	          	                              		'<a href="javascript:void(0)" ng-click="sortType = \'rarity\'; sortReverse = !sortReverse">'+
	          	                              		'<span class="center magicfont">Raret&eacute;</span>'+
	          	                              		'<i ng-show="sortType == \'rarity\' && !sortReverse" class="fa fa-caret-up" aria-hidden="true"></i>'+
	          	                          	  		'<i ng-show="sortType == \'rarity\' && sortReverse" class="fa fa-caret-down" aria-hidden="true"></i>'+
	          	                          	  		'</a>'+
	          	                                '</th>'+
	          	                                '<th class="center">'+ 
	          	                              		'<a href="javascript:void(0)" ng-click="sortType = \'price\'; sortReverse = !sortReverse">'+
	          	                              		'<span class="center magicfont">Prix</span>'+
	          	                              		'<i ng-show="sortType == \'price\' && !sortReverse" class="fa fa-caret-up" aria-hidden="true"></i>'+
	          	                          	  		'<i ng-show="sortType == \'price\' && sortReverse" class="fa fa-caret-down" aria-hidden="true"></i>'+
	          	                          	  		'</a>'+ 
	          	                                '</th>'+
	          	                                '<th class="center">'+ 
	          	                              		'<a href="javascript:void(0)">'+
	          	                              		'<span class="center magicfont">Nb</span>'+
	          	                              		'</a>'+ 
	          	                                 '</th>'+
	          	                                 '<th class="width50"></th>'+
	          	                               '</tr>'+
	          								'</thead>'+
	          								'<tbody>'+
	          								'<tr ng-repeat="u in sideboardListFinal | orderBy:sortType:sortReverse | filter:searchCard">'+
	          									'<td class="magicfont"><a ng-href="https://www.magicbazar.fr/recherche/search.php?s={{u.card.nameFr}}" target="_blank"><span ng-bind="u.card.nameFr"></span></a></td>'+
	          									'<td class="magicfont"><span ng-bind="u.card.nameEn"></span></td>'+
	          									'<td class="magicfont"><span ng-bind="u.card.type.name"></span></td>'+
	          									'<td class="magicfont"><span ng-bind="u.card.edition.name"></span></td>'+
	          									'<td class="center magicfont">'+
	          	                              		'<mana-cost idcard="u.id" str="u.card.manaCost" url="url"></mana-cost>'+
	          	                              	'</td>'+
	          	                              	'<td class="center magicfont"><img ng-src="{{url}}{{u.card.rarity.name}}.gif"></td>'+
	          	                              	'<td class="center small"><span ng-bind="u.card.price"></span>&euro;</td>'+
	          	                              	'<td class="center small"><span ng-bind="u.quantity"></span></td>'+
	          	                              	'<td><button type="button" ng-click="remove(u)" class="btn btn-danger custom-width">Supprimer</button></td>'+
	                                        '</tr>'+
	                                        '</tbody>'+
	                                      '</table>'+
	                                      '</div>'+
	                                      '</div>'+		  
	                                      '</div>',
			controller: function ($scope) {
				
				$scope.remove = function (sideboard) {
					if (confirm("Etes vous sur de vouloir supprimer cette carte de ce deck?")) {
						
			        	 
						 CardService.findCardById(sideboard.card.id)
				            .then(
				            function(d) {
				            	var card = d;
				            	card.nbDispo = parseInt(card.nbDispo) +parseInt(sideboard.quantity);
				            	
				            	CardService.updateCard(card, card.id)
					             .then(
					             function(d) {
					            	 
					            	 SideboardService.deleteSideboard(sideboard.id)
							            .then(
							            function() {
							            	document.location.reload();
							            },
							            function(errResponse){
							                console.error('Error while deleting Edition');
							            }
							        );
					             },
					             function(errResponse){
					                 console.error('CardController : Error while updating Card from CardService');
					             }
					         );
				            	
				            },
				            function(errResponse){
				                console.error('Error while deleting Edition');
				            }
				        );
						
						
						
						
			        	 
					}
				}
				
				SideboardService.getCardByDeckId($scope.deckid)
	    		.then(
	    			function(d) {
	    				$scope.sideboardListFinal = d;
	    			},
	    			function(errResponse) {
	    				console.error('Error while fetching Decks with cardId '+ $scope.idcard);
	    			}
	    		);
			}
		}
	});