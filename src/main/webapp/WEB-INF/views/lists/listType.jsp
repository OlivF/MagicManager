<form>
	<div class="form-group">
    	<div class="input-group">
        	<div class="input-group-addon"><i class="fa fa-search"></i></div>
	        <input type="text" class="form-control" placeholder="Search Type by name" ng-model="searchType">
	    </div>      
    </div>
</form>
<div class="panel panel-default">
<!-- Default panel contents -->
	<div class="panel-heading"><span class="lead magicfont">OUR COLLECTION : List Type </span><button type="button" ng-click="displayTypeFct();" class="btn btn-success btnAdd"><i class="fa fa-plus-circle fa-1x" aria-hidden="true"></i> Add a type</button></div>
    <div class="tablecontainer">
    	<table class="table table-hover">
        	<thead>
            	<tr>
                	<th>
                    	<a href="javascript:void(0)" ng-click="sortTypeByName = 'name'; sortReverseTypeByName = !sortReverseTypeByName">
                        	<span class="magicfont">Name</span>
                          	<i ng-show="sortTypeByName == 'name' && !sortReverseTypeByName" class="fa fa-caret-up" aria-hidden="true"></i>
                          	<i ng-show="sortTypeByName == 'name' && sortReverseTypeByName" class="fa fa-caret-down" aria-hidden="true"></i>
                        </a> 
                    </th>
                    <th class="width50"></th>
                </tr>
             </thead>
             <tbody>
             	<tr ng-repeat="t in ctrlT.types | orderBy:sortTypeByName:sortReverseTypeByName | filter:searchType">
               		<td class="magicfont"><span ng-bind="t.name"></span></td>
                    <td class="center">
                    	<button type="button" ng-click="displayTypeFct();ctrlT.edit(t.typeId)" class="btn btn-primary custom-width"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> Edit</button>  <!-- <button type="button" ng-click="ctrl.remove(u.id)" class="btn btn-danger custom-width">Remove</button>-->
                    </td>
                </tr>
             </tbody>
        </table>
    </div>
</div>