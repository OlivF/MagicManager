'use strict';
 
angular.module('myApp').controller('CardController', ['$scope', 'CardService', '$sce', function($scope, CardService, $sce) {
    var self = this;
    
    /* Structure d'une carte */
    self.card = {
    				id : null,
    				nameFr : '',
    				nameEn : '',
    				type : '', 
    				edition : "",
    				manaCost : "", 
    				rarity: "", 
    				price : ""
    			};
    
    /* Le tableau de carte */
    self.cards = [];
 
    /* Les variables pour la gestion du cout de mana de la carte */
    self.arrayManaCost = [];
    self.manaCostStr = "";
    
    /* Les variables pour le filtre */
    $scope.sortType     = 'nameFr'; // set the default sort type
    $scope.sortReverse  = false;  // set the default sort order
    
    /* Les fonctions du controller */
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
    
    /* A l'initialisation du controleur on récupère tous les cartes dans le tableau de cartes */
    fetchAllCards();
    
    /* Met dans le tableau de carte toutes les cartes */
    function fetchAllCards() {
        CardService.fetchAllCards()
            .then(
            function(d) {
                self.cards = d;
                console.info('CardController : Fetching All Card From DB...... OK');
            },
            function(errResponse){
                console.error('CardController : Error while fetching Card from CardService');
            }
        );
    }
 
    /* Ajout d'une carte */
    function createCard(card){
        CardService.createCard(card)
            .then(
            function() {
            	console.info('CardController : Add Card..... OK');
            	console.info(card);
            	fetchAllCards();
            },
            function(errResponse){
                console.error('CardController : Error while creating Card from CardService');
            }
        );
    }
 
    /* Modifie une carte */
    function updateCard(card, id){
        CardService.updateCard(card, id)
            .then(
            function() {
            	console.info('CardController : Update Card...... Ok');
            	console.info(card);
            	fetchAllCards();
            },
            function(errResponse){
                console.error('CardController : Error while updating Card from CardService');
            }
        );
    }
 
    /* Supprime une carte */
    function deleteCard(id){
        CardService.deleteCard(id)
            .then(
            function() {
            	console.info('CardController : Delete Card with id ' + id + ' ..... OK');
            	fetchAllCards();
            },
            function(errResponse){
                console.error('CardController : Error while deleting Card From CardService');
            }
        );
    }
 
    /* Soumission du formulaire d'ajout/update  de carte*/
    function submit() {
    	$('.popin.addCard').removeClass('displayCard');
    	self.card.manaCost = self.manaCostStr;
    	if (self.card.id === null || typeof self.card.id === "undefined"){
            console.info('CardController : Saving New Card', self.card);
            createCard(self.card);
            $('.banniereAdd').addClass('displayBan');
        	setTimeout(function(){
        		$('.banniereAdd').removeClass('displayBan');	
        	}, 3000);
        } else {
            updateCard(self.card, self.card.id);
            console.info('CardController : Card updated with id ', self.card.id);
            $('.banniereUpdate').addClass('displayBan');
        	setTimeout(function(){
        		$('.banniereUpdate').removeClass('displayBan');	
        	}, 3000);
        }
        reset();
    }
 
    /* Mise à jour d'une carte */
    function edit(id){
        console.info('CardController : id to be edited', id);
        for(var i = 0; i < self.cards.length; i++){
            if(self.cards[i].id === id) {
                self.card = angular.copy(self.cards[i]);
                break;
            }
        }
    }
 
    /* Suppression d'une carte */
    function remove(id){
        console.info('CardController : id to be deleted', id);
        if(typeof self.card !== "undefined" && self.card.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteCard(id);
    }
 
    /* Reset form and object */
    function reset(){
        self.card = {
        				id : null,
        				nameFr : '',
        				nameEn : '',
        				type : '',
        				edition : '', 
        				manaCost : "", 
        				rarity : "", 
        				price : ""};
        self.manaCostStr="";
        //$scope.myForm.$setPristine(); //reset Form
    }
    
    $scope.addManaCost = function ( url , str) {
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