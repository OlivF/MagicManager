<div class="popin addEdition" ng-class="{'displayEdition': (mainCtrl.displayEdition)}">
	<div class="closeBtn" ng-click="ctrlE.reset();closePopin();"><i class="fa fa-times fa-3x" aria-hidden="true"></i></div>
	<div class="generic-container">
      	<div class="panel panel-default">
          	<div class="panel-heading"><span class="lead">Ajouter une édition:</span></div>
          	<div class="formcontainer">
           		<form ng-submit="ctrlE.submit()" name="myFormEdition" class="form-horizontal">
            		<input type="hidden" ng-model="ctrlE.edition.id" />
               		<div class="row">
                   		<div class="form-group col-md-12">
                       		<label class="col-md-2 control-lable" for="name">Nom</label>
                       		<div class="col-md-7">
                         		<input type="text" ng-model="ctrlE.edition.name" id="nameEdition" class="name form-control input-sm" placeholder="Enter name" required ng-minlength="3"/>
                          		<div class="has-error" ng-show="myFormEdition.$dirty">
                           			<span ng-show="myFormEdition.name.$error.required">Ceci est un champs obligatoire</span>
                           			<span ng-show="myFormEdition.name.$error.minlength">La longueur minimale est de 3</span>
                           			<span ng-show="myFormEdition.name.$invalid">Ce champs est invalide</span>
                          		</div>
                       		</div>
                   		</div>
               		</div>
               		<div class="row">
                   		<div class="form-actions floatRight">
                       		<input type="submit"  ng-click="closePopin();" value="{{!ctrlE.edition.id ? 'Ajouter' : 'Mettre à jour'}}" class="btn btn-primary btn-sm" ng-disabled="myFormEdition.$invalid">
                       		<button type="button" ng-click="ctrlE.reset()" class="btn btn-warning btn-sm" ng-disabled="myFormEdition.$pristine">Reset Form</button>
                   		</div>
               		</div>
           		</form>
       		</div>
   		</div>
	</div>
</div>