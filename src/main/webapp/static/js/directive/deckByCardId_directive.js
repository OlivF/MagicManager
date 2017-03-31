/*
 * Create an html balise who generate a dropdown list containing
 * decks associate to the card id in parameter
 */
angular.module('app.directives.decksList',[])
	.directive('decksList', function (CarddeckService, CardService){
		return {
			restrict: 'E',
			scope: {
				idcard: '=',
				url: '='
			},
			template: '<div ng-show="showDropdownList" class="dropdown dropdown-scroll dropdownType">'+
		    			'<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown">'+
	    					'<span class="deckChoose">Decks</span>'+
	    						'<i class="fa fa-caret-down fa-2x" aria-hidden="true"></i>'+
	    				'</button>'+
	    				'<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">'+ 
	    					'<li role="presentation">'+
	    						'<div class="input-group input-group-sm search-control">'+ 
	    							'<span class="input-group-addon"><span class="glyphicon glyphicon-search"></span></span>'+
	    							'<input type="text" class="form-control" placeholder="Chercher un deck" ng-model="queryCD"></input>'+
	    						'</div>'+
	    					'</li>'+
	    					'<li role="presentation" ng-repeat="carddeck in decksListFinal | filter:queryCD"><a href="/MagicManagerSpringWebMVC/deckInfo/{{carddeck.deck.id}}"><span class="typeSelector">{{carddeck.deck.name}}</span></a></li>'+
					    '</ul>'+
						'</div>'+
						'<button type="button" ng-show="!showDropdownList" ng-click="remove(idcard)" class="btn btn-danger custom-width">Supprimer</button>',
			controller: function ($scope) {
				
				$scope.remove = function (id) {
					if (confirm("Etes vous sur de vouloir supprimer cette carte?")) {
						CardService.deleteCard(id)
				            .then(
				            function() {
				            	console.info('CardController : Delete Card with id ' + id + ' ..... OK');
				            	//fetchAllCards();
				            	document.location.reload();
				            },
				            function(errResponse){
				                console.error('CardController : Error while deleting Card From CardService');
				            }
				        );
				    }
					
				}
				
				CarddeckService.getDeckByCardId($scope.idcard)
	    		.then(
	    			function(d) {
	    				$scope.decksListFinal = d;
	    				$scope.showDropdownList = true;
	    				if($scope.decksListFinal.length === 0 ) {
	    					$scope.showDropdownList = false;
	    				}
	    			},
	    			function(errResponse) {
	    				console.error('Error while fetching Decks with cardId '+ $scope.idcard);
	    			}
	    		);
			}
		}
	});