rootURL = "http://localhost:8082/AdventAles/rest/users/ ..... ";

$(document).ready(function () {
	$("#pwordGroup").hide();

	$(document).on('click', '.btn-user', function () {
		$("#pwordGroup").show();
		/*clearErrorFields();
		if(validate()) {
			registerUser();
			emptyTheForm();	
		}*/
		return false;		
	});
});