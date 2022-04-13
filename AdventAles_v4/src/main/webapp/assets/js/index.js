$(document).ready(function () {
	if (sessionStorage.getItem("authenticated") == null) {
		sessionStorage.setItem("authenticated", "false");
	}
});