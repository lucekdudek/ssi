$('.btn-toggle').on('click', function(){
    $('.main__bg').addClass('is-active');
    $('.main__sidebar').addClass('is-active');
})

$('.main__bg').on('click', function(){
    $('.main__bg').removeClass('is-active');
    $('.main__sidebar').removeClass('is-active');
})