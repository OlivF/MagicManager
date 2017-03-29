'use strict';
 
angular.module('myApp').controller('CarddeckController', ['$scope', 'CarddeckService', '$sce', function($scope, CarddeckService, $sce) {
    var self = this;
    self.cardeck={id:null,cardId:'',deckId:'', quantity:''};
    self.carddecks= [];
    console.log('INIT');
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
 
    //fetchAllCarddecks();
    //getDeckByCardId(1);
    function getCardByDeckId(deckId) {
    	CarddeckService.getCardByDeckId(deckId)
    		.then(
    			function(d) {
    				self.carddecks = d;
    			},
    			function(errResponse) {
    				console.error('Error while fetching CardDeck with deckId '+deckId);
    			}
    		);
    }
    
    $scope.getDeckByCardId = function (cardId) {
    	CarddeckService.getDeckByCardId(cardId)
    		.then(
    			function(d) {
    				self.carddecks = d;
    			},
    			function(errResponse) {
    				console.error('Error while fetching CardDeck with cardId '+id);
    			}
    		);
    }
    
    
    
    function fetchAllCarddecks(){
        CarddeckService.fetchAllCarddecks()
            .then(
            function(d) {
                self.carddecks = d;
            },
            function(errResponse){
                console.error('Error while fetching Carddeck');
            }
        );
    }
 
    function createCarddeck(carddeck){
        CarddeckService.createCarddeck(carddeck)
            .then(
            fetchAllCarddecks,
            function(errResponse){
                console.error('Error while creating Carddeck');
            }
        );
    }
 
    function updateCarddeck(carddeck, id){
        CarddeckService.updateCarddeck(carddeck, id)
            .then(
            fetchAllCarddecks,
            function(errResponse){
                console.error('Error while updating Edition');
            }
        );
    }
 
    function deleteCarddeck(id){
        CarddeckService.deleteCarddeck(id)
            .then(
            fetchAllEditions,
            function(errResponse){
                console.error('Error while deleting Edition');
            }
        );
    }
 
    function submit() {
    	/*$('.popin.addEdition').removeClass('displayEdition');
        if(self.edition.id===null || typeof self.edition.id === "undefined"){
            console.log('Saving New Edition', self.edition);
            createEdition(self.edition);
        }else{
            updateEdition(self.edition, self.edition.id);
            console.log('Edition updated with id ', self.edition.id);
        }
        reset();*/
    }
 
    function edit(id){
       /* console.log('id to be edited', id);
        $scope.displayPopinAddClass=true;
        for(var i = 0; i < self.editions.length; i++){
            if(self.editions[i].id === id) {
                self.edition = angular.copy(self.editions[i]);
                break;
            }
        }*/
    }
 
    function remove(id){
        /*console.log('id to be deleted', id);
        if(typeof self.edition !== "undefined" && self.edition.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteEdition(id);*/
    }
 
 
    function reset(){
       // self.edition={id:null,name:''};
       // $scope.myFormEdition.$setPristine(); //reset Form
    }    
}]);