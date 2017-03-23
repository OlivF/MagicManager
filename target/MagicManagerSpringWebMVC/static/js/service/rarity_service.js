'use strict';
 
angular.module('myApp').factory('RarityService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = 'http://localhost:8081/MagicManagerSpringWebMVC/rarity/';
 
    var factory = {
        fetchAllRaritys: fetchAllRaritys,
        createRarity: createRarity,
        updateRarity:updateRarity,
        deleteRarity:deleteRarity
    };
 
    return factory;
 
    function fetchAllRaritys() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Raritys');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function createRarity(rarity) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, rarity)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating Rarity');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
 
    function updateRarity(rarity, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, rarity)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating Rarity');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function deleteRarity(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting Rarity');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
}]);