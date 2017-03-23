<form>
	<div class="form-group">
    	<div class="input-group">
        	<div class="input-group-addon"><i class="fa fa-search"></i></div>
	        <input type="text" class="form-control" placeholder="Search Edition by name" ng-model="searchEdition">
	    </div>      
    </div>
</form>
<div class="panel panel-default">
<!-- Default panel contents -->
	<div class="panel-heading"><span class="lead magicfont">OUR COLLECTION : List Edition </span> <button type="button" ng-click="displayEditionFct();" class="btn btn-success btnAdd"><i class="fa fa-plus-circle fa-1x" aria-hidden="true"></i> Add an Edition</button></div>
    <div class="tablecontainer">
    	<table class="table table-hover">
        	<thead>
            	<tr>
                	<th>
                    	<a href="javascript:void(0)" ng-click="sortEditionByName = 'name'; sortReverseEditionByName = !sortReverseEditionByName">
                        	<span class="magicfont">Name</span>
                          	<i ng-show="sortEditionByName == 'name' && !sortReverseEditionByName" class="fa fa-caret-up" aria-hidden="true"></i>
                          	<i ng-show="sortEditionByName == 'name' && sortReverseEditionByName" class="fa fa-caret-down" aria-hidden="true"></i>
                        </a> 
                    </th>
                    <th class="width50"></th>
                </tr>
             </thead>
             <tbody>
             	<tr ng-repeat="e in ctrlE.editions | orderBy:sortEditionByName:sortReverseEditionByName | filter:searchEdition" ng-click="displayEditionFct();ctrlE.edit(e.id)">
               		<td class="magicfont"><span ng-bind="e.name"></span></td>
                    <td class="center">
                    	<button type="button" ng-click="displayEditionFct();ctrlE.edit(e.id)" class="btn btn-primary custom-width"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> Edit</button>  <!-- <button type="button" ng-click="ctrl.remove(u.id)" class="btn btn-danger custom-width">Remove</button>-->
                    </td>
                </tr>
             </tbody>
        </table>
    </div>
</div>