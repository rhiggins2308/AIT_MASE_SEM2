var rootUrl = "rest/order";
var cartUrl = "rest/cart";

var getTotals = function (data) {
	$("#productCost").empty();
	$("#shippingCost").empty();
	$("#totalCost").empty();
	var cartItems = 0;
	var productCost = 0;
	var totalShipping = 0;
	var totalCost = 0;
	
	$.each(data, function (index, calendar) {
		var cost = calendar.cost;
		productCost += cost;
		cartItems++;
	});
	$("#productCost").append("€" + productCost.toFixed(2));	
	
	if (productCost < 75) {
		totalShipping = 5.99 * cartItems;
	}
	
	$("#shippingCost").append("€" + totalShipping.toFixed(2));
	
	totalCost = productCost + totalShipping;
	sessionStorage.setItem("totalCost", totalCost);
	$("#totalCost").append("<strong>€" + totalCost.toFixed(2) + "</strong>");
};

var getPendingOrderItems = function () {
	$.ajax({
		type: 'GET',
		url: rootUrl,
		dataType: "json",
		success: getTotals
	});
};

var formToJSON = function() {
	return JSON.stringify({
		"orderShipped" : 0,
		"userEmail": sessionStorage.getItem("user"),
		"orderTotal": sessionStorage.getItem("totalCost"),
		"address1": $('#addressOne').val(),
		"address2": $('#addressTwo').val(),
		"town": $('#addressTown').val(),
		"county": $('#addressCounty').val()
    });
};

var clearCart = function () {
	$.ajax({
		type: 'DELETE',
		url: cartUrl + "/clearcart",
		success: $('#confirmationModal').modal('show')
	});
};

var createOrder = function(){
	$.ajax({
		type: "POST",
		contentType: 'application/json',
		url: rootUrl + "/makeorder",
		dataType: "json",
		data : formToJSON(),
		success: function (data, textStatus, jqXHR) {
			sessionStorage.setItem("orderId", data.orderId);
			clearCart();
		}
	});
};

$(document).ready(function () {
	getPendingOrderItems();
	$(document).on("click", "#btnOrder", function() {
		createOrder();
		return false;
	});
	
	$(document).on("click", "#btnRedirect", function() {
		$('#confirmationModal').modal('hide');
		window.location.replace("store.html");
	});
});