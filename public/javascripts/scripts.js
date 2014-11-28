$(document).ready(function(){
	$('[data-toggle=offcanvas]').click(function() {
		$('.row-offcanvas').toggleClass('active');
		});

		$('.btn-toggle').click(function() {
			$(this).find('.btn').toggleClass('active').toggleClass('btn-default').toggleClass('btn-primary');
		});
		
		$('.deletar-meta').click(function() {
				var element = $(this);
				var id = element.attr('id');
				url = "/deletarmeta/" + id;
				
				$.ajax({
				    url: url,
				    type: "DELETE"
				}).done(function(){
				    window.location.href = "/";
				});
		});
});