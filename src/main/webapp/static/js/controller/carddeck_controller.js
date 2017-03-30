'use strict';
 
angular.module('myApp').controller('CarddeckController', ['$scope', 'CarddeckService', 'DeckService', '$sce', function($scope, CarddeckService, DeckService, $sce) {
    var self = this;
    self.carddeck = {
    					id : null,
    					card : '',
    					deck : '', 
    					quantity : ''
    			    };
    self.carddecks = [];
    
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
    				console.info('CarddeckController : Fetching ' + d.length + ' cards in decks ' + deckId + ' ..... OK');
    			},
    			function(errResponse) {
    				console.error('Error while fetching Card with deckId ' + deckId);
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
    				console.error('Error while fetching CardDeck with cardId '+cardId);
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
 
    function submit(deckId, cardId, quantity) {
    	console.log('SUBMIT CARDDECK' + deckId + "-"+cardId + "-"+quantity);
    	
    	 DeckService.findDeckById(deckId)
         .then(
         function(d) {
             
             self.carddeck.deck = d;
         	self.carddeck.card = cardId;
         	self.carddeck.quantity = quantity;
         	if(self.carddeck.id===null || typeof self.carddeck.id === "undefined"){
                 console.log('Saving New Card in Deck', self.carddeck);
                 createCarddeck(self.carddeck);
             }else{
                 //updateEdition(self.edition, self.edition.id);
                 //console.log('Edition updated with id ', self.edition.id);
             }
         },
         function(errResponse){
             console.error('Error while fetching Carddeck');
         }
     );
    	
    
    	
    	
        //reset();
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