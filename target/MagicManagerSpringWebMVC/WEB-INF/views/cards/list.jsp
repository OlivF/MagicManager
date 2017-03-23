<div class="generic-container">
          <form>
    <div class="form-group">
      <div class="input-group">
        <div class="input-group-addon"><i class="fa fa-search"></i></div>

        <input type="text" class="form-control" placeholder="Search card by name" ng-model="searchCard">

      </div>      
    </div>
  </form>
          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead magicfont">OUR COLLECTION : List of Magic Cards </span> <button type="button" ng-click="displayCardFct();" class="btn btn-success btnAdd"><i class="fa fa-plus-circle fa-1x" aria-hidden="true"></i> Add a Card</button></div>
              <div class="tablecontainer">
                  <table class="table table-hover">
                      <thead>
                          <tr>
                          	  <th>
                          	  	<a href="javascript:void(0)" ng-click="sortType = 'nameFr'; sortReverse = !sortReverse">
                          	  		<span class="magicfont">French Name</span>
                          	  		<i ng-show="sortType == 'nameFr' && !sortReverse" class="fa fa-caret-up" aria-hidden="true"></i>
                          	  		<i ng-show="sortType == 'nameFr' && sortReverse" class="fa fa-caret-down" aria-hidden="true"></i>
                          	  	</a> 
                          	  </th>
                              <th>
                              	<a href="javascript:void(0)" ng-click="sortType = 'nameEn'; sortReverse = !sortReverse">
                              		<span class="magicfont">English Name</span>
                              		<i ng-show="sortType == 'nameEn' && !sortReverse" class="fa fa-caret-up" aria-hidden="true"></i>
                          	  		<i ng-show="sortType == 'nameEn' && sortReverse" class="fa fa-caret-down" aria-hidden="true"></i>
                              	</a> 
                              </th>
                              <th>
                               <a href="javascript:void(0)" ng-click="sortType = 'type'; sortReverse = !sortReverse">
                               <span class="magicfont">Type</span>
                               <i ng-show="sortType == 'type' && !sortReverse" class="fa fa-caret-up" aria-hidden="true"></i>
                          	  		<i ng-show="sortType == 'type' && sortReverse" class="fa fa-caret-down" aria-hidden="true"></i>
                               </a> 
                               
                              </th>
                              <th>
                               <a href="javascript:void(0)" ng-click="sortType = 'edition'; sortReverse = !sortReverse">
                               	<span  class="magicfont">Edition</span>
                               	<i ng-show="sortType == 'edition' && !sortReverse" class="fa fa-caret-up" aria-hidden="true"></i>
                          	  		<i ng-show="sortType == 'edition' && sortReverse" class="fa fa-caret-down" aria-hidden="true"></i>
                               </a>
                              </th>
                              <th class="center"> 
                              	<a href="javascript:void(0)" ng-click="sortType = 'manaCost'; sortReverse = !sortReverse">
                              		<span class="center magicfont">Mana Cost</span>
                              		<i ng-show="sortType == 'manaCost' && !sortReverse" class="fa fa-caret-up" aria-hidden="true"></i>
                          	  		<i ng-show="sortType == 'manaCost' && sortReverse" class="fa fa-caret-down" aria-hidden="true"></i>
                              	</a>
                              
                              </th>
                              <th class="center"> 
                              	<a href="javascript:void(0)" ng-click="sortType = 'rarity'; sortReverse = !sortReverse">
                              		<span class="center magicfont">Rarity</span>
                              		<i ng-show="sortType == 'rarity' && !sortReverse" class="fa fa-caret-up" aria-hidden="true"></i>
                          	  		<i ng-show="sortType == 'rarity' && sortReverse" class="fa fa-caret-down" aria-hidden="true"></i>
                              	</a>
                              
                              </th>
                              <th class="center"> 
                              	<a href="javascript:void(0)" ng-click="sortType = 'price'; sortReverse = !sortReverse">
                              		<span class="center magicfont">Price</span>
                              		<i ng-show="sortType == 'price' && !sortReverse" class="fa fa-caret-up" aria-hidden="true"></i>
                          	  		<i ng-show="sortType == 'price' && sortReverse" class="fa fa-caret-down" aria-hidden="true"></i>
                              	</a> 
                              
                              </th>
                              <th class="center"> 
                              	<a href="javascript:void(0)">
                              		<span class="center magicfont">Nb</span>
                              	</a> 
                              
                              </th>
                              <th class="width50"></th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="u in ctrl.cards | orderBy:sortType:sortReverse | filter:searchCard">
                         	  <td class="magicfont"><span ng-bind="u.nameFr"></span></td>
                              <td class="magicfont"><span ng-bind="u.nameEn"></span></td>
                              <td class="magicfont"><span ng-bind="u.type"></span></td>
                              <td class="magicfont"><span ng-bind="u.edition"></span></td>
                              <td class="center magicfont"><span ng-bind-html='getManaCostHtml(u.manaCost, "<c:url value='/static/img/'/>")'></span></td>
                              <td class="center magicfont"><img ng-src="<c:url value='/static/img/{{u.rarity}}.gif'/>"></td>
                              <td class="center small"><span ng-bind="u.price"></span>&euro;</td>
                              <td class="center small"><span ng-bind="u.nbItem"></span></td>
                              <td class="center">
                              <button type="button" ng-click="displayCardFct();ctrl.edit(u.id)" class="btn btn-primary custom-width"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> Edit</button>  <!-- <button type="button" ng-click="ctrl.remove(u.id)" class="btn btn-danger custom-width">Remove</button>-->
                              </td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>		  
      </div>