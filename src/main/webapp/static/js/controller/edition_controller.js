'use strict';
 
angular.module('myApp').controller('EditionController', ['$scope', 'EditionService', '$sce', function($scope, EditionService, $sce) {
    var self = this;
    self.edition = {
    	id : null,
    	name : ''
    };
    self.editions= [];
 
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
 
    $scope.sortType     = 'name'; // set the default sort type
    $scope.sortReverse  = false;  // set the default sort order
    
    $scope.sortEditionByName = 'name';
    $scope.sortReverseEditionByName = true;
    
    fetchAllEditions();
    
    /* get all editions */
    function fetchAllEditions(){
        EditionService.fetchAllEditions()
            .then(
            function(d) {
                self.editions = d;
                console.info('EditionController : Fetching ' + d.length + ' Editions .... OK');
            },
            function(errResponse){
                console.error('EditionController : Error while fetching Edition');
            }
        );
    }
 
    /* crée une edition */
    function createEdition(edition){
        EditionService.createEdition(edition)
            .then(
            function() {
            	console.info('EditionController : Save New Edition...... OK', edition);
            	fetchAllEditions();
            },
            function(errResponse){
                console.error('EditionController : Error while creating Edition');
            }
        );
    }
 
    /* Update une edition */
    function updateEdition(edition, id){
        EditionService.updateEdition(edition, id)
            .then(
            function() {
            	console.info('EditionController : Update Edition with id ' + id + '...... OK', edition);
            	fetchAllEditions();
            },
            function(errResponse){
                console.error('EditionController : Error while updating Edition');
            }
        );
    }
 
    /* Supprime une edition */
    function deleteEdition(id){
        EditionService.deleteEdition(id)
            .then(
            function() {
            	console.info('EditionController : Delete Edition with id ' + id + '...... OK', edition);
            	fetchAllEditions();
            },
            function(errResponse){
                console.error('EditionController : Error while deleting Edition');
            }
        );
    }
    
    /* Soumission du form add/update */
    function submit() {
    	$('.popin.addEdition').removeClass('displayEdition');
        if (self.edition.id===null || typeof self.edition.id === "undefined") {
            console.info('EditionController : Saving New Edition', self.edition);
            createEdition(self.edition);
        } else {
            updateEdition(self.edition, self.edition.id);
            console.info('EditionController : Edition updated with id ', self.edition.id);
        }
        reset();
    }
 
    /* update an edition */
    function edit(id){
        console.info('EditionController : id to be edited', id);
        $scope.displayPopinAddClass = true;
        for(var i = 0; i < self.editions.length; i++){
            if(self.editions[i].id === id) {
                self.edition = angular.copy(self.editions[i]);
                break;
            }
        }
    }
    
    /* Remove an edition */
    function remove(id){
        console.info('EditionController : id to be deleted', id);
        if(typeof self.edition !== "undefined" && self.edition.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteEdition(id);
    }
 
    /* Reset form and obj */
    function reset(){
        self.edition = {
        	id : null,
        	name : ''
        };
       // $scope.myFormEdition.$setPristine(); //reset Form
    }    
}]);