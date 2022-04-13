rootUrl = "rest/order";

var generateOrdersTable = function (data) {
	$('#orderTable').empty();
	$.each(data, function (index, order) {
		var address = "";
		if (order.address2 == null || order.address2 == "") {
			address = order.address1 + ", " + order.town + ", " + order.county;
		} else {
			address = order.address1 + ", " + order.address2 + ", " + order.town + ", " + order.county;
		}
		
		$("#orderTable").append('<tr>'
								+ '<td>' + order.orderId + '</td>'
								+ '<td>' + order.userEmail + '</td>'
								+ '<td>€' + order.orderTotal + '</td>'
								+ '<td>' + address + '</td>'
								+ '<td><button type="button" class="btn btn-warning updateStatus" id="' + order.orderId + '">'
								+ 'Complete'
								+ '</button>'
								+'</td>'
								+ '</tr>');
	});
};

var getOpenOrders = function () {
	$.ajax({
		type: 'GET',
		url: rootUrl + '/getopenorders',
		dataType: "json",
		success: generateOrdersTable
	});
};

var orderToJSON = function (data) {
	return JSON.stringify({
		"orderId": data.wineId,
		"orderShipped": data.orderShipped,
		"userEmail": data.userEmail,
		"orderTotal": data.orderTotal,
		"address1": data.address1,
		"address2": data.address2,
		"town": data.town,
		"county": data.county
    });
};

var shipOrder = function(orderId){
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: rootUrl + '/shiporder/' + orderId,
		dataType: 'json',
		success: window.location.reload()
	});	
};

$(document).ready(function () {
	
	getOpenOrders();
	
	$(document).on("click", ".updateStatus", function() {
		shipOrder(this.id);
		return false;
	});
});