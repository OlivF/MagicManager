$(function() {
    console.log( "ready!" );
    
    console.log( "Init Event on KeyBoard" );
    /*$( "html" ).unbind().keydown(function( event ) {
    	var code = event.which;
    	if (code === 27 && $('.popin.addCard').is(':visible')) {
    		console.log("Close Overlay and Popin Add Card");
    		$('.overlay').removeClass('display');
    		$('.popin.addCard').removeClass('displayandanim');
    	}	
    });*/
    console.log("updated");
 // jQuery
    /*$('.dropdown-menu').find('input').click(function (e) {
        e.stopPropagation();
    });*/
    
    
    /* Hack for edition select */
    $( ".dropdownEdition" ).on( "click", ".editionSelector", function() {
    	 $(".editionChoose").text($(this).text());
    });
    
    /* Hack for type select */
    $( ".dropdownType" ).on( "click", ".typeSelector", function() {
    	 $(".typeChoose").text($(this).text());
    }); 
    
    $('.emptyField').click( function() {
    	console.log("click");
    	$('.result').text("");
    	$('.manaCost').val("");
    });

});