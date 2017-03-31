/*
 * Create an html balise who generate an img for the manacost
 * associate to the card id in parameter
 */
angular.module('app.directives.priceSideboard',[])
	.directive('priceSideboard', function (SideboardService, $sce){
		return {
			restrict: 'E',
			template: '<span ng-bind-html="result"></span>',
			scope: {
				deckid: '='
			},
			controller: function ($scope) {
				
				 SideboardService.getCardByDeckId($scope.deckid)
		            .then(
		            function(d) {
		                $scope.cards = d;
		                var total = 0;
		                for (var i = 0; i < $scope.cards.length; i++) {
							total += parseInt($scope.cards[i].card.price)*parseInt($scope.cards[i].quantity);
						}
						
						$scope.priceDeck = total + "&euro;";
						$scope.result = $sce.trustAsHtml($scope.priceDeck);
		            },
		            function(errResponse){
		                console.error('Error while fetching Card');
		            }
		        );
				
				
				
			}
		}
	});