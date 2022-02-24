var rootURL = "http://localhost:8080/HarryPotter/rest/books";

var renderList = function (data) {
	$('#bookList').empty();
	$.each(data, function (index, book) {
		$("#bookList").append('<div id="' + book.id + '" class="details">'
										+ '<img src="images/' + book.image + '" height="150">'
										+ '<h1>' + book.title + '</h1>'
										+ '<h2>By ' + book.author + '</h2>'
										+ '<h2>Illustrated by ' + book.illustrated + '</h2>'
										+ '<input id="' + book.id + '" class="infoButton" type="button" value="More Info">'
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

var showDetails = function(book) {
	$('#detailsModal').find('.modal-title').text(book.title);
	$('#pic').attr('src', 'images/' + book.imageModal);
	$('#rrp').val('€' + book.rrp);
	$('#online_price').val('€' + book.online);
	$('#saving').val('€' + (book.rrp - book.online).toFixed(2) + ' ('+ ((book.rrp-book.online)/book.rrp).toFixed(2) + '%)');
	$('#detailsModal').modal('show');
};

var findBySeries = function(series) {
	$.ajax({
		type: 'GET',
		url: rootURL + "/search/" + series,
		dataType: "json",
		success: function(data) {
			renderList(data);
		}
	});
}

$(document).ready(function () {
	
	$(document).on("click", ".infoButton", function() {
		findById(this.id);
		return false;
	});
	
	$(document).on("click", "#searchButton", function() {
		$('#filterModal').modal('show');
	});
	
	$(document).on("click", "#submitButton", function() {
		$('#filterModal').modal('hide');
		findBySeries($('#series').val());
	});
	findAll();	
});