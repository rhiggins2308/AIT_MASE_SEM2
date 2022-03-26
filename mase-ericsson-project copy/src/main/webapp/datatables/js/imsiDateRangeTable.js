var rootUrl = "rest/networkevents/";

var findAll=function(){
	var start_date = $("#start_date").val();
	var end_date = $("#end_date").val();
	$.ajax({
        url : rootUrl + "queryimsi/" + start_date + "/" + end_date,
        type : 'GET',
        dataType:'json',
        success : renderList
	});
};

var renderList=function(data){
	list = data;
	$.each(list,function(index,data){
		$('#table_body').append('<tr><td>'+data.imsi+'</td></tr>');
		});  
		$('#table_id').DataTable();

};

$(document).on('click','#btnSearch',function() {
	findAll();
});