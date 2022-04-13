$(document).ready(function () {
	if (sessionStorage.getItem("authenticated") == "true" && sessionStorage.getItem("userType") == 1){
		$("#navFulfillment").show();
	} else {
		$("#navFulfillment").hide();
	}
	
	if (sessionStorage.getItem("authenticated") == "true"){
		$("#navLogin").hide();
		$("#navLogout").show();
	} else {
		$("#navLogin").show();
		$("#navLogout").hide();
	}
	
	$(document).on("click", "#navLogout", function() {
		sessionStorage.setItem("authenticated", "false");
		window.location.replace("login.html");
		return false;
	});
});