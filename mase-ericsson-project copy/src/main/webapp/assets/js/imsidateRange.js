var rootUrl ='rest/networkevents/';
var list;

function imsiFindByDates(startDate, endDate) {
	var network = { events: [] }
	$.ajax({
		type: 'GET',
		url: rootUrl +'queryimsi/'+ startDate + '/' + endDate,
		dataType: "json",
		success: function(data) {
            $.each( data, function( key, value ) {
            	  network.events.push( {
						  imsi: data[key]
            	});
            });
            if(network && network.events.length == 0)
            	$('#abcd').html("NO Records Found");
            else
            var record='';
              $.each(network.events, function(key,value){
            	  record += '<tr>';
            	             	      	  
                  record += '<td>' + 
                      value.imsi+ '</td>';

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
   	
$(document).on("click", "#btnSearch", function() {
	imsiFindByDates($('#start_date').val(), $('#end_date').val());
});