function login(userId, password) {
	$.ajax({
			type: 'GET',
			url: 'rest/users/login/' + userId + '/' + password,
			dataType: "json",
			success: pickPage,
			error:errorMessage			
		})
}

var errorMessage = function(){
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
	htmlStr += '<p style="color:red;">Incorrect Password! </p>  ';
	$('.validation-message').append(htmlStr);
}

var pickPage = function(data) {
	var type = sessionStorage.setItem("userType",data.userType)
 	if(data.userType=="1" || data.userType=="2" || data.userType=="3"){
		window.location.replace("queryDashboard.html");
	} else if(data.userType=="4"){
		window.location.replace("index.html");
	}
}

function validate() { 
     var password = $('#password').val();
     var userId =  $('#userId').val(); 
          
	if ((userId==null || userId=="") && (password==null || password=="")) {
		alert("Please enter user Id and password to login");
	} else if (userId==null || userId=="") { 
	  	alert("Please enter your user Id!"); 
	 	return false; 
    } else if(password==null || password=="") { 
     	alert("Please enter your password!"); 
     	return false; 
    } else {
		return true;
	}
}

$(document).ready(function () {
	$(document).on("click", "#login", function () {
		if(validate()){
			var userId = $("#userId").val();
			console.log(userId);
			var password = $("#password").val();
			login(userId, password);
		}
		return false;
	})
});