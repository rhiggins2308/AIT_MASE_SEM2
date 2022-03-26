var rootUrl = "rest/networkevents/";

function searchByUE(){
	var UE_num_val=$("#ue_num").val();
	var start_date_val=$("#start_date").val();
	var end_date_val=$("#end_date").val();
	
	var network = { events: [] } 
    $.ajax({
        url : rootUrl + "UEreport/" + UE_num_val + "/" + start_date_val + "/" + end_date_val,
        type : 'GET',
        dataType:'json',
        success : function(data) {              
    		//result = [];
    		//data.forEach(v => result.push({ 'ue': v }));  
		console.log('Data: '+JSON.stringify(data));
		$.each( data, function( key, value ) {
            	  network.events.push( {
							imsi: data[key][0],
							total: data[key][1],
							duration : data[key][2]
            		});
      	});

            //console.log('Network', network);
			//console.log(network.size);
        if(network && network.events.length == 0)
        	$('#abcd').html("NO Records Found");
        else
            var record='';
            $.each(network.events, function(key,value){
				record += '<tr>';
				
            	record += '<td>' + 
                	value.imsi + '</td>';

            	record += '<td>' + 
                    value.total + '</td>';

                record += '<td>' + 
                    value.duration + '</td>';
                
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

	while (div.firstChild) {
		div.removeChild(div.firstChild);
	}
}

$(document).on('click','#btnSearch',function() {
		clearTable();
		searchByUE();
		//return false;
});
