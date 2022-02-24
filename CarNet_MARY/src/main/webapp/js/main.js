 

var rootUrl = "http://localhost:8080/CarNet/rest/"
var list;

// Step 3
var findAll = function() {
	$.ajax({
		type: 'GET',
		url: rootUrl + "cars",
		dataType: "json",
		success: renderList
	});
}

//Step 4
var findById = function(id) {
	$.ajax({
		type: 'GET',
		url: rootUrl + 'cars/' + id,
		dataType: "json",
		success: function(data) {
			currentEntry = data;
			showDetails(currentEntry);
		}
	});
}

//Step 5
var findByDates = function(startDate, endDate) {
	$.ajax({
		type: 'GET',
		url: rootUrl + 'cars/search/' + startDate + '/' + endDate,
		dataType: "json",
		success: function(data) {
			$('#whichCars').text("Filtering from " + startDate + ' to ' + endDate);
			renderList(data);
		}
	});
}

var renderList = function(list) {
	htmlStr = '';
	$('.scroll_cars').remove();
	$.each(list, function(index, cars) {
		htmlStr += '<div class="scroll_cars">';
		htmlStr += '<img src="images/cars/' + cars.image + '" alt="Car">';
		htmlStr += '<p>' + cars.year + ' ' + cars.make + ' ' + cars.model + '</p>';
		htmlStr += '<a href id="' + cars.id + '">';
		htmlStr += '<span class="fa fa-info-circle fa-2x" id="' + cars.id + '"></span></a></div>';
	});
	$('.list_cars_scroll').append(htmlStr);
};

var showDetails = function(car) {
	$('#detailsModal').find('.modal-title').text("Details for " + car.year + ' ' + car.make + ' ' + car.model);
	$('#pic').attr('src', 'images/cars/' + car.image);
	$('#color').val(car.color);
	$('#year').val(car.year);
	$('#engine').val(car.litre + ' litres');
	$('#miles').val(car.mileage + ' miles');
	$('#condition').val(car.cond);
	$('#detailsModal').modal('show');
}


$(document).ready(function() {
	// Event handler for the modal info
	$(document).on("click", ".fa-info-circle", function() {
		findById(this.id);
		return false;
	});

	// Event handler for the modal filter
	$(document).on("click", "#searchButton", function() {
		$('#filterModal').modal('show');
	});

	// Event handler for the modal submit button
	$(document).on("click", "#submitButton", function() {
		$('#filterModal').modal('hide');
		findByDates($('#minYear').val(), $('#maxYear').val());
	});

	findAll();
});