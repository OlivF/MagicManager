'use strict';

/* Service Sideboard :
 * - Recupere tous les sideboards 
 * - Recupere toutes les cartes pour un deck
 * - Recupere tous les decks pour une carte
 * - Crée un sideboard
 */

angular.module('myApp').factory('SideboardService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = 'http://localhost:8081/MagicManagerSpringWebMVC/sideboard/';
    
    var factory = {
        fetchAllSideboards: fetchAllSideboards,
        getCardByDeckId:getCardByDeckId,
        getDeckByCardId:getDeckByCardId,
        createSideboard: createSideboard,
        updateSideboard:updateSideboard,
        deleteSideboard:deleteSideboard
    };
 
    return factory;
 
    function fetchAllSideboards() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('SideboardService : Error while fetching All CardDecks');
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
                console.error('SideboardService : Error while get Card By DeckId ' + deckid);
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
                console.error('SideboardService : Error while get Deck by cardID ' + cardid);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
 
    function createSideboard(sideboard) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, sideboard)
            .then(
            function (response) {
                deferred.resolve(response.data);
                fetchAllSideboards();
            },
            function(errResponse){
                console.error('SideboardService : Error while creating Sideboard ', sideboard);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
 
    function updateSideboard(sideboard, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, sideboard)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('SideboardService : Error while updating Sideboard ' + id, sideboard);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function deleteSideboard(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('SideboardService : Error while deleting Sideboard ' + id);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
}]);