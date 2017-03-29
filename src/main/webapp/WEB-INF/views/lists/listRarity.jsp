<form>
	<div class="form-group">
    	<div class="input-group">
        	<div class="input-group-addon"><i class="fa fa-search"></i></div>
	        <input type="text" class="form-control" placeholder="Chercher une rareté par son nom" ng-model="searchRarity">
	    </div>      
    </div>
</form>
<div class="panel panel-default">
<!-- Default panel contents -->
	<div class="panel-heading"><span class="lead magicfont">Toutes les Raretés </span><button type="button" ng-click="displayRarityFct();" class="btn btn-success btnAdd"><i class="fa fa-plus-circle fa-1x" aria-hidden="true"></i> Ajouter une Rareté</button></div>
    <div class="tablecontainer">
    	<table class="table table-hover">
        	<thead>
            	<tr>
                	<th class="width80">
                    	<a href="javascript:void(0)" ng-click="sortRarityByName = 'name'; sortReverseRarityByName = !sortReverseRarityByName">
                        	<span class="magicfont">Nom</span>
                          	<i ng-show="sortRarityByName == 'name' && !sortReverseRarityByName" class="fa fa-caret-up" aria-hidden="true"></i>
                          	<i ng-show="sortRarityByName == 'name' && sortReverseRarityByName" class="fa fa-caret-down" aria-hidden="true"></i>
                        </a> 
                    </th>
                    <th class="width20"></th>
                </tr>
             </thead>
             <tbody>
             	<tr ng-repeat="r in ctrlR.raritys | orderBy:sortRarityByName:sortReverseRarityByName | filter:searchRarity">
               		<td class="magicfont"><span ng-bind="r.name"></span></td>
                    <td class="center">
                    	<button type="button" ng-click="displayRarityFct();ctrlR.edit(r.id)" class="btn btn-primary custom-width"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> Modifier</button>  <!-- <button type="button" ng-click="ctrl.remove(u.id)" class="btn btn-danger custom-width">Remove</button>-->
                    </td>
                </tr>
             </tbody>
        </table>
    </div>
</div>