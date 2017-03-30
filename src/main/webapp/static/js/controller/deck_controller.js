'use strict';
 
angular.module('myApp').controller('DeckController', ['$scope', 'DeckService', 'TypeService', '$sce', function($scope, DeckService, TypeService, $sce) {
    var self = this;
    self.deck={id:null,name:'',color:''};
    self.decks=[];
    
    self.submit = submit;
    self.reset = reset;
    self.arrayColor = [];
    self.colorStr = "";
    $scope.sortType     = 'name'; // set the default sort type
    $scope.sortReverse  = false;  // set the default sort order
    
    fetchAllDecks();
    
    function fetchAllDecks(){
        DeckService.fetchAllDecks()
            .then(
            function(d) {
                self.decks = d;
                //updateCardType();
            },
            function(errResponse){
                console.error('Error while fetching Decks');
            }
        );
    }
 
    
    function redirect() {
    	console.log("redirect");
    	document.location = "/MagicManagerSpringWebMVC/deckList/";
    }
   /* function updateCardType() {
    	
    	//console.log(self.cards[0])
    	/*for(var i=0; i<self.cards.length;i++); {
    		self.card[i].type = "blabla";
    		
    	}*/
    	
    /*}
    
    */
    
    function createDeck(deck){
        DeckService.createDeck(deck)
            .then(
            fetchAllDecks,
            function(errResponse){
                console.error('Error while creating Deck');
            }
        );
    }
    
 /*
    function updateCard(card, id){
        CardService.updateCard(card, id)
            .then(
            fetchAllCards,
            function(errResponse){
                console.error('Error while updating Card');
            }
        );
    }
 
    function deleteCard(id){
        CardService.deleteCard(id)
            .then(
            fetchAllCards,
            function(errResponse){
                console.error('Error while deleting Card');
            }
        );
    }*/
    
    $scope.addColor = function ( url , str) {
    	console.log('ADD COLOR');
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
    	console.log("here");
    	self.deck.color = "";
    	self.colorStr = "";
    }
    
    $scope.getColor = function () {
    	console.log("GET COLOR");
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
    	
    	//console.log(value);
    	return value;
    }
    
    
 
    function submit() {
    	$('.popin.addDeck').removeClass('displayDeck');
    	
    	self.deck.color = self.colorStr;
    	if(self.deck.id===null || typeof self.deck.id === "undefined"){
            console.log('Saving New Deck', self.deck);
            createDeck(self.deck);
            redirect();
            //$('.banniereAdd').addClass('displayBan');
        	//setTimeout(function(){
        		//$('.banniereAdd').removeClass('displayBan');	
        	//}, 3000);
        }else{
            updateDeck(self.deck, self.deck.id);
            console.log('Deck updated with id ', self.deck.id);
            //$('.banniereUpdate').addClass('displayBan');
        	//setTimeout(function(){
        		//$('.banniereUpdate').removeClass('displayBan');	
        	//}, 3000);
        }
        //reset();
    }
 
 /*   function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.cards.length; i++){
            if(self.cards[i].id === id) {
                self.card = angular.copy(self.cards[i]);
                break;
            }
        }
    }
 
    function remove(id){
        console.log('id to be deleted', id);
        if(typeof self.card !== "undefined" && self.card.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteCard(id);
    }
 */
 
    function reset(){
        //self.deck={id:null,name:'',color:''};
        //self.colorStr="";
        //$scope.myForm.$setPristine(); //reset Form
    }
   /* 
    $scope.seeCardInfo = function() {
    	console.log("CLICK ON CARD");
    };
    
    
    $scope.addManaCost = function ( url , str) {
    	//console.log('ADD MANACOST');
    	self.arrayManaCost.push(url);
    	self.manaCostStr = self.manaCostStr + str;
    	self.card.manaCost = self.manaCostStr;
    }
    
    $scope.addManaCostHtml = function ( url ) {
    
    	var html = '';
    	var array = [];
    	var manaCost;
    	if(self.card.manaCost != "") {
    		manaCost = self.card.manaCost;
    	} else {
    	 manaCost = self.manaCostStr;
    	}
    	
    	for(var j=0; j<manaCost.length; j++) {
    		array.push(manaCost.substring(j,j+1));
    	}
    	
    	
    	for ( var i=0; i< array.length; i++) {
    		html += '<img src="'+url+array[i]+'.gif" />';
    	}
    	
    	
    	return $sce.trustAsHtml(html);
    	
    }
    
    $scope.emptyManaCost = function () {
    	//console.log("here");
    	self.card.manaCost = "";
    	self.manaCostStr = "";
    }
    
    $scope.getManaCost = function () {
    	var value = '';
    	var array = [];
    	var manaCost;
    	if(self.card.manaCost != "") {
    		manaCost = self.card.manaCost;
    		self.manaCostStr = manaCost;
    	} else {
    	 manaCost = self.manaCostStr;
    	}
    	for(var j=0; j<manaCost.length; j++) {
    		array.push(manaCost.substring(j,j+1));
    	}
    	
    	
    	for ( var i=0; i< array.length; i++) {
    		value += array[i];
    	}
    	
    	//console.log(value);
    	return value;
    }
    
    $scope.getManaCostHtml = function ( manaCost, url ) {
    	//console.log("GET MANA COST HTML" + manaCost);
    	var html = '';
    	var array = [];
    	for(var j=0; j<manaCost.length; j++) {
    		array.push(manaCost.substring(j,j+1));
    	}
    	
    	
    	for ( var i=0; i< array.length; i++) {
    		html += '<img src="'+url+array[i]+'.gif" />';
    	}
    	
    	
    	return $sce.trustAsHtml(html);
    	
    }
    
    function getType ( type_id ) {
    	//console.log("GET TYPE "+type_id)
    	var type;
    	/*TypeService.getTypeById( type_id ).then(
                function(d) {
                    type = d;
                    return $sce.trustAsHtml(type.name);
                },
                function(errResponse){
                    console.error('Error while fetching Type by Id');
                }
               );*/
    	//console.log(type);
    	/*return $sce.trustAsHtml("ok");
    }*/
    
}]);