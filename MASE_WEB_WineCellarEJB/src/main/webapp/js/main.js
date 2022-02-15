// The root URL for the RESTful services
var rootURL = "http://localhost:8082/MASE_WEB_WineCellarEJB/rest/wines";

/*
var clearList = function () {
	while ($('#wineList').lastChild) {
		$('#wineList').removeChild($('#wineList').lastChild);
	}
};
*/
var renderList = function (data) {
	//clearList();
	$.each(data, function (index, wine) {
		$('#wineList').append('<li><a href="#" id="' + wine.id + '">' + wine.name + '</a></li>');
	});
};

var renderDetails = function (wine) {
	$('#wineId').val(wine.id);
	$('#name').val(wine.name);
	$('#grapes').val(wine.grapes);
	$('#country').val(wine.country);
	$('#region').val(wine.region);
	$('#year').val(wine.year);
	$('#pic').attr('src', 'pics/' + wine.picture);
	$('#description').val(wine.description);
};

var findAll = function () {
	console.log('findAll');
	//clearList();
	$.ajax({
		type: 'GET',
		url: rootURL,
		dataType: "json", // data type of response
		success: renderList
	});
};

var resetForm = function () {
	$('#wineId').val("");
	$('#name').val("");
	$('#grapes').val("");
	$('#country').val("");
	$('#region').val("");
	$('#year').val("");
	$('#pic').attr('src', "");
	$('#description').val("");
	$('#searchKey').val("");
};

var currentWine;

var findById = function (id) {
	console.log('findById: ' + id);
	$.ajax({
		type: 'GET',
		url: rootURL + "/" + id,
		dataType: "json",
		success: function (data) {
			$('#btnDelete').show();
			console.log('findById success: ' + data.name);
			currentWine = data;
			renderDetails(currentWine);
		}
	});
};

var newWine = function () {
	$('#btnDelete').hide();
	resetForm();
};

// Helper function to serialize all the form fields into a JSON string
var formToJSON = function () {
	var wineId = $('#wineId').val();
	return JSON.stringify({
		"id": wineId == "" ? null : wineId,
		"name": $('#name').val(),
		"grapes": $('#grapes').val(),
		"country": $('#country').val(),
		"region": $('#region').val(),
		"year": $('#year').val(),
		"picture": "generic.jpg",
		"description": $('#description').val()
    });
};

var addWine = function () {
	console.log('addWine');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL,
		dataType: "json",
		data: formToJSON(),
		success: function (data, textStatus, jqXHR) {
			alert('Wine created successfully');
			$('#btnDelete').show();
			$('#wineId').val(data.id);
            window.location.reload();
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('addWine error: ' + textStatus);
		}
	});
};

var updateWine = function () {
	console.log('updateWine');
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: rootURL + "/" + $('#wineId').val(),
		dataType: "json",
		data: formToJSON(),
		success: function (data, textStatus, jqXHR) {
			alert('Wine updated successfully');
            window.location.reload();
		},
		error: function (jqXHR, textStatus, errorThrown) {
			alert('updateWine error: ' + textStatus);
		}
	});
};

var deleteWine = function () {
	console.log('deleteWine');
	$.ajax({
		type: 'DELETE',
		url: rootURL + "/" + $('#wineId').val(),
		success: function (data, textStatus, jqXHR){
			alert('Wine deleted successfully');
			resetForm();
            window.location.reload();
		},
		error: function (jqXHR, textStatus, errorThrown)  {
			alert('deleteWine error');
		}
	});
};

var search = function (searchKey) {
	resetForm();
	if (searchKey == "") {
        findAll();
    } else {
        findByName(searchKey);
    }
};

var findByName = function (searchKey) {
	console.log('findByName: ' + searchKey);
	$.ajax({
		type: 'GET',
		url: rootURL + "/search/" + searchKey,
		dataType: "json",
		success: renderList
	});
};


//When the DOM is ready.
$(document).ready(function(){
	$('#btnDelete').hide();
	resetForm();
	findAll();
	
	$(document).on("click", '#wineList a', function(){
		findById(this.id);
		return false;
	});
	
	$(document).on('click','#btnAdd',function() {
		newWine();
		return false;
	});
	
	$(document).on('click','#btnSave',function() {
		//if the id is empty we are adding a new wine - otherwise update
		if ($('#wineId').val() == '') {
			//clearList();
			addWine();
		} else {
			updateWine();		
		}
		return false;
	});
	
	$(document).on('click','#btnDelete',function() {
		deleteWine();
		return false;
	});
	
	$(document).on('click','#btnSearch',function() {
		search($('#searchKey').val());
		return false;
	});
	
	$(document).on('keypress','#searchKey',function(e){
		if(e.which == 13) {
			search($('#searchKey').val());
			return false;
	    }
	});
});