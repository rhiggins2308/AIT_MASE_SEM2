$(document).ready(function(){
	var type = sessionStorage.getItem("userType");
	 		if(type=="1"){
				document.getElementById("NetworkEngItem").style.display="none";
				document.getElementById("SupportEngItem").style.display="none";
			}
			else if(type=="2"){
				document.getElementById("NetworkEngItem").style.display="none";
			 }
});