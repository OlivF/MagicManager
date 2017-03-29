/*
 * Create an html balise who generate a dropdown list containing
 * decks associate to the card id in parameter
 */
angular.module('app.directives.decksList',[])
	.directive('decksList', function (CarddeckService){
		return {
			restrict: 'E',
			scope: {
				idcard: '='
			},
			template: '<div class="dropdown dropdown-scroll dropdownType">'+
		    			'<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown">'+
	    					'<span class="deckChoose">Decks</span>'+
	    						'<i class="fa fa-caret-down fa-2x" aria-hidden="true"></i>'+
	    				'</button>'+
	    				'<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">'+ 
	    					'<li role="presentation">'+
	    						'<div class="input-group input-group-sm search-control">'+ 
	    							'<span class="input-group-addon"><span class="glyphicon glyphicon-search"></span></span>'+
	    							'<input type="text" class="form-control" placeholder="Query" ng-model="queryCD"></input>'+
	    						'</div>'+
	    					'</li>'+
	    					'<li role="presentation" ng-repeat="carddeck in decksListFinal | filter:queryCD"><a href="#"><span class="typeSelector">{{carddeck.deck.name}}</span></a></li>'+
					    '</ul>'+
						'</div>',
			controller: function ($scope) {
				CarddeckService.getDeckByCardId($scope.idcard)
	    		.then(
	    			function(d) {
	    				$scope.decksListFinal = d;
	    			},
	    			function(errResponse) {
	    				console.error('Error while fetching Decks with cardId '+ $scope.idcard);
	    			}
	    		);
			}
		}
	});