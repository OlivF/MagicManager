<div class="popin addType" ng-class="{'displayType': (mainCtrl.displayType)}">
	<div class="closeBtn" ng-click="closePopin();"><i class="fa fa-times fa-3x" aria-hidden="true"></i></div>
	<div class="generic-container">
      	<div class="panel panel-default">
          	<div class="panel-heading"><span class="lead">Add a type:</span></div>
          	<div class="formcontainer">
           		<form ng-submit="ctrlT.submit()" name="myFormType" class="form-horizontal">
            		<input type="hidden" ng-model="ctrlT.type.typeId" />
               		<div class="row">
                   		<div class="form-group col-md-12">
                       		<label class="col-md-2 control-lable" for="name">Name</label>
                       		<div class="col-md-7">
                         		<input type="text" ng-model="ctrlT.type.name" id="nameType" class="name form-control input-sm" placeholder="Enter name" required ng-minlength="3"/>
                          		<div class="has-error" ng-show="myFormType.$dirty">
                           			<span ng-show="myFormType.name.$error.required">This is a required field</span>
                           			<span ng-show="myFormType.name.$error.minlength">Minimum length required is 3</span>
                           			<span ng-show="myFormType.name.$invalid">This field is invalid </span>
                          		</div>
                       		</div>
                   		</div>
               		</div>
               		<div class="row">
                   		<div class="form-actions floatRight">
                       		<input type="submit"  ng-click="closePopin();" value="{{!ctrlT.type.typeId ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myFormType.$invalid">
                       		<button type="button" ng-click="ctrlT.reset()" class="btn btn-warning btn-sm" ng-disabled="myFormType.$pristine">Reset Form</button>
                   		</div>
               		</div>
           		</form>
       		</div>
   		</div>
	</div>
</div>