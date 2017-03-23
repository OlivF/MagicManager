<div class="generic-container">

<div class="panel panel-warning">
<div class="panel-heading">
    <h3 class="panel-title">CREATE YOUR DECK</h3>
  </div>
  <div class="panel-body">
  	
	<ul class="list-group">
		<li class="list-group-item list-group-item-info">1. Complete form with deck information</li>
		<li class="list-group-item list-group-item-info">2. Add card from your collection using "Add to your deck" button</li>
		<li class="list-group-item list-group-item-info">3. Select number for each card composing your deck</li>
		<li class="list-group-item list-group-item-info">4. Validate</li>
	</ul>
	
	 <form>
  	<div class="form-group">
  		<label class="sr-only" for="deckname">Deck Name</label>
	    <div class="input-group">
	      <div class="input-group-addon">Deck Name</div>
	      <input type="text" class="form-control" id="deckname" placeholder="Enter your deck name">
	    </div>
	    <hr class="featurette-divider">
	    <div class="form-group">
		    <textarea class="form-control" rows="6" placeholder="Enter your deck description"></textarea>
		</div>
		
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
                              <th> 
                              	<a href="javascript:void(0)" ng-click="sortType = 'manaCost'; sortReverse = !sortReverse">
                              		<span class="center magicfont">Mana Cost</span>
                              		<i ng-show="sortType == 'manaCost' && !sortReverse" class="fa fa-caret-up" aria-hidden="true"></i>
                          	  		<i ng-show="sortType == 'manaCost' && sortReverse" class="fa fa-caret-down" aria-hidden="true"></i>
                              	</a>
                              
                              </th>
                              <th> 
                              	<a href="javascript:void(0)" ng-click="sortType = 'rarity'; sortReverse = !sortReverse">
                              		<span class="center magicfont">Rarity</span>
                              		<i ng-show="sortType == 'rarity' && !sortReverse" class="fa fa-caret-up" aria-hidden="true"></i>
                          	  		<i ng-show="sortType == 'rarity' && sortReverse" class="fa fa-caret-down" aria-hidden="true"></i>
                              	</a>
                              
                              </th>
                              <th> 
                              	<a href="javascript:void(0)" ng-click="sortType = 'price'; sortReverse = !sortReverse">
                              		<span class="center magicfont">Price</span>
                              		<i ng-show="sortType == 'price' && !sortReverse" class="fa fa-caret-up" aria-hidden="true"></i>
                          	  		<i ng-show="sortType == 'price' && sortReverse" class="fa fa-caret-down" aria-hidden="true"></i>
                              	</a> 
                              
                              </th>
                              <th> 
                              	<a href="javascript:void(0)">
                              		<span class="center magicfont">Nb</span>
                              	</a> 
                              
                              </th>
                              <th></th>
                          </tr>
                      </thead>
                      <tbody>
                          
                      </tbody>
                  </table>
                </div>
                <div class="input-group">
               	 <input class="btn btn-success" type="submit" value="Submit">
               	</div>
  	</div>
  </form>
  </div>
  
 
</div>
</div>