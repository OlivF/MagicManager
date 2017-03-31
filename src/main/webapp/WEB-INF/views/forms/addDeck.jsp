<div class="popin addDeck" ng-class="{'displayDeck': (mainCtrl.displayDeck)}">
	<div class="closeBtn" ng-click="closePopin();"><i class="fa fa-times fa-3x" aria-hidden="true"></i></div>
	<div class="generic-container">
      	<div class="panel panel-default">
          	<div class="panel-heading"><span class="lead">Créer un deck</span></div>
          	<div class="formcontainer">
           		<form ng-submit="ctrlD.submit()" name="myFormDeck" class="form-horizontal">
            		<input type="hidden" ng-model="ctrlD.deck.id" />
               		<div class="row">
                   		<div class="form-group col-md-12">
                       		<label class="col-md-2 control-lable" for="nameDeck">Nom</label>
                       		<div class="col-md-7">
                         		<input type="text" ng-model="ctrlD.deck.name" id="nameDeck" class="nameDeck form-control input-sm" placeholder="Choisissez un nom" required ng-minlength="3"/>
                          		<div class="has-error" ng-show="myFormDeck.$dirty">
                           			<span ng-show="myFormDeck.nameDeck.$error.required">Ce champ est obligatoire</span>
                           			<span ng-show="myFormDeck.nameDeck.$error.minlength">La longueur minimum est 3</span>
                           			<span ng-show="myFormDeck.nameDeck.$invalid">Ce champ est invalide</span>
                          		</div>
                       		</div>
                   		</div>
               		</div>
               		<div class="row">
               			<div class="form-group col-md-6">
               				<div class="input-group">
						   		 <span class="input-group-addon">Couleur</span>
						   		 <div class="form-control result" ng-bind-html='addColorHtml("<c:url value='/static/img/'/>");'></div><i ng-click="emptyColor();"  class="emptyField fa fa-times fa-3x" aria-hidden="true"></i>
                    			<input type="hidden" class="color form-control" id="color" name="color" ng-model="ctrlD.deck.color" required ng-value='getColor();' />
                    		</div>
                      		<div class="has-error" ng-show="myFormDeck.$dirty">
                          		<span ng-show="myFormDeck.color.$error.required">This is a required field</span>
                           	</div>
               			</div>
               			<div class="form-group col-md-6">
                    		<div class="manaCostSymbol">
                    		 <img ng-click="addColor('1.gif', '1');" src="<c:url value='/static/img/1.gif'/>" />
	                    		<img ng-click="addColor('2.gif', '2');" src="<c:url value='/static/img/2.gif'/>" />
	                    		<img ng-click="addColor('3.gif', '3');" src="<c:url value='/static/img/3.gif'/>" />
	                    		<img ng-click="addColor('4.gif', '4');" src="<c:url value='/static/img/4.gif'/>" />
	                    		<img ng-click="addColor('5.gif', '5');" src="<c:url value='/static/img/5.gif'/>" />
	                    		<img ng-click="addColor('6.gif', '6');" src="<c:url value='/static/img/6.gif'/>" />
	                    		<img ng-click="addColor('7.gif', '7');" src="<c:url value='/static/img/7.gif'/>" />
	                    		<img ng-click="addColor('8.gif', '8');" src="<c:url value='/static/img/8.gif'/>" />
	                    		<img ng-click="addColor('9.gif', '9');" src="<c:url value='/static/img/9.gif'/>" />
	                    		<img ng-click="addColor('B.gif', 'B');" src="<c:url value='/static/img/B.gif'/>" />
	                    		<img ng-click="addColor('G.gif', 'G');" src="<c:url value='/static/img/G.gif'/>" />
	                    		<img ng-click="addColor('R.gif', 'R');" src="<c:url value='/static/img/R.gif'/>" />
	                    		<img ng-click="addColor('U.gif', 'U');" src="<c:url value='/static/img/U.gif'/>" />
	                    		<img ng-click="addColor('W.gif', 'W');" src="<c:url value='/static/img/W.gif'/>" />
                    			<img ng-click="addColor('L.gif', 'L');" src="<c:url value='/static/img/L.gif'/>" />
                    			
                    	
                    		</div>
                    	</div>
               		</div>
               		<div class="row">
                   		<div class="form-actions floatRight">
                       		<input type="submit"  ng-click="closePopin();" value="{{!ctrlD.deck.id ? 'Ajouter' : 'Mettre à jour'}}" class="btn btn-primary btn-sm" ng-disabled="myFormDeck.$invalid">
                       		<button type="button" ng-click="ctrlD.reset()" class="btn btn-warning btn-sm" ng-disabled="myFormDeck.$pristine">Reset Form</button>
                   		</div>
               		</div>
           		</form>
       		</div>
   		</div>
	</div>
</div>