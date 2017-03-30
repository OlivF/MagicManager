'use strict';
 
angular.module('myApp').factory('TypeService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = 'http://localhost:8081/MagicManagerSpringWebMVC/type/';
 
    var factory = {
        fetchAllTypes: fetchAllTypes,
        createType: createType,
        updateType:updateType,
        getTypeById:getTypeById,
        deleteType:deleteType
    };
 
    return factory;
 
    function fetchAllTypes() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('TypeService : Error while fetching Types');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function createType(type) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, type)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('TypeService : Error while creating Type', type);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function updateType(type, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, type)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('TypeService : Error while updating Type ' + id, type);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function deleteType(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('TypeService : Error while deleting Type ' + id);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
}]);