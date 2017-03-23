'use strict';
 
angular.module('myApp').controller('TypeController', ['$scope', 'TypeService', '$sce', function($scope, TypeService, $sce) {
    var self = this;
    self.type={id:null,name:''};
    self.types=[];
 
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
 
    //$scope.displayPopinAddClass = "";
    
    $scope.sortType     = 'name'; // set the default sort type
    $scope.sortReverse  = false;  // set the default sort order
    $scope.searchFish   = '';     // set the default search/filter term
    
    
    $scope.sortTypeByName = 'name';
    $scope.sortReverseTypeByName = true;
    
    fetchAllTypes();
    
    function fetchAllTypes(){
        TypeService.fetchAllTypes()
            .then(
            function(d) {
                self.types = d;
            },
            function(errResponse){
                console.error('Error while fetching Type');
            }
        );
    }
 
    function createType(type){
        TypeService.createType(type)
            .then(
            fetchAllTypes,
            function(errResponse){
                console.error('Error while creating Type');
            }
        );
    }
 
    function updateType(type, id){
        TypeService.updateType(type, id)
            .then(
            fetchAllTypes,
            function(errResponse){
                console.error('Error while updating Type');
            }
        );
    }
 
    function deleteType(id){
        TypeService.deleteType(id)
            .then(
            fetchAllTypes,
            function(errResponse){
                console.error('Error while deleting Type');
            }
        );
    }
 
    function submit() {
    	$('.popin.addType').removeClass('displayType');
        if(self.type.id===null || typeof self.type.id === "undefined"){
            console.log('Saving New Type', self.type);
            createType(self.type);
        }else{
            updateType(self.type, self.type.id);
            console.log('Type updated with id ', self.type.id);
        }
        reset();
    }
 
    function edit(id){
        console.log('id to be edited', id);
        $scope.displayPopinAddClass=true;
        for(var i = 0; i < self.types.length; i++){
            if(self.types[i].id === id) {
                self.type = angular.copy(self.types[i]);
                break;
            }
        }
    }
 
    function remove(id){
        console.log('id to be deleted', id);
        if(typeof self.type !== "undefined" && self.type.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteType(id);
    }
 
 
    function reset(){
        self.type={id:null,name:''};
       // $scope.myFormType.$setPristine(); //reset Form
    }    
}]);