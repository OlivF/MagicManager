/*
 * Create an html balise who generate an img for the manacost
 * associate to the card id in parameter
 */
angular.module('app.directives.priceCollection',[])
	.directive('priceCollection', function (CardService, $sce){
		return {
			restrict: 'E',
			template: '<span ng-bind-html="result"></span>',
			controller: function ($scope) {
				
				 CardService.fetchAllCards()
		            .then(
		            function(d) {
		                $scope.cards = d;
		                var total = 0;
						
						for (var i = 0; i < $scope.cards.length; i++) {
							total += parseInt($scope.cards[i].price)*parseInt($scope.cards[i].nbItem);
						}
						
						$scope.priceCollection = total + "&euro;";
						$scope.result = $sce.trustAsHtml($scope.priceCollection);
		            },
		            function(errResponse){
		                console.error('Error while fetching Card');
		            }
		        );
				
				
				
			}
		}
	});