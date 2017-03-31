<div class="popin addCard" ng-class="{'displayCard': (mainCtrl.displayCard)}">
	<div class="closeBtn" ng-click="closePopin();"><i class="fa fa-times fa-3x" aria-hidden="true"></i></div>
	<div class="generic-container">
      	<div class="panel panel-default">
      		
          	<div class="panel-heading"><span class="lead">Add a Card in your Collection</span></div>
          	<div class="formcontainer">
           		<form ng-submit="ctrl.submit()" id="myForm" name="myForm" class="">
            		<input type="hidden" ng-model="ctrl.card.id" />
            		<input type="hidden" ng-model="ctrl.card.nbDispo" ng-value="ctrl.card.nbDispo" />
            		<input type="hidden" class="nbItemOld{{ctrl.card.id}}" value="{{ctrl.card.nbItemOld}}" />
               		<div class="row">
               		
                   		<div class="form-group col-md-6">
                   			<div class="input-group">
							    <span class="input-group-addon">Name Fr</span>
							    <input type="text" class="form-control nameFr" ng-model="ctrl.card.nameFr" id="nameFr" name="nameFr" placeholder="Enter name fr" required ng-minlength="3">
							</div>
							<div class="has-error" ng-show="myForm.$dirty">
                           		<span ng-show="myForm.nameFr.$error.$invalid">This is a required field</span>
                           		<span ng-show="myForm.nameFr.$error.minlength">Minimum length required is 3</span>
                           	</div>        		
                   		</div>
                   		
                   		<div class="form-group col-md-6">
                   			<div class="input-group">
							    <span class="input-group-addon">Type</span>
							    <div class="dropdown dropdown-scroll dropdownType">
								    <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown">
								    	<span ng-if="!!ctrl.card.type" class="typeChoose">{{ctrl.card.type.name}}</span>
								    	<span ng-if="!ctrl.card.type" class="typeChoose" ng-model="ctrl.card.type">Choose a Type</span> 
								    	<i class="fa fa-caret-down fa-2x" aria-hidden="true"></i>
								    </button>
								    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
								        <li role="presentation">
								            <div class="input-group input-group-sm search-control"> 
								            	<span class="input-group-addon">
								                    <span class="glyphicon glyphicon-search"></span>
												</span>
								                <input type="text" class="form-control" placeholder="Query" ng-model="queryT"></input>
								            </div>
								        </li>
								        <li ng-click="ctrl.card.type=t" role="presentation" ng-repeat='t in ctrlT.types | filter:queryT'> <a href="#"> <span class="typeSelector">{{t.name}}</span> </a>
								        </li>
								    </ul>
								</div>
							</div>
							<div class="has-error" ng-show="myForm.$dirty">
                           			<span ng-show="myForm.type.$error.required">This is a required field</span>
                           			<span ng-show="myForm.type.$error.minlength">Minimum length required is 3</span>
                           			<span ng-show="myForm.type.$invalid">This field is invalid </span>
                       			</div>
                   		</div>
                   	</div>
                   	
 					<div class="row">                  	
                   		<div class="form-group col-md-6">
                   			 <div class="input-group">
							    <span class="input-group-addon">Name En</span>
							    <input type="text" class="form-control nameEn" ng-model="ctrl.card.nameEn" id="nameEn" name="nameEn" placeholder="Enter name en" required ng-minlength="3">
							 </div>
							 <div class="has-error" ng-show="myForm.$dirty">
                           		<span ng-show="myForm.nameEn.$error.$invalid">This is a required field</span>
                           		<span ng-show="myForm.nameEn.$error.minlength">Minimum length required is 3</span>
                           	</div>      
                  		</div>
                  		
                  		<div class="form-group col-md-6">
                  			<div class="input-group">
							    <span class="input-group-addon">Edition</span>
							    <div class="dropdown dropdown-scroll dropdownEdition">
								    <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown"><span ng-if="!!ctrl.card.edition" class="editionChoose">{{ctrl.card.edition.name}}</span><span ng-if="!ctrl.card.edition" class="editionChoose">Choose an Edition</span> <i class="fa fa-caret-down fa-2x" aria-hidden="true"></i>
								    </button>
								    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
								        <li role="presentation">
								            <div class="input-group input-group-sm search-control"> <span class="input-group-addon">
								                    <span class="glyphicon glyphicon-search"></span>
								</span>
								                <input type="text" class="form-control" placeholder="Query" ng-model="query"></input>
								            </div>
								        </li>
								        <li ng-click="ctrl.card.edition=e"  role="presentation" ng-repeat='e in ctrlE.editions | filter:query'> <a href="#"> <span class="editionSelector">{{e.name}}</span> </a>
								        </li>
								    </ul>
								</div>
							 </div>
							 <div class="has-error" ng-show="myForm.$dirty">
                    	           <span ng-show="myForm.edition.$error.required">This is a required field</span>
                        		</div>
                  		</div>
               		</div>
            		
            		<div class="row">
                   		<div class="form-group col-md-6">
                   		  	<div class="input-group">
						   		 <span class="input-group-addon">Mana Cost</span>
						   		 <div class="form-control result" ng-bind-html='addManaCostHtml("<c:url value='/static/img/'/>");'></div><i ng-click="emptyManaCost();"  class="emptyField fa fa-times fa-3x" aria-hidden="true"></i>
                    			<input type="hidden" class="manaCost form-control" id="manaCost" name="manaCost" ng-model="ctrl.card.manaCost" required ng-value='getManaCost();' />
                    		</div>
                      		<div class="has-error" ng-show="myForm.$dirty">
                          		<span ng-show="myForm.manaCost.$error.required">This is a required field</span>
                           	</div>
                    	</div>
                    	<div class="form-group col-md-6">
                    		<div class="manaCostSymbol">
                    		 <img ng-click="addManaCost('1.gif', '1');" src="<c:url value='/static/img/1.gif'/>" />
	                    		<img ng-click="addManaCost('2.gif', '2');" src="<c:url value='/static/img/2.gif'/>" />
	                    		<img ng-click="addManaCost('3.gif', '3');" src="<c:url value='/static/img/3.gif'/>" />
	                    		<img ng-click="addManaCost('4.gif', '4');" src="<c:url value='/static/img/4.gif'/>" />
	                    		<img ng-click="addManaCost('5.gif', '5');" src="<c:url value='/static/img/5.gif'/>" />
	                    		<img ng-click="addManaCost('6.gif', '6');" src="<c:url value='/static/img/6.gif'/>" />
	                    		<img ng-click="addManaCost('7.gif', '7');" src="<c:url value='/static/img/7.gif'/>" />
	                    		<img ng-click="addManaCost('8.gif', '8');" src="<c:url value='/static/img/8.gif'/>" />
	                    		<img ng-click="addManaCost('9.gif', '9');" src="<c:url value='/static/img/9.gif'/>" />
	                    		<img ng-click="addManaCost('B.gif', 'B');" src="<c:url value='/static/img/B.gif'/>" />
	                    		<img ng-click="addManaCost('G.gif', 'G');" src="<c:url value='/static/img/G.gif'/>" />
	                    		<img ng-click="addManaCost('R.gif', 'R');" src="<c:url value='/static/img/R.gif'/>" />
	                    		<img ng-click="addManaCost('U.gif', 'U');" src="<c:url value='/static/img/U.gif'/>" />
	                    		<img ng-click="addManaCost('W.gif', 'W');" src="<c:url value='/static/img/W.gif'/>" />
                    			<img ng-click="addManaCost('L.gif', 'L');" src="<c:url value='/static/img/L.gif'/>" />
                    			
                    	
                    		</div>
                    		
                    	</div>
               		</div>
               
               		<div class="row">
                   		<div class="form-group col-md-6">
                    		 <div class="input-group">
							    <span class="input-group-addon">Rarity</span>
							    <label class="radio-inline" ng-repeat="r in ctrlR.raritys">
								  <input type="radio" ng-if="!!ctrl.card.rarity" checked="checked" ng-click="ctrl.card.rarity=r" id="rarity" name="rarity" value="{{r}}" required> 
								  <input type="radio" ng-if="!ctrl.card.rarity" ng-click="ctrl.card.rarity=r" id="rarity" name="rarity" value="{{r}}" required> <img ng-src="<c:url value='/static/img/{{r.name}}.gif'/>">
								</label>
							 </div>
                    	</div>
                    	
                    	<div class="form-group col-md-3">
                    		
                    			<div class="input-group">
							   		 <span class="input-group-addon">Price</span>
							   		 <input type="number" ng-model="ctrl.card.price" id="price" class="price form-control input-sm" placeholder="Enter price" step="any" required />
							 	</div>
							 	<div class="has-error" ng-show="myForm.$dirty">
                           			<span ng-show="myForm.price.$error.required">This is a required field</span>
                           			<span ng-show="myForm.price.$invalid">This field is invalid </span>
                        		</div>
                    	</div>
                    	<div class="form-group col-md-3">
                   			<div class="input-group">
						   		 <span class="input-group-addon">Quantity</span>
						   		 <input type="number" ng-model="ctrl.card.nbItem" id="nbItem" class="nbItem form-control input-sm" placeholder="Enter price" step="1" required />
						 	</div>
						 	<div class="has-error" ng-show="myForm.$dirty">
                          			<span ng-show="myForm.price.$error.required">This is a required field</span>
                          			<span ng-show="myForm.price.$invalid">This field is invalid </span>
                       		</div>
                    	</div>
               		</div>
                  <div class="row">
                 	 <div class="col-md-6 form-actions">
                   		<button type="button" ng-click="displayTypeFct();" class="btn btn-primary btn-sm">Add Type</button>
                   		<button type="button" ng-click="displayEditionFct();" class="btn btn-primary btn-sm">Add Edition</button>
                   		<button type="button" ng-click="displayRarityFct();" class="btn btn-primary btn-sm">Add Rarity</button>
                   	</div>
                   	<div class="col-md-6 form-actions">
                   		<input type="submit"  ng-click="closePopin();" value="{{!ctrl.card.id ? 'Add' : 'Update'}}" class="btn btn-success btn-sm" ng-disabled="myForm.$invalid">
                   		<button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                   	</div>
               	</div>
               		</form>
           		
       		</div>
       		
       			
       		
       		
   		</div>
	</div>
</div>