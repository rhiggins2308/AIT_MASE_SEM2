var rootUrl = "rest/networkevents/";

var findAll=function(){
	var imsi_num_val=$("#imsi_num").val();
    $.ajax({
        url : rootUrl + imsi_num_val,
        type : 'GET',
        dataType:'json',
        success : renderList
	});
};

var renderList=function(data){
	list=data;
	$.each(list,function(index,data){
		$('#table_body').append('<tr><td>'+data.eventId+'</td><td>'+data.causeCode+
		  '</td><td>'+data.description+'</td></tr>');
		});  
		$('#table_id').DataTable();
};

$(document).on('click','#btnSearch',function() {
	findAll();
});