//rootURL = "http://localhost:8082/AdventAles/rest/users/login";
rootURL = "rest/users/login";

var emptyTheForm = function() {
	$('#userEmail').empty();
  	$('#userPassword').empty();	
};

function validate() { 
	var userEmail =  $('#userEmail').val(); 
	var userPassword = $('#userPassword').val();
     
    if ((userEmail==null || userEmail=="") && (userPassword==null || userPassword=="")) {
		alert("Please enter user Id and password to login");
		return false;
	} else if (userEmail==null || userEmail=="") { 
	  	alert("Please enter your user Id!"); 
		return false;
	} else if(userPassword==null || userPassword=="") { 
		alert("Please enter your password!"); 
     	return false;
	} else {
		return true;
	}
}

// utility messages taken from team possible
var errorMessage = function(){
	console.log("login failed message!");
	document.getElementById("password").value = "";
	htmlStr = '';
	htmlStr += '<div class="message">';
	htmlStr += '<p>Incorrect Password!   ';
	htmlStr += '<i class="fa-solid fa-thumbs-down" style="color: #ff0000; font-size:20px;"></i>';
	htmlStr += '</p>';
	htmlStr += '<hr>';
	htmlStr += '</div>';
	$('.p-5').append(htmlStr);
}
var validationMessage = function(){
	console.log("field validation message!");
	htmlStr += '<p style="color:red;">Incorrect Password! </p>  ';
	$('.validation-message').append(htmlStr);
}

// not relevant for thsi project ... taken from team possible
var pickPage = function(data){
	var type = sessionStorage.setItem("userType",data.userType)
//	var json = JSON.parse(data);
	console.log("user type: "+type)
	 if(data.userType=="1"){
				window.location.replace("http://localhost:8080/mase-group-project/queryDashboard.html");
			}else if(data.userType=="2"){
				window.location.replace("http://localhost:8080/mase-group-project/queryDashboard.html");
			}else if(data.userType=="3"){
				window.location.replace("http://localhost:8080/mase-group-project/queryDashboard.html");
			}else if(data.userType=="4"){
				window.location.replace("http://localhost:8080/mase-group-project/index.html");
			}
}

function login(userEmail, userPassword) {
	$.ajax({
		type: 'GET',
		url: rootURL + '/login/' + userEmail + '/' + userPassword,
		dataType: "json",
		success: pickPage,
		error: errorMessage 
	});
}

$(document).ready(function () {
	$(document).on('click', '.btn-user', function () {
		//clearErrorFields();
		if(validate()) {
			var userEmail = $("#userEmail").val();
			var pword = $("#userPassword").val();
			login(userEmail, pword);
			emptyTheForm();	
		}
		return false;		
	});
});