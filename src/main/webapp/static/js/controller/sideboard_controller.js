'use strict';
 
angular.module('myApp').controller('SideboardController', ['$scope', 'SideboardService', 'CardService', 'DeckService', '$sce', function($scope, SideboardService, CardService, DeckService, $sce) {
    var self = this;
    self.sideboard = {
    	id : null,
    	card : '',
    	deck : '', 
    	quantity : ''
    };
    self.sideboards = [];
    
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
    
     
    fetchAllSideboards();
    //getDeckByCardId(1);
    function getCardByDeckId(deckId) {
    	SideboardService.getCardByDeckId(deckId)
    		.then(
    			function(d) {
    				self.sideboards = d;
    				console.info('SideboardController : Fetching ' + d.length + ' cards in deckId ' + deckId + ' ..... OK');
    			},
    			function(errResponse) {
    				console.error('Error while fetching Card with deckId ' + deckId);
    			}
    		);
    }
    
    $scope.getDeckByCardId = function (cardId) {
    	SideboardService.getDeckByCardId(cardId)
    		.then(
    			function(d) {
    				self.sideboards = d;
    			},
    			function(errResponse) {
    				console.error('Error while fetching CardDeck with cardId '+cardId);
    			}
    		);
    }
    
    
    
    function fetchAllSideboards(){
        SideboardService.fetchAllSideboards()
            .then(
            function(d) {
                self.sideboards = d;
                console.info('SideboardController : Fetching All cardsdeck..... OK');
            },
            function(errResponse){
                console.error('Error while fetching Sideboard');
            }
        );
    }
 
    function createSideboard(sideboard){
        SideboardService.createSideboard(sideboard)
            .then(
            function() {
            	window.location.reload();
            	//getCardByDeckId(sideboard.deck.id);
            },            
            function(errResponse){
                console.error('Error while creating Sideboard');
            }
        );
    }
 
    function updateSideboard(sideboard, id){
        SideboardService.updateSideboard(sideboard, id)
            .then(
            fetchAllSideboards,
            function(errResponse){
                console.error('Error while updating Edition');
            }
        );
    }
 
    function deleteSideboard(id){
    	SideboardService.deleteSideboard(id)
            .then(
            fetchAllSideboards,
            function(errResponse){
                console.error('Error while deleting Edition');
            }
        );
    }
 
    function submit(deckId, card) {
    	
    	var quantity = $('.quantity' + card.id).val();
    	
    	DeckService.findDeckById(deckId)
         .then(
         function(d) {
        	 
        	 card.nbDispo = parseInt(card.nbDispo) -parseInt(quantity);
        	 
        	 CardService.updateCard(card, card.id)
             .then(
             function() {
            	self.sideboard.deck = d;
              	self.sideboard.card = card;
              	self.sideboard.quantity = quantity;
             
              	if ( self.sideboard.id === null || typeof self.sideboard.id === "undefined") {
                      console.log('Saving New Card in Deck', self.sideboard);
                      createSideboard(self.sideboard);
                 } else {
                      //updateEdition(self.edition, self.edition.id);
                      //console.log('Edition updated with id ', self.edition.id);
                 }
             },
             function(errResponse){
                 console.error('CardController : Error while updating Card from CardService');
             }
         );
            
         },
         function(errResponse){
             console.error('Error while fetching Sideboard');
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