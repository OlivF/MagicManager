'use strict';
 
angular.module('myApp').factory('CardService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = 'http://localhost:8081/MagicManagerSpringWebMVC/card/';
 
    var factory = {
        fetchAllCards: fetchAllCards,
        createCard: createCard,
        updateCard:updateCard,
        deleteCard:deleteCard
    };
 
    return factory;
 
    function fetchAllCards() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Cards');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function createCard(card) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, card)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating Card');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
 
    function updateCard(card, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, card)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating Card');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function deleteCard(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting Card');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
}]);