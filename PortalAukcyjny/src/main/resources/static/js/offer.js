$(function(){
	var dateSpan = /([0-9]{4})-([0-9]{2})-([0-9]{2})\s([0-9]{2}):([0-9]{2})/g.exec($('#date strong').text());
	var endDate = new Date(dateSpan[1], dateSpan[2] - 1, dateSpan[3], dateSpan[4], dateSpan[5]);
	var currentDate = new Date();
	var timeDiff = endDate.getTime() - currentDate.getTime();
	var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
	var diffHours = Math.ceil(timeDiff / (1000 * 3600)); 
	var diffMinutes = Math.ceil(timeDiff / (1000 * 60)); 
	var diffSeconds = Math.ceil(timeDiff / 1000); 
	

	function countingMinutes(){
		currentDate = new Date();
		timeDiff = endDate.getTime() - currentDate.getTime();
		diffMinutes = Math.ceil(timeDiff / (1000 * 60)); 
		$('#date strong').text('za '+diffMinutes+' minuty');
		if(diffSeconds > 1){
			setTimeout(countingMinutes, 1000);
		}else{
			countingSeconds();
		}
	}
	
	function countingSeconds(){
		currentDate = new Date();
		timeDiff = endDate.getTime() - currentDate.getTime();
		diffSeconds = Math.ceil(timeDiff / 1000);
		$('#date strong').text('za '+diffSeconds+' sekundy');
		if(diffSeconds > 1){
			setTimeout(countingSeconds, 500);
		}else{
			$('.product-details form').css('display', 'none');
			$('#date').text('Aukcja się skończyła! :(');
		}
	}
	
	if(diffDays <= 1){
		if(diffHours > 1) {
			$('#date strong').text('za '+diffHours+' godziny');
		}else{
			if(diffMinutes > 1){
				countingMinutes();
			}else{
				if(diffSeconds > 1){
					countingSeconds();
				}else{
					$('.product-details form').css('display', 'none');
					$('#date').text('Aukcja się skończyła! :(');
				}
			}
		}
	}
	
	$('.product-details form').on('submit', function(event){
		event.preventDefault();
		
		var currentPrice = $('.product-details__bid span').text();
		
		var form = $(this);
		var input = form.find('input[name="price"]');
		
		input.css('color', '#000000');
		
		if(parseInt(input.val())<=parseInt(currentPrice)) return input.css('color', '#ff0000');
		
		$.ajax({
			method: "POST",
			url: form.attr('action'),
			data: { price: input.val() }
		})
		.done(function( price ) {
			form[0].reset();
			$('.product-details__bid span').text(price);
			input.val(price);
			$('#list').append('<li>'+$('#user').text()+' :'+price+'</li>');
		});
	})
})