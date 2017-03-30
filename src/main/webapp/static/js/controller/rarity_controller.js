'use strict';
 
angular.module('myApp').controller('RarityController', ['$scope', 'RarityService', '$sce', function($scope, RarityService, $sce) {
    var self = this;
    self.raritys = {
    	id : null,
    	name : ''
    };
    self.raritys = [];
 
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
 
    $scope.sortType     = 'name'; // set the default sort type
    $scope.sortReverse  = false;  // set the default sort order
 
    $scope.sortRarityByName = 'name';
    $scope.sortReverseRarityByName = true;
    
    fetchAllRaritys();
    
    /* get all rarity */
    function fetchAllRaritys(){
        RarityService.fetchAllRaritys()
            .then(
            function(d) {
                self.raritys = d;
                console.info('RarityController : Fetching ' + d.length + ' Raritys .... OK');
            },
            function(errResponse){
                console.error('RarityController : Error while fetching Rarity');
            }
        );
    }
 
    /* Add rarity */
    function createRarity(rarity){
        RarityService.createRarity(rarity)
            .then(
            function() {
            	console.info('RarityController : Add Rarity..... OK', rarity);
            	fetchAllRaritys();
            },            
            function(errResponse){
                console.error('RarityController : Error while creating Rarity', rarity);
            }
        );
    }
 
    /* update rarity */
    function updateRarity(rarity, id){
        RarityService.updateRarity(rarity, id)
            .then(
            function() {
            	console.info('RarityController : Update Rarity ' + id + '..... OK', rarity);
            	fetchAllRaritys();
            },
            function(errResponse){
                console.error('RarityController : Error while updating Rarity ' + id, rarity);
            }
        );
    }
 
    /* Supprime une rarity */
    function deleteRarity(id){
        RarityService.deleteRarity(id)
            .then(
            function() {
            	console.info('RarityController : Delete Rarity..... OK', id);
            	fetchAllRaritys();
            },
            function(errResponse){
                console.error('RarityController : Error while deleting Rarity', id);
            }
        );
    }
 
    /* Soumission formulaire ADD/Update */
    function submit() {
    	$('.popin.addRarity').removeClass('displayRarity');
        if(self.rarity.id===null || typeof self.rarity.id === "undefined"){
            console.info('RarityController : Saving New Rarity', self.rarity);
            createRarity(self.rarity);
        }else{
            updateRarity(self.rarity, self.rarity.id);
            console.info('RarityController : Rarity updated with id ', self.rarity.id);
        }
        reset();
    }
 
    /* Mettre à jour une rarity */
    function edit(id){
        console.info('RarityController : id to be edited', id);
        $scope.displayPopinAddClass=true;
        for(var i = 0; i < self.raritys.length; i++){
            if(self.raritys[i].id === id) {
                self.rarity = angular.copy(self.raritys[i]);
                break;
            }
        }
    }
 
    /* Supprime une rarity */
    function remove(id){
        console.info('RarityController : id to be deleted', id);
        if(typeof self.rarity !== "undefined" && self.rarity.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteRarity(id);
    }
 
    /* Reset form and rarity obj */
    function reset(){
        self.rarity = {
        	id : null,
        	name : ''
        };
        //$scope.myFormRarity.$setPristine(); //reset Form
    }    
}]);