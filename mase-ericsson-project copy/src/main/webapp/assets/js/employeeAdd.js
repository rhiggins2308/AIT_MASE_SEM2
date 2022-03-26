var rootUrl = "rest/users";
var currentUser;

var findAll=function() {
	$.ajax({
		type: 'GET',
		url: rootUrl+ '/allUsers',
		dataType: "json", // data type of response
		success: renderList
	});
};
var clearFields = function () {
	$('#firstName').val(null);
	$('#lastName').val(null);
	$('#employeeType').val(null); 
    $('#password').val(null);
    $('#passwordRepeat').val(null);
}

var addUser = function () {
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootUrl+'/register',
		dataType: "json",
		data: formToJSON(),
		success:successMessage,
		error: errorMessage
	});
};
var successMessage = function(){
	clearFields();
	htmlStr = '';
	htmlStr += '<hr>';
	htmlStr += '<div class="message">';
	htmlStr += '<p>Employee Registration Successful!   ';
	htmlStr += '<i class="fa-solid fa-thumbs-up" style="color: #33FF8A; font-size:20px;"></i>';
	htmlStr += '</p>';
	htmlStr += '<hr>';
	htmlStr += '</div>';
	$('.p-5').append(htmlStr);
}
var errorMessage = function(){
	clearFields();
	htmlStr = '';
	htmlStr += '<div class="message">';
	htmlStr += '<p>Registration Error!   ';
	htmlStr += '<i class="fa-solid fa-thumbs-down" style="color: #ff0000; font-size:20px;"></i>';
	htmlStr += '</p>';
	htmlStr += '<hr>';
	htmlStr += '</div>';
	$('.p-5').append(htmlStr);
}
var renderList= function(data) {
	var list = data == null ? [] : (data instanceof Array ? data : [data]);
	$('#userList li').remove();
	$.each(list, function(id, user) {
		$('#userList').append('<li>'+user.firstName+' '+user.lastName+'</li>');
	});
};

var formToJSON=function(){
	var userType = 0;
	if($('#employeeType').val().localeCompare("Customer Service Representative")){
		userType = 1;
	}
	else if($('#employeeType').val().localeCompare("Support Engineer")){
		userType = 2;
	}
	else if($('#employeeType').val().localeCompare("Network Management Engineer")){
		userType = 3;
	}
	return JSON.stringify({	
		"userType" : 	userType,      
		"firstName": $('#firstName').val(),      
		"lastName" : $('#lastName').val(),        
		"password" : $('#password').val()   
	});
};

function validate()
{ 
     var firstName = $('#firstName').val();
     var lastName = $('#lastName').val();
     var userType =  $('#employeeType').val(); 
     var password = $('#password').val();
     var passwordRepeat= $('#passwordRepeat').val();
          
     if (firstName==null || firstName=="")
     { 
     alert("First Name can't be blank"); 
     return false; 
     }

     if (lastName==null || lastName=="")
     { 
     alert("Last Name can't be blank"); 
     return false; 
     }
     else if (passwordRepeat==null || passwordRepeat=="")
     { 
     alert("Repeat Password can't be blank"); 
     return false; 
     }
     else if(password.length<6)
     { 
     alert("Password must be at least 6 characters long."); 
     return false; 
     } 
     else if (password!=passwordRepeat)
     { 
     alert("Confirm Password should match with the Password"); 
     return false; 
     }   
     else{
	return true;}
 }  

$(document).ready(function(){
	$(document).on('click','#regBtn',function() {
		if(validate()){
		addUser();}
		return false;
	});
	
	$(document).on('click','#allUserBtn',function() {
		
		findAll();
		return false;
	});
});