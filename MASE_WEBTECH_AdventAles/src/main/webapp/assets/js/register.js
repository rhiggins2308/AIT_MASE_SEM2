rootUrl = "rest/users/register";

function registerMessage(regField) {
	$("#registermessage").append('<small class="text-muted">'
										+ regField
										+ ' is missing or invalid'
										+ '</small>');
}

function validate() {
	
	var emailTemplate = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	
	if ($('#firstName').val() == "" || $('#lastName').val() == "") {
		registerMessage("First/Last Name");
		return false;
	}
	if ($('#userEmail').val() == "" || !emailTemplate.test($('#userEmail').val())) {
		registerMessage("Email Address");
		return false;
	}
	if ($('#dobDay').val() == "" || $('#dobMonth').val() == "" || $('#dobYear').val() == "") {
		registerMessage("Date Of Birth");
		return false;
	}
	if ($('#userPassword').val() == "") {
		registerMessage("Password");
		return false;
	}
	if ($('#verifyPassword').val() == "") {
		registerMessage("Repeat Password");
		return false;
	}
	if ($('#userPassword').val() != $('#verifyPassword').val()) {
		$("#registermessage").append('<small class="text-muted">'
										+ 'Passwords do not match'
										+ '</small>');
		return false;
	}
	if (!$("#ageCheck").is(':checked')) {
		$("#registermessage").append('<small class="text-muted">'
										+ 'Please verify that you are over 18'
										+ '</small>');
		return false;
	}
	return true;
}

var successMessage = function(){
	$('#registermessage').append('<div class="message" id="regSuccess">'
									+  	'<p>User Registration Successful!<br/>Please <a href="login.html">Log in</a> to checkout'
									+	'</p><hr></div>');
};

var errorMessage = function(){
	$('#registermessage').append('<div class="message" id="regFailure">'
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
		"userType" : 2      
	});
};

var registerUser = function () {
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootUrl,
		dataType: "json",
		data: formToJSON(),
		success: window.location.replace("login.html"),
		error: errorMessage
	});
};

$(document).ready(function () {
	
	$(document).on('click', '#btnRegister', function () {
		if(validate()) {
			registerUser();
		}
	});
});