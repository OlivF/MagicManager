'use strict';
 
angular.module('myApp').controller('MainController', ['$scope', function($scope) {
	
	var self = this;
	
	self.bodyClassOverflow = false;
	self.displayCard = false;
	self.displayEdition = false;
	self.displayType = false;
	self.displayRarity = false;
	self.displayDeck = false;
	
	self.displayListForDeck = false;
    
	self.openPopinCard = 0;
	
	$scope.displayListForDeckFct = function() {
		self.displayListForDeck = true;
	}
	
    $scope.displayCardFct = function() {
    	self.bodyClassOverflow = true;
    	self.displayCard = true;
    	self.displayEdition = false;
    	self.displayType = false;
    	self.displayRarity = false;
    	self.displayDeck = false;
    	
    	self.openPopinCard = 1;
    }
    
    $scope.displayEditionFct = function() {
    	self.bodyClassOverflow = true;
    	self.displayEdition = true;
    	self.displayCard = false;
    	self.displayType = false;
    	self.displayRarity = false;
    	self.displayDeck = false;
    	
    	if(self.openPopinCard === 1) {
    		self.openPopinCard = 2;
    	}
    }
    
    $scope.displayTypeFct = function() {
    	self.bodyClassOverflow = true;
    	self.displayType = true;
    	self.displayCard = false;
    	self.displayEdition = false;
    	self.displayRarity = false;
    	self.displayDeck = false;
    	
    	if(self.openPopinCard === 1) {
    		self.openPopinCard = 2;
    	}
    }
    
    $scope.displayRarityFct = function() {
    	self.bodyClassOverflow = true;
    	self.displayRarity = true;
    	self.displayCard = false;
    	self.displayEdition = false;
    	self.displayType = false;
    	self.displayDeck = false;
    	
    	if(self.openPopinCard === 1) {
    		self.openPopinCard = 2;
    	}
    }
    
    $scope.displayDeckFct = function() {
    	self.bodyClassOverflow = true;
    	self.displayRarity = false;
    	self.displayCard = false;
    	self.displayEdition = false;
    	self.displayType = false;
    	self.displayDeck = true;
    	
    	if(self.openPopinCard === 1) {
    		self.openPopinCard = 2;
    	}
    }
    
    $scope.closePopin = function() {
    	
    	
    	self.displayEdition = false;
    	self.displayType = false;
    	self.displayRarity = false;
    	self.displayDeck = false;
    	
    	if(self.openPopinCard === 0) {
    		self.bodyClassOverflow = false;
    	}
    	if(self.openPopinCard === 1) {
    		self.openPopinCard = 0;
    		self.displayCard = false;
    		self.bodyClassOverflow = false;
    	}
    	
    	if(self.openPopinCard === 2) {
    		self.openPopinCard = 1;
    		self.displayCard = true;
    		self.bodyClassOverflow = true;
    	}
    	
    	
    }
}]);