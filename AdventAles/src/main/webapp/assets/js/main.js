//var rootURL = "http://localhost:8082/AdventAles/rest/calendars";
var rootURL = "rest/calendars";

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
										+ '<input id="' + calendar.calId + '" class="infoButton" type="button" value="More Info">'
										+ '</div>'
								+ '</div>');
	});
};

var findAll = function () {
	$.ajax({
		type: 'GET',
		url: rootURL,
		dataType: "json",
		success: renderList
	});
};

var findById = function(id) {
	$.ajax({
		type: 'GET',
		url: rootURL + "/" + id,
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
	
/*	
	$(document).on("click", "#searchButton", function() {
		$('#filterModal').modal('show');
	});
	
	$(document).on("click", "#submitButton", function() {
		$('#filterModal').modal('hide');
		findBySeries($('#series').val());
	});
*/
	findAll();	
});