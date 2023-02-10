$('#slide_next').click(function () {
	slideNext();
})
$('#slide_previous').click(function() {
	slidePrev();
})
function slideNext() {
	if ($('li').is(':animated')) {
		return false;
	}

	let left = $('li.on').width();
	let currentIdx = $('li.on').index();
	let nextIdx = currentIdx + 1;

	if (nextIdx > $('li').length - 1) {
		nextIdx = 0;
	}

	$('li').removeClass('on')
	$('li').eq(currentIdx).css({ zIndex: 10 })
	$('li').eq(nextIdx).addClass('on').css({
		left: left,
		zIndex: 20,
		display: 'block'}).animate({left: 0}, 500, function () {
			$('li').eq(currentIdx).css({ display: 'none' })
			})
		}
		function slidePrev() {
			if ($('li').is(':animated')) {
				return false;
				}
				let left = $('li.on').width() * -1;
				let currentIdx = $('li.on').index();
				let prevIdx = currentIdx - 1;
				$('li').removeClass('on')
				$('li').eq(currentIdx).css({ zIndex: 10 })
				$('li').eq(prevIdx).addClass('on').css({
					left: left,
					zIndex: 20,
					display: 'block'}).animate({
						left: 0}, 500, function () {
							$('li').eq(currentIdx).css({ display: 'none' })
							})
						}