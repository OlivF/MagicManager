<div class="popin addType" ng-class="{'displayType': (mainCtrl.displayType)}">
	<div class="closeBtn" ng-click="closePopin();"><i class="fa fa-times fa-3x" aria-hidden="true"></i></div>
	<div class="generic-container">
      	<div class="panel panel-default">
          	<div class="panel-heading"><span class="lead">Ajouter un type:</span></div>
          	<div class="formcontainer">
           		<form ng-submit="ctrlT.submit()" name="myFormType" class="form-horizontal">
            		<input type="hidden" ng-model="ctrlT.type.typeId" />
               		<div class="row">
                   		<div class="form-group col-md-12">
                       		<label class="col-md-2 control-lable" for="name">Nom</label>
                       		<div class="col-md-7">
                         		<input type="text" ng-model="ctrlT.type.name" id="nameType" class="name form-control input-sm" placeholder="Enter name" required ng-minlength="3"/>
                          		<div class="has-error" ng-show="myFormType.$dirty">
                           			<span ng-show="myFormType.name.$error.required">Ceci est un champs obligatoire</span>
                           			<span ng-show="myFormType.name.$error.minlength">La longueur minimale est de 3</span>
                           			<span ng-show="myFormType.name.$invalid">Ce champs est invalide</span>
                          		</div>
                       		</div>
                   		</div>
               		</div>
               		<div class="row">
                   		<div class="form-actions floatRight">
                       		<input type="submit"  ng-click="closePopin();" value="{{!ctrlT.type.typeId ? 'Ajouter' : 'Mettre à jour'}}" class="btn btn-primary btn-sm" ng-disabled="myFormType.$invalid">
                       		<button type="button" ng-click="ctrlT.reset()" class="btn btn-warning btn-sm" ng-disabled="myFormType.$pristine">Reset Form</button>
                   		</div>
               		</div>
           		</form>
       		</div>
   		</div>
	</div>
</div>