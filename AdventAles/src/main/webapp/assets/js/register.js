rootURL = "rest/users/register";

var successMessage = function(){
	$('#regSuccess').remove();
	$('#regFailure').remove();
	$('#registrationMessage').append('<div class="message" id="regSuccess">'
									+  	'<p>User Registration Successful!<br/>Please <a href="login.html">Log in</a> to checkout'
									+	'</p><hr></div>');
};

var errorMessage = function(){
	$('#regSuccess').remove();
	$('#regFailure').remove();
	$('#registrationMessage').append('<div class="message" id="regFailure">'
									+  	'<p>User Registration Failed!<br/>Please check your details and try again'
									+	'</p><hr></div>');
};

var formToJSON = function(){
	return JSON.stringify({
		"firstName" : $('#firstName').val(),      
		"lastName" : $('#lastName').val(),        
		"userEmail" : $('#userEmail').val(),
		"dob" : $('#dobDay').val() + $('#dobMonth').val() + $('#dobYear').val(),
		"pword" : $('#userPassword').val(),      
	});
};

var registerUser = function () {
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL,
		dataType: "json",
		data: formToJSON(),
		success: successMessage,
		error: errorMessage
	});
};

function validate() {
	var firstName = $('#firstName').val();
    var lastName = $('#lastName').val();
    var userEmail =  $('#userEmail').val(); 
    var dobDay = $('#dobDay').val();
	var dobMonth = $('#dobMonth').val();
	var dobYear = $('#dobYear').val();
    var userPassword = $('#userPassword').val();
    var verifyPassword = $('#verifyPassword').val();
	var emailTemplate = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	
    if (firstName==null || firstName=="" || lastName==null || lastName=="") { 
    	$('#noName').html("Full Name is required"); 
     	return false; 
    } if (!emailTemplate.test(userEmail)) {
		$('#invalidEmail').html("Email must be in valid format"); 
     	return false; 
	} else if (dobDay==null || dobDay=="" || dobMonth==null || dobMonth=="" || dobYear==null || dobYear=="") {
		$('#invalidDOB').html("Date of Birth is required"); 
     	return false;
	} else if (userPassword.length < 6 || verifyPassword.length < 6) { 
     	$('#invalidDOB').html("Password is too short (at least 6 characters please)"); 
     	return false; 
	} else if (userPassword!=verifyPassword) { 
     	$('#invalidDOB').html("Passwords do not match"); 
     	return false;
	} else if (!$("#ageCheck").is(':checked'))   {
		$('#noAgeCheck').html("You must confirm that you are old enough to purchase alcohol legally"); 
     	return false;
	} else{ return true;}
}

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

var clearErrorFields = function() {
	$("#noName").empty();
  	$('#invalidEmail').empty();
  	$('#invalidDOB').empty();
  	$('#invalidPassword').empty();
  	$('#noAgeCheck').empty();
};

$(document).ready(function () {
	clearErrorFields();
	emptyTheForm();
	document.getElementById("firstName").focus();
	
	$(document).on('click', '.btn-user', function () {
		clearErrorFields();
		if(validate()) {
			registerUser();
			emptyTheForm();	
		}
		return false;		
	});
});