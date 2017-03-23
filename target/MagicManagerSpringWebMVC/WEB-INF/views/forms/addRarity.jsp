<div class="popin addRarity" ng-class="{'displayRarity': (mainCtrl.displayRarity)}">
	<div class="closeBtn" ng-click="closePopin();"><i class="fa fa-times fa-3x" aria-hidden="true"></i></div>
	<div class="generic-container">
      	<div class="panel panel-default">
          	<div class="panel-heading"><span class="lead">Add a rarity:</span></div>
          	<div class="formcontainer">
           		<form ng-submit="ctrlR.submit()" name="myFormRarity" class="form-horizontal">
            		<input type="hidden" ng-model="ctrlR.rarity.id" />
               		<div class="row">
                   		<div class="form-group col-md-12">
                       		<label class="col-md-2 control-lable" for="name">Name</label>
                       		<div class="col-md-7">
                         		<input type="text" ng-model="ctrlR.rarity.name" id="nameRarity" class="name form-control input-sm" placeholder="Enter name" required ng-minlength="3"/>
                          		<div class="has-error" ng-show="myFormRarity.$dirty">
                           			<span ng-show="myFormRarity.name.$error.required">This is a required field</span>
                           			<span ng-show="myFormRarity.name.$error.minlength">Minimum length required is 3</span>
                           			<span ng-show="myFormRarity.name.$invalid">This field is invalid </span>
                          		</div>
                       		</div>
                   		</div>
               		</div>
               		<div class="row">
                   		<div class="form-actions floatRight">
                       		<input type="submit"  ng-click="closePopin();" value="{{!ctrlR.rarity.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myFormR.$invalid">
                       		<button type="button" ng-click="ctrlR.reset()" class="btn btn-warning btn-sm" ng-disabled="myFormRarity.$pristine">Reset Form</button>
                   		</div>
               		</div>
           		</form>
       		</div>
   		</div>
	</div>
</div>