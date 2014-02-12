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

function loadPhotos() {
	if ($("#id")[0]) {
		$.ajax({
			url: urlContext + 'photo/produit?id=' + $("#id")[0].value,
			dataType:'html'
		}).done(function(res){
			$('#photos').html(res);
			$('.deleteIcon').click(function(e){
				var id = e.currentTarget.id;
				$.ajax({
					url:urlContext+'photo/delete?id=' + id,
					dataType:'html'
				}).done(loadPhotos);
			});
		});
	}
}

function initPanier() {
	$("#vider-panier").click(function() {
		$.post(urlContext + "panier/vider", {}, function(data) {
			$("#panierdiv").html(data);
		});
	});

	$(".inc-quantite-panier").click(function() {
		var idProduit = $(this).attr("data-id");
		var input = $("#quantite_" + idProduit);
		var quantite = parseInt(input.val());
		input.val(quantite + 1);
	});

	$(".dec-quantite-panier").click(function() {
		var idProduit = $(this).attr("data-id");
		var input = $("#quantite_" + idProduit);
		var quantite = parseInt(input.val());
		if (quantite > 1) {
			input.val(quantite - 1);
		}
	});
	
	$(".add-panier").click(function() {
		var idProduit = $(this).attr("data-id");
		var input = $("#quantite_" + idProduit);
		var quantite = parseInt(input.val());
		if (quantite > 0) {
			$.post(urlContext + "panier/add", {id: idProduit, quantite: quantite}, function(data) {
				$("#panierdiv").html(data);
			});
		}
		return false;
	});

}