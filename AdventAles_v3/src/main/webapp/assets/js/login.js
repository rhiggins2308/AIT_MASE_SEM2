rootUrl = "rest/users/login/";

function clearMessages() {
	$('.text-muted').remove();
}

function clearFields() {
	$('#userEmail').val(null);
	$('#userPassword').val(null);
}

function resetPage() {
	clearMessages();
	clearFields();
}

function validate() {
	var userEmail = $('#userEmail').val();	
	var userPassword = $('#userPassword').val();
	
	if (userEmail=="" || userPassword=="") {
		$("#authmessage").append('<small class="text-muted">'
										+ 'Email address / password combination incorrect'
										+ '</small>');
		
		return false;
	} else {
		return true;
	}
}

function login(userEmail, userPassword) {
	$.ajax({
		type: 'GET',
		url: rootUrl + userEmail + '/' + userPassword,
		dataType: "json",
		success: function() {
			sessionStorage.setItem("authenticated", true);
			sessionStorage.setItem("user", userEmail);
			resetPage();
			var user = sessionStorage.getItem("user");
			$("#authmessage").append('<small class="text-muted">'
										+ 'Login Successful'
										+ ': '
										+ '</small>');
		},
		error: function() {
			sessionStorage.setItem("authenticated", false);
			resetPage();
			$("#authmessage").append('<small class="text-muted">'
										+ 'Login Failed'
										+ '</small>');
		}
	});
}

$(document).ready(function () {
	resetPage();
	
	$(document).on('click', '#btnLogin', function() {
		clearMessages();
		
		if (validate()) {
			var userEmail = $('#userEmail').val();	
			var userPassword = $('#userPassword').val();
			console.log("test");
			login(userEmail, userPassword);
		}
		
		return false;	
	});
});