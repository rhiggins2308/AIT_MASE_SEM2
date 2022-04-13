var calendarUrl = "rest/calendars";
var cartUrl = "rest/cart";

var renderList = function (data) {
	$('#calendarList').empty();
	$.each(data, function (index, calendar) {
		$("#calendarList").append('<div class="card" id="'+ calendar.calId + '">' 
										+ '<div class="card-header">' + calendar.calSize + ' Bottles</div>'
										+ '<div class="card-body">'
										+ '<img src="assets/img/' + calendar.image + '" height="150">'
										+ '<h2>' + calendar.calType + '</h2>'
										+ '</div>'
										+ '<div class="card-footer">'
										+ '<h2>Price: €' + calendar.cost.toFixed(2) + '</h2>'
										+ '<input id="' + calendar.calId + '" class="btnAddToCart" type="button" value="Add To Cart">'
										+ '<input id="' + calendar.calId + '" class="infoButton" type="button" value="More Info">'
										+ '</div>'
								+ '</div>');
	});
};

var findAll = function () {
	$.ajax({
		type: 'GET',
		url: calendarUrl,
		dataType: "json",
		success: renderList
	});
};

var itemToJSON = function(data){
	var cal = 	JSON.stringify({
						"calId" : data.calId,
						"calType" : data.calType,      
						"calSize" : data.calSize,        
						"cost" : data.cost,
						"image" : data.image  
				});
	return cal;
};

var addCartItem = function(data) {
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: cartUrl + "/add",
		dataType: "json",
		data: itemToJSON(data),
		success: console.log("item added to cart"),
		error: console.log("item already added to cart. Change quantities on cart page")
	});
}

var addToCart = function(id) {
	$.ajax({
		type: 'GET',
		url: calendarUrl + "/" + id,
		dataType: "json",
		success: function(data) {
			addCartItem(data);	
		}
	});
};

var checkCart = function(id) {
	$.ajax({
		type: 'GET',
		url: cartUrl + "/check/" + id,
		dataType: "json",
		success: function(id) {
			addToCart(id);	
		},
		error: alert("already in cart")
	});
};

var findById = function(id) {
	$.ajax({
		type: 'GET',
		url: calendarUrl + "/" + id,
		dataType: "json",
		success: function(data) {
			showDetails(data);
		}
	});
};

var showDetails = function(calendar) {
	$('#detailsModal').find('.modal-title').text("Calendar Details");
	$('#modalPic').attr('src', 'assets/img/' + calendar.image);
	$('#calType').val(calendar.calType);
	$('#calSize').val(calendar.calSize);
	$('#cost').val('€' + (calendar.cost).toFixed(2));
	$('#detailsModal').modal('show');
};

$(document).ready(function () {
	
	$(document).on("click", ".btnAddToCart", function() {
		checkCart(this.id);
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
});