var rootUrl = "rest/cart";

var getCartItems = function () {
	$.ajax({
		type: 'GET',
		url: rootUrl + "/getall",
		dataType: "json",
		success: renderList
	});
};


$(document).ready(function () {
	getCartItems();
	/*
	$(document).on("click", ".btnAddToCart", function() {
		addToCart(this.id); // to be implemented
		return false;
	});
	
	$(document).on("click", ".infoButton", function() {
		findById(this.id);
		return false;
	});
	
	$(document).on("click", ".btn-default", function() {
		$('#detailsModal').modal('hide');
	});
	
	$(document).on("click", ".close", function() {
		$('#detailsModal').modal('hide');
	});

	findAll();	
	*/
});