$(document).on("click", "#updateUser", function(){
	if($('#employeeId').val() == '')
		addNewEmployee();
	else
		updateUser();
	return false;
});

var formToJSONUpdate = function(){
	return JSON.stringify({
		"firstName" : $('#firstName').val(),      
		"lastName" : $('#lastName').val(),        
		"employeeType" : $('#employeeType').val(),
		"password" : $('#passwordInput').val(),   
		"employeeId" : $('#employeeId').val()   
	})};
	
var updateUser = function(){
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: '/' + $('#employeeId').val(),
		dataType: "json",
		data: formToJSONUpdate(),
		success: function(data, textStatus, jqXHR){
			alert('User updated successfully');
			window.location.reload();
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('updateUser error: ' + textStatus);
		}
	});
	
	var emptyTheForm = function() {
		$("#firstName").val(null);
	  	$('#lastName').val(null);
	  	$('#employeeType').val(null);
	  	$('#passwordInput').val(null);
	  	$('#employeeId').val(null);	
		  alert('Update Cancelled.')	
	};
	
	$(function(){
		$('#userDetails').on('click',  function(event){
			event.preventDefault();
			updateUser();
			emptyTheForm();
		})
	});
}