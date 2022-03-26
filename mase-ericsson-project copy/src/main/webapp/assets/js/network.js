//var rootUrl = "http://localhost:8082/mase-group-project/rest/networkevents/";
var rootUrl = "rest/networkevents/";

var displayAllImsiForNetwork = function() {
	$.ajax({
		type: 'GET',
		url: rootUrl + "findAllImsiForNetwork/",
		dataType: 'json',
		success: renderList
	});
};

var renderList = function(data) {
	let dropdown = $('#imsi-dropdown');

	dropdown.empty();

	dropdown.append('<option selected="true" disabled>Choose Imsi</option>');
	dropdown.prop('selectedIndex', 0);

	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);
	$.each(list, function(id, imsi) {
		dropdown.append($('<option></option>').attr('value', imsi).text(imsi));
	});
}

$(document).on('click', '#btnSearch', function() {
	clearTable();
	searchByImsi();
});

function searchByImsi() {
	var imsiVal = $("#imsi-dropdown").val();
	var start_date_val = $("#start_date").val();
	var end_date_val = $("#end_date").val();
	console.log(imsiVal);

	var network = { events: [] }
	$.ajax({
		url : rootUrl + "imsireport/" + imsiVal +"/" + start_date_val + "/" + end_date_val,
        type : 'GET',
        dataType:'json',
        success : function(data) {              
            console.log('Data: '+JSON.stringify(data));
            $.each( data, function( key, value ) {
            	  network.events.push( {
            		    imsi: data[key][0],
            		    total: data[key][1],
						sum : data[key][2]						
            		});
            	});

			console.log('Network', network);
			console.log(network.size);
			if (network && network.events.length == 0)
				$('#abcd').html("NO Records Found");
			else
				var record = '';
			$.each(network.events, function(key, value) {
				record += '<tr>';

				record += '<td>' +
					value.imsi + '</td>';

				record += '<td>' +
					value.total + '</td>';
				record += '<td>' +
					value.sum + '</td>';

				record += '</tr>';

			});


			//});

			//INSERTING ROWS INTO TABLE 
			// alert(record);

			$('#table_body').append(record);

			$('#table_id').DataTable();

		},
		error: function(request, error) {
			alert("Request: " + JSON.stringify(request));
		}
	});
}

function clearTable() {
	var div = document.getElementById('table_body');

	while (div.firstChild) {
		div.removeChild(div.firstChild);
	}
}

$(document).ready(function(){    
	displayAllImsiForNetwork();
});