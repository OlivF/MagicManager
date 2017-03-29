/*
 * Create an html balise who generate an img for the manacost
 * associate to the card id in parameter
 */
angular.module('app.directives.deckColor',[])
	.directive('deckColor', function ($sce){
		return {
			restrict: 'E',
			scope: {
				color: '=',
				url: '=',
			},
			template: '<span ng-bind-html="result"></span>',
			controller: function ($scope) {
				$scope.manaCostCard = '';
				console.log($scope.color);
		    	var array = [];
		    	for ( var j = 0; j < $scope.color.length; j++ ) {
		    		array.push($scope.color.substring(j, j + 1));
		    	}
		    	for ( var i = 0; i < array.length; i++ ) {
		    		$scope.manaCostCard += '<img src="'+$scope.url+array[i]+'.gif" />';
		    	}
		    	
				$scope.result = $sce.trustAsHtml($scope.manaCostCard);
			}
		}
	});