var rootURL = "http://localhost:8080/HarryPotter_TEST/rest/books";

var renderList = function (data) {
	$('.scroll_cars').remove();
	$.each(data, function (index, car) {
		$(".list_cars_scroll").append('<div class="scroll_cars">'
										+ '<img src="images/cars/' + car.image + '" alt="Car">'
										+ '<p>' + car.year + ' ' + car.make + ' ' + car.model + '</p>'
										+ '<a href="#" id="' + car.id + '">'
										+ '<span class="fa fa-info-circle fa-2x"></span>'
										+ '</a>'
										+ '</div>');
	});
};

var findAll = function () {
	$.ajax({
		type: 'GET',
		url: rootURL,
		dataType: "json", // data type of response
		success: renderList
	});
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
};

var findById = function (id) {
	$.ajax({
		type: 'GET',
		url: rootURL + "/" + id,
		dataType: "json",
		success: function (data) {
			showDetails(data);
		}
	});
	
	$('#detailsModal').modal('show');
};

var findByDates = function(minYear, maxYear) {
	$.ajax({
		type: 'GET',
		url: rootURL + "/search/" + minYear + "/" + maxYear,
		dataType: "json",
		success: function(data) {
			$('#whichCars').text("Filtering from " + minYear + " to " + maxYear);
			renderList(data);	
		}
	});	
}

//When the DOM is ready.
$(document).ready(function () {
	findAll();
	
	$(document).on('click', '.scroll_cars a', function () {
		findById(this.id);
		return false;
	});
	
	$(document).on('click', '#searchButton', function () {
		$('#filterModal').modal('show');
	});
	
	$(document).on('click', '#submitButton', function() {
		$('#filterModal').modal('hide');
		findByDates($('#minYear').val(), $('#maxYear').val());
	});
	
});