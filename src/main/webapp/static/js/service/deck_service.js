'use strict';
 
angular.module('myApp').factory('DeckService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = 'http://localhost:8081/MagicManagerSpringWebMVC/deck/';
 
    var factory = {
        fetchAllDecks: fetchAllDecks,
        findDeckById: findDeckById,
        createDeck: createDeck,
        updateDeck:updateDeck,
        //deleteCard:deleteCard
    };
 
    return factory;
 
    function fetchAllDecks() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('DeckService : Error while fetching Decks');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function findDeckById( deckId ) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+deckId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('DeckService : Error while fetching Decks by Id ' + deckId);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function createDeck(deck) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, deck)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('DeckService : Error while creating Deck');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
 
    function updateDeck(deck, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, deck)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating Deck');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 /*
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
    }*/
 
}]);