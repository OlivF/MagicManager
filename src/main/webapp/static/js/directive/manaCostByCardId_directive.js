/*
 * Create an html balise who generate an img for the manacost
 * associate to the card id in parameter
 */
angular.module('app.directives.manaCost',[])
	.directive('manaCost', function ($sce){
		return {
			restrict: 'E',
			scope: {
				idcard: '=',
				url: '=',
				str: '='
			},
			template: '<span ng-bind-html="result"></span>',
			controller: function ($scope) {
				$scope.manaCostCard = '';
		    	var array = [];
		    	for ( var j = 0; j < $scope.str.length; j++ ) {
		    		array.push($scope.str.substring(j, j + 1));
		    	}
		    	for ( var i = 0; i < array.length; i++ ) {
		    		$scope.manaCostCard += '<img src="'+$scope.url+array[i]+'.gif" />';
		    	}
				$scope.result = $sce.trustAsHtml($scope.manaCostCard);
			}
		}
	});