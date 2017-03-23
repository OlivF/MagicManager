'use strict';
 
angular.module('myApp').controller('CardController', ['$scope', 'CardService', '$sce', function($scope, CardService, $sce) {
    var self = this;
    self.card={id:null,nameFr:'',nameEn:'',type:'', edition:"", manaCost:"", rarity:"", price:""};
    self.cards=[];
 
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
    
    self.arrayManaCost = [];
    self.manaCostStr = "";
    
    $scope.sortType     = 'nameFr'; // set the default sort type
    $scope.sortReverse  = false;  // set the default sort order
    $scope.searchFish   = '';     // set the default search/filter term
    
    fetchAllCards();
    
    function fetchAllCards(){
        CardService.fetchAllCards()
            .then(
            function(d) {
                self.cards = d;
            },
            function(errResponse){
                console.error('Error while fetching Card');
            }
        );
    }
 
    function createCard(card){
        CardService.createCard(card)
            .then(
            fetchAllCards,
            function(errResponse){
                console.error('Error while creating Card');
            }
        );
    }
 
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
    }
 
    function submit() {
    	$('.popin.addCard').removeClass('displayCard');
    	
    	self.card.manaCost = self.manaCostStr;
    	if(self.card.id===null || typeof self.card.id === "undefined"){
            console.log('Saving New Card', self.card);
            createCard(self.card);
            $('.banniereAdd').addClass('displayBan');
        	setTimeout(function(){
        		$('.banniereAdd').removeClass('displayBan');	
        	}, 3000);
        }else{
            updateCard(self.card, self.card.id);
            console.log('Card updated with id ', self.card.id);
            $('.banniereUpdate').addClass('displayBan');
        	setTimeout(function(){
        		$('.banniereUpdate').removeClass('displayBan');	
        	}, 3000);
        }
        reset();
    }
 
    function edit(id){
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
 
 
    function reset(){
        self.card={id:null,nameFr:'',nameEn:'',type:'',edition:'', manaCost:"", rarity:"", price:""};
        self.manaCostStr="";
        //$scope.myForm.$setPristine(); //reset Form
    }
    
    $scope.addManaCost = function ( url , str) {
    	console.log('ADD MANACOST');
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
    	console.log("here");
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
    	
    	console.log(value);
    	return value;
    }
    
    $scope.getManaCostHtml = function ( manaCost, url ) {
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
    
    
    
}]);