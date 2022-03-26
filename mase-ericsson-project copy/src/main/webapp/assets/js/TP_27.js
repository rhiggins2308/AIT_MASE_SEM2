var rootUrl ='rest/networkevents/';
var list;

function top_10_imsiFindByDates(startDate, endDate) {
	var network = { events: [] }
	$.ajax({
		type: 'GET',
		url: rootUrl +'top10_imsiCountforNtwEng/'+ startDate + '/' + endDate,
		dataType: "json",
		success: function(data) {
console.log('Data: '+JSON.stringify(data));
            $.each( data, function( key, value ) {
            	  network.events.push( {
						  
				        imsi: data[key][0],
            		    callfailure: data[key][1],
            		});
          		
            	});
            console.log('Network',network);
            console.log(network.size);
            if(network && network.events.length == 0)
            	  $('#abcd').html("NO Records Found");
            else
             var record='';
              $.each(network.events, function(key,value){
            	  record += '<tr>';
            	             	      	  
                  record += '<td>' + 
                      value.imsi+ '</td>';

                    record += '<td>' + 
                      value.callfailure+ '</td>';

                   record += '</tr>';
              });
       

              //INSERTING ROWS INTO TABLE 
              $('#table_body').append(record);
               $('#table_id').DataTable();
            //$('#table').html(JSON.stringify(network.events));
          //renderList();  
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
		
		top_10_imsiFindByDates($('#start_date').val(), $('#end_date').val());
	});



