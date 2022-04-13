var rootUrl = "rest/cart";

var renderList = function (data) {
	$("#itemCount").empty();
	$("#productCost").empty();
	$("#shippingCost").empty();
	$("#totalCost").empty();
	var cartItems = 0;
	var productCost = 0;
	var totalShipping = 0;
	var totalCost = 0;
	
	$('#cartDetails').empty();
	$.each(data, function (index, calendar) {
		cartItems++;
		$("#cartDetails").append('<div class="row">'
									+ '<div class="col-lg-3 col-md-12 mb-4 mb-lg-0">'
									+ '<div class="bg-image hover-overlay hover-zoom ripple rounded" data-mdb-ripple-color="light">'
									+ '<img src="assets/img/' + calendar.image + '" class="w-100" />'
									+ '<a href="#!">'
		                    		+ '<div class="mask" style="background-color: rgba(251, 251, 251, 0.2)"></div>'
		                 			+ '</a></div></div>'
									+ '<div class="col-lg-5 col-md-6 mb-4 mb-lg-0">'
									+ '<p><strong>' + calendar.calType + '</strong></p>'
									+ '<p>Bottles: '+calendar.calSize + '</p>'
									+ '<button type="button" class="btn btn-danger btn-sm mb-2" data-mdb-toggle="tooltip" title="Remove Item">'
					                + '<i class="fas fa-trash"></i></button></div>'
					      			+ '<div class="col-lg-4 col-md-6 mb-4 mb-lg-0">'
				                	+ '<div class="d-flex mb-4" style="max-width: 300px">'
				                  	+ '<button class="btn btn-primary px-3 me-2"'
				                    + 'onclick="this.parentNode.querySelector(\'input[type=number]\').stepDown()">'
				                    + '<i class="fas fa-minus"></i></button>'
									+ '<div class="form-outline">'
				                  	+ '<input id="numItems' + calendar.calId + '" min="0" name="quantity" value="1" type="number" class="form-control itemQty" />'
					                + '<label class="form-label" for="form1">Quantity</label></div>'  
					                + '<button class="btn btn-primary px-3 ms-2"'
									+ 'onclick="this.parentNode.querySelector(\'input[type=number]\').stepUp()">'
				                  	+ '<i class="fas fa-plus"></i></button></div>'
				                    + '<p class="text-start text-md-center">'
				                    + '<strong>€' + calendar.cost.toFixed(2) + '</strong></p></div></div>'	
			                	    + '<hr class="my-4" />');
		productCost += calendar.cost.toFixed(2) * $(".itemQty").val();	
	});
	
	if (cartItems == 0) {
		$("#itemCount").append("Cart is empty");
	} else if (cartItems == 1) {
		$("#itemCount").append("Cart - " + cartItems + " item");
	} else {
		$("#itemCount").append("Cart - " + cartItems + " items");
	}
	
	$("#productCost").append("€" + productCost.toFixed(2));	
	
	if (productCost < 75) {
		totalShipping = 5.99 * cartItems;
	}
	
	$("#shippingCost").append("€" + totalShipping.toFixed(2));
	
	$("#totalCost").append("<strong>€" + (productCost + totalShipping).toFixed(2) + "</strong>");
};

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
});