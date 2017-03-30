'use strict';
 
angular.module('myApp').controller('TypeController', ['$scope', 'TypeService', '$sce', function($scope, TypeService, $sce) {
    var self = this;
    self.type = {
    	typeId : null,
    	name : ''
    };
    self.types = [];
 
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
 
    $scope.sortType     = 'name'; // set the default sort type
    $scope.sortReverse  = false;  // set the default sort order
    
    $scope.sortTypeByName = 'name';
    $scope.sortReverseTypeByName = true;
    
    fetchAllTypes();
    
    function fetchAllTypes(){
        TypeService.fetchAllTypes()
            .then(
            function(d) {
                self.types = d;
                console.info('TypeController : Fetching ' + d.length + ' Types .... OK');
            },
            function(errResponse){
                console.error('TypeController : Error while fetching Type');
            }
        );
    }
 
    function createType(type){
        TypeService.createType(type)
            .then(
            function() {
            	console.info('TypeController: Add New Type ..... OK', type);
             	fetchAllTypes();
            },
            function(errResponse){
                console.error('TypeController : Error while creating Type');
            }
        );
    }
 
    function updateType(type, id){
        TypeService.updateType(type, id)
            .then(
    		function() {
    			console.info('TypeController: Update Type ..... OK', type);
            	fetchAllTypes();
            },
            function(errResponse){
                console.error('TypeController : Error while updating Type ' + id, type);
            }
        );
    }
 
    function deleteType(id){
        TypeService.deleteType(id)
            .then(
            function() {
            	fetchAllTypes();
            },
            function(errResponse){
                console.error('TypeController : Error while deleting Type ' + id);
            }
        );
    }
 
    /* Soumission form Add/Update */
    function submit() {
    	$('.popin.addType').removeClass('displayType');
        if(self.type.typeId===null || typeof self.type.typeId === "undefined"){
            console.info('TypeController : Saving New Type', self.type);
            createType(self.type);
        }else{
            updateType(self.type, self.type.typeId);
            console.info('TypeController : Type updated with id ', self.type.typeId);
        }
        reset();
    }
 
    /* Mettre à jour un type */
    function edit(id){
        console.info('TypeController : id to be edited', id);
        $scope.displayPopinAddClass = true;
        for(var i = 0; i < self.types.length; i++){
            if(self.types[i].typeId === id) {
                self.type = angular.copy(self.types[i]);
                break;
            }
        }
    }
 
    /* Supprime un type */
    function remove(id){
        console.info('TypeController : id to be deleted', id);
        if(typeof self.type !== "undefined" && self.type.typeId === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteType(id);
    }
 
    /* Reset form and obj */
    function reset(){
        self.type = {
        	typeId :null,
        	name : ''
        };
       // $scope.myFormType.$setPristine(); //reset Form
    }    
}]);