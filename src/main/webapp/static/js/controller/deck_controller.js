'use strict';
 
angular.module('myApp').controller('DeckController', ['$scope', 'DeckService', '$sce', function($scope, DeckService, $sce) {
    var self = this;
    self.deck = {
    				id : null,
    				name : '',
    				color : ''
    			};
    
    self.decks=[];
    
    self.arrayColor = [];
    self.colorStr = "";
    
    $scope.sortType     = 'name'; // set the default sort type
    $scope.sortReverse  = false;  // set the default sort order
    
    self.submit = submit;
    self.edit = edit;
    self.reset = reset;
    
    fetchAllDecks();
    
    function fetchAllDecks(){
        DeckService.fetchAllDecks()
            .then(
            function(d) {
                self.decks = d;
                console.info('DeckController : Fetching ' + d.length + ' Decks..... OK');
            },
            function(errResponse){
                console.error('DeckController : Error while fetching Decks');
            }
        );
    }
    
    /* Modifie une carte */
    function updateDeck(deck, id){
    	DeckService.updateDeck(deck, id)
            .then(
            function() {
            	fetchAllDecks();
            },
            function(errResponse){
                console.error('DeckController : Error while updating Deck from DeckService');
            }
        );
    }
 
    function redirect() {
    	document.location = "/MagicManagerSpringWebMVC/deckList/";
    }
    
    function createDeck(deck){
        DeckService.createDeck(deck)
            .then(
            fetchAllDecks,
            function(errResponse){
                console.error('Error while creating Deck');
            }
        );
    }
    
    $scope.addColor = function ( url , str) {
    	self.arrayColor.push(url);
    	self.colorStr = self.colorStr + str;
    	self.deck.color = self.colorStr;
    }
    
    $scope.addColorHtml = function ( url ) {
    
    	var html = '';
    	var array = [];
    	var color;
    	if(self.deck.color != "") {
    		color = self.deck.color;
    	} else {
    	 color = self.colorStr;
    	}
    	for(var j=0; j<color.length; j++) {
    		array.push(color.substring(j,j+1));
    	}
    	for ( var i=0; i< array.length; i++) {
    		html += '<img src="'+url+array[i]+'.gif" />';
    	}
    	return $sce.trustAsHtml(html);
    }
    
    $scope.emptyColor = function () {
    	self.deck.color = "";
    	self.colorStr = "";
    }
    
    $scope.getColor = function () {
    	var value = '';
    	var array = [];
    	var color;
    	if(self.deck.color != "") {
    		color = self.deck.color;
    		self.colorStr = color;
    	} else {
    	 color = self.colorStr;
    	}
    	for(var j=0; j<color.length; j++) {
    		array.push(color.substring(j,j+1));
    	}
    	for ( var i=0; i< array.length; i++) {
    		value += array[i];
    	}
    	return value;
    }
    
    function submit() {
    	$('.popin.addDeck').removeClass('displayDeck');
    	
    	self.deck.color = self.colorStr;
    	if(self.deck.id===null || typeof self.deck.id === "undefined"){
            console.info('DeckController : Saving New Deck', self.deck);
            createDeck(self.deck);
            redirect();
        } else {
            updateDeck(self.deck, self.deck.id);
            console.info('DeckController : Deck updated with id ', self.deck.id);
        }
        reset();
    }
 
    /* Mise à jour d'une carte */
    function edit(id){
        console.info('DeckController : id to be edited', id);
        for(var i = 0; i < self.decks.length; i++){
            if(self.decks[i].id === id) {
                self.deck = angular.copy(self.decks[i]);
                break;
            }
        }
    }
    
    function reset(){
        self.deck={id:null,name:'',color:''};
        self.colorStr="";
        //$scope.myForm.$setPristine(); //reset Form
    }
}]);