var rootUrl = "rest/database";

var upload=function() {
	console.log('findAll');
	$.ajax({
		type: 'GET',
		url: rootUrl+ '/populate',
		dataType: "json", // data type of response
		success: successMessage
	});
};

var successMessage = function(){
	htmlStr = '';
	htmlStr += '<hr>';
	htmlStr += '<div class="message">';
	htmlStr += '<p>Update Successful!   ';
	htmlStr += '<i class="fa-solid fa-square-check" style="color: #33FF8A; font-size:20px;"></i>';
	htmlStr += '</p>';
	htmlStr += '<hr>';
	htmlStr += '</div>';
	$('.p-5').append(htmlStr);
}
$body = $("body");

$(document).on({
    ajaxStart: function() { $body.addClass("loading");    },
     ajaxStop: function() { $body.removeClass("loading"); }    
});

$(document).ready(function(){
	$(document).on('click','#uploadBtn',function() {
		upload();
		return false;
	});
});