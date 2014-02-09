if (typeof jQuery !== 'undefined') {
	(function($) {
		$('#spinner').ajaxStart(function() {
			$(this).fadeIn();
		}).ajaxStop(function() {
			$(this).fadeOut();
		});
	})(jQuery);
}

function loginClick() {
	$("#loginbtn").prop("disabled",true);
}

function loginSuccess(data,textStatus) {
	$("#loginbtn").prop("disabled",false);
	if(data.error) {
		$("#loginError").show();
		$("#loginError").html(data.error);
		return;
	}
	window.location.reload();
}

function loginFailure(XMLHttpRequest,textStatus,errorThrown) {
	//console.log(textStatus,errorThrown)
}

function loginComplete(XMLHttpRequest,textStatus) {
	//console.log(textStatus)
}
