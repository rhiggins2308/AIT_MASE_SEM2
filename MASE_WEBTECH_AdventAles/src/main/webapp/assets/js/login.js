rootUrl = "rest/users/login/";

function clearMessages() {
	$('.text-muted').remove();
}

function clearFields() {
	$('#userEmail').val(null);
	$('#userPassword').val(null);
}

function redirect(data) {
	if (sessionStorage.getItem("userType") == 1) {
		window.location.replace("fulfilment.html");
	} else {
		if (sessionStorage.getItem("checkoutFlag" == "return")){
			window.location.replace("checkout.html");
		} else {
			window.location.replace("store.html");
		}	
	}
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
		success: function(data) {
			sessionStorage.setItem("authenticated", "true");
			sessionStorage.setItem("user", userEmail);
			sessionStorage.setItem("userType", data.userType)
			redirect();
		},
		error: function() {
			sessionStorage.setItem("authenticated", "false");
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