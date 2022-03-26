var rootUrl="rest/networkevents/";

var displayAllFailureClass=function(){
	$.ajax({
		type: 'GET',
		url: rootUrl + "allfailureclass",
		dataType: 'json',
		success: renderList
	});
};

var renderList = function(data){
	let dropdown = $('#failureclass-dropdown');

	dropdown.empty();

	dropdown.append('<option selected="true" disabled>Choose Failure Class</option>');
	dropdown.prop('selectedIndex', 0);
	
	var list = data == null ? [] : (data instanceof Array ? data : [data]);
	$.each(list, function(id, failureclass) {
		dropdown.append($('<option></option>').attr('value', failureclass).text(failureclass));
	});
}

$(document).on('click','#btnSearch',function() {
	clearTable();
	searchByFailureClass();
});

function searchByFailureClass(){
	var failureClassVal =$("#failureclass-dropdown").val();
	var network = { events: [] } 
    $.ajax({
        url : rootUrl + "failureclass/" + failureClassVal,
        type : 'GET',
        dataType:'json',
        success : function(data) {              
            $.each( data, function( key, value ) {
            	  network.events.push( {
            		    imsi: value
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
	displayAllFailureClass(); // Get all imsi
});