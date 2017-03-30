'use strict';
 
angular.module('myApp').factory('EditionService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = 'http://localhost:8081/MagicManagerSpringWebMVC/edition/';
    
    var factory = {
        fetchAllEditions: fetchAllEditions,
        createEdition: createEdition,
        updateEdition:updateEdition,
        deleteEdition:deleteEdition
    };
 
    return factory;
 
    function fetchAllEditions() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('EditionService: Error while fetching Editions');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function createEdition(edition) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, edition)
            .then(
            function (response) {
                deferred.resolve(response.data);
                fetchAllEditions();
            },
            function(errResponse){
                console.error('EditionService: Error while creating Edition');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
 
    function updateEdition(edition, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, edition)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('EditionService : Error while updating Edition');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function deleteEdition(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('EditionService: Error while deleting Edition');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
}]);