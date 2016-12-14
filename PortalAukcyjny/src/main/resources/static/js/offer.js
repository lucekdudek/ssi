$('.product-details form').on('submit', function(event){
	event.preventDefault();
	
	var form = $(this);
	var input = form.find('input[name="price"]');
	
	$.ajax({
		method: "POST",
		url: form.attr('action'),
		data: { price: input.val() }
	})
	.done(function( price ) {
		$('.product-details__bid span').text(price);
	});
})