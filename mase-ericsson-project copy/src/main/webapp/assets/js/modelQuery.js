var rootUrl="rest/model/";

var displayAllModel=function(){
	$.ajax({
		type: 'GET',
		url: rootUrl,
		dataType: 'json',
		success: renderList
	});
};

var renderList = function(data){
	let dropdown = $('#model-dropdown');
	dropdown.empty();
	dropdown.append('<option selected="true" disabled>Choose Model</option>');
	dropdown.prop('selectedIndex', 0);
	
	var list = data == null ? [] : (data instanceof Array ? data : [data]);
	$.each(list, function(id, ue) {
		dropdown.append($('<option></option>').attr('value', ue).text(ue));
	});
}

$(document).on('click','#btnSearch',function() {
	clearTable();
	searchByModel();
});

function searchByModel(){
	var modelVal =$("#model-dropdown").val();
	var network = { events: [] } 
    $.ajax({
        url : rootUrl + modelVal,
        type : 'GET',
        dataType:'json',
        success : function(data) {              
            $.each( data, function( key, value ) {
            	  network.events.push( {
            		    eventId: data[key][0],
            		    causeCode: data[key][1],
						numOccurences : data[key][2]						
            		});
            	});
            if(network && network.events.length == 0)
            	  $('#abcd').html("NO Records Found");
            else
              var record='';
              $.each(network.events, function(key,value){
            	  record += '<tr>';
            	  record += '<td>' + 
                      value.eventId + '</td>';
                  record += '<td>' + 
                      value.causeCode + '</td>';
                  record += '<td>' + 
                      value.numOccurences+ '</td>';
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
	displayAllModel();
});