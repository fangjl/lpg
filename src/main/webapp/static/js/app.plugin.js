!function ($) {

  $(function(){
 	
  	var isRgbaSupport = function(){
		var value = 'rgba(1,1,1,0.5)',
		el = document.createElement('p'),
		result = false;
		try {
			el.style.color = value;
			result = /^rgba/.test(el.style.color);
		} catch(e) {}
		el = null;
		return result;
	};

	var toRgba = function(str, alpha){
		var patt = /^#([\da-fA-F]{2})([\da-fA-F]{2})([\da-fA-F]{2})$/;
		var matches = patt.exec(str);
		return "rgba("+parseInt(matches[1], 16)+","+parseInt(matches[2], 16)+","+parseInt(matches[3], 16)+","+alpha+")";
	};
	$('.pillbox input').on('blur', function() {
		addPill($(this));
	});

	$('.pillbox input').on('keypress', function(e) {
	    if(e.which == 13) {
	        e.preventDefault();
	        addPill($(this));
	    }
	});


	if ($.support.pjax) {
		
	  $(document).on('click', 'a[data-pjax]', function(event) {
		event.preventDefault();
	    var container = $($(this).data('target'));
	    $.pjax.click(event, {container: container});
	  });
	}
	
	else{
	
		$(document).on('click', 'a[data-pjax]', function(event) {
			
			 var container = $("#content");
			 
			 $.ajax({
					type:  'GET',
					url: $(this).attr("href"),
					data: {},
					cache: true,
					success: function(response){
						
						container.html(response);
						
					},
					error: function(){
						
					},
					statusCode: {
						503: function(xhr, ajaxOptions, thrownError) {
							alert("error_503" || thrownError);
						}
					}
				});
			// alert(container);
	//	    var container = $($(this).data('target'));
		  /*//  alert(1);
		    container.load({
		    	
		    	type:"html",
		    	url:$(this).attr("href"),
		    	success:function(data){
		    		alert(data);
		    	}
		    });
		   // $.pjax.click(event, {container: container});
		   */
		  });
		
		
	}
		
	;
	
	


  });
}(window.jQuery);