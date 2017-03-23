'use strict';
 
angular.module('myApp').controller('RarityController', ['$scope', 'RarityService', '$sce', function($scope, RarityService, $sce) {
    var self = this;
    self.raritys={id:null,name:''};
    self.raritys=[];
 
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
 
    //$scope.displayPopinAddClass = "";
    
    $scope.sortType     = 'name'; // set the default sort type
    $scope.sortReverse  = false;  // set the default sort order
    $scope.searchFish   = '';     // set the default search/filter term
    
    
    $scope.sortRarityByName = 'name';
    $scope.sortReverseRarityByName = true;
    
    fetchAllRaritys();
    
    function fetchAllRaritys(){
        RarityService.fetchAllRaritys()
            .then(
            function(d) {
                self.raritys = d;
            },
            function(errResponse){
                console.error('Error while fetching Rarity');
            }
        );
    }
 
    function createRarity(rarity){
        RarityService.createRarity(rarity)
            .then(
            fetchAllRaritys,
            function(errResponse){
                console.error('Error while creating Rarity');
            }
        );
    }
 
    function updateRarity(rarity, id){
        RarityService.updateRarity(rarity, id)
            .then(
            fetchAllRaritys,
            function(errResponse){
                console.error('Error while updating Rarity');
            }
        );
    }
 
    function deleteRarity(id){
        RarityService.deleteRarity(id)
            .then(
            fetchAllRaritys,
            function(errResponse){
                console.error('Error while deleting Rarity');
            }
        );
    }
 
    function submit() {
    	$('.popin.addRarity').removeClass('displayRarity');
        if(self.rarity.id===null || typeof self.rarity.id === "undefined"){
            console.log('Saving New Rarity', self.rarity);
            createRarity(self.rarity);
        }else{
            updateRarity(self.rarity, self.rarity.id);
            console.log('Rarity updated with id ', self.rarity.id);
        }
        reset();
    }
 
    function edit(id){
        console.log('id to be edited', id);
        $scope.displayPopinAddClass=true;
        for(var i = 0; i < self.raritys.length; i++){
            if(self.raritys[i].id === id) {
                self.rarity = angular.copy(self.raritys[i]);
                break;
            }
        }
    }
 
    function remove(id){
        console.log('id to be deleted', id);
        if(typeof self.rarity !== "undefined" && self.rarity.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteRarity(id);
    }
 
 
    function reset(){
        self.rarity={id:null,name:''};
        //$scope.myFormRarity.$setPristine(); //reset Form
    }    
}]);