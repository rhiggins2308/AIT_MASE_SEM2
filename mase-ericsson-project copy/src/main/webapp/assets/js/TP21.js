var rootUrl="rest/networkevents/";

var displayAllImsi=function(){
	$.ajax({
		type:'GET',
		url:rootUrl + "findAllImsi",
		dataType:'json',
		success:renderList
	});
};

var renderList = function(data){
	let dropdown = $('#imsi-dropdown');
	dropdown.empty();
	dropdown.append('<option selected="true" disabled>Choose Imsi</option>');
	dropdown.prop('selectedIndex', 0);
	
	var list = data == null ? [] : (data instanceof Array ? data : [data]);
	$.each(list, function(id, imsi) {
		dropdown.append($('<option></option>').attr('value', imsi).text(imsi));
	});
}

$(document).on('click','#btnSearch',function() {
	clearTable();
	searchByImsi();
});

function searchByImsi(){
	var imsiVal =$("#imsi-dropdown").val();
	var start_date_val=$("#start_date").val();
	var end_date_val=$("#end_date").val();

	var network = { events: [] } 
    $.ajax({
        url : rootUrl + "imsiCountForServiceRep/" + imsiVal + "/" + start_date_val + "/" + end_date_val,
        type : 'GET',
        dataType:'json',
        success : function(data) {              
            $.each( data, function( key, value ) {
            	  network.events.push( {
            		    imsi: data[key][0],
            		    count: data[key][1]						
            		});
            	});
            if(network && network.events.length == 0)
            	  $('#abcd').html("NO Records Found");
            else
              var record='';
              $.each(network.events, function(key,value){
            	  record += '<tr>';
            	  record += '<td>' + 
                      value.imsi + '</td>';
                  record += '<td>' + 
                      value.count + '</td>';
                  record += '</tr>';
		  	  });
              $('#table_body').append(record);
		  	  $('#table_id').DataTable();
        },
        error : function(request,error)
        {
            alert("Request: "+JSON.stringify(request));
        }
	});
}

function clearTable() {
    var div = document.getElementById('table_body');

    while(div.firstChild) {
               div.removeChild(div.firstChild);
  	}
}

$(document).ready(function(){    
	displayAllImsi();
});