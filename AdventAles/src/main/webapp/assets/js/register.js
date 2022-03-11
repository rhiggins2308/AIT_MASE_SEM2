rootURL = "http://localhost:8082/AdventAles/rest/users/register";

var formToJSONUpdate = function(){
	return JSON.stringify({
		"firstName" : $('#firstName').val(),      
		"lastName" : $('#lastName').val(),        
		"userEmail" : $('#userEmail').val(),
		"dob" : $('#dobDay').val() + $('#dobMonth').val() + $('#dobYear').val(),
		"pword" : $('#userPassword').val(),      
	})};
	
var registerUser = function() {
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: rootURL,
		dataType: "json",
		data: formToJSONUpdate(),
		success: function(data, textStatus, jqXHR) {
			alert('User updated successfully');
			window.location.reload();
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert('Unable to register this user' + textStatus);
		}
	});
};

var emptyTheForm = function() {
	$("#firstName").val(null);
  	$('#lastName').val(null);
  	$('#userEmail').val(null);
  	$('#dobDay').val(null);
  	$('#dobMonth').val(null);
	$('#dobYear').val(null);
	$('#userPassword').val(null);	
	$('#verifyPassword').val(null);	
};

$(document).ready(function () {
	$(document).on('click', '.btn-user', function () {
		registerUser();
		emptyTheForm();
		return false;
	});
});