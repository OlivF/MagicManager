'use strict';
 
angular.module('myApp').factory('CarddeckService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = 'http://localhost:8081/MagicManagerSpringWebMVC/carddeck/';
    
    var factory = {
        fetchAllCarddecks: fetchAllCarddecks,
        getCardByDeckId:getCardByDeckId,
        getDeckByCardId:getDeckByCardId,
        createCarddeck: createCarddeck,
        updateCarddeck:updateCarddeck,
        deleteCarddeck:deleteCarddeck
    };
 
    return factory;
 
    function fetchAllCarddecks() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Editions');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function getCardByDeckId(deckid) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+"deck/"+deckid)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating Carddeck');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function getDeckByCardId(cardid) {
        var deferred = $q.defer();
       
        $http.get(REST_SERVICE_URI+"card/"+cardid)
            .then(
            function (response) {
            	deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while get Carddeck by DeckID');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
 
    function createCarddeck(carddeck) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, carddeck)
            .then(
            function (response) {
                deferred.resolve(response.data);
                fetchAllEditions();
            },
            function(errResponse){
                console.error('Error while creating Carddeck');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
 
    function updateCarddeck(carddeck, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, carddeck)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating Carddeck');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function deleteCarddeck(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting Carddeck');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
}]);