var rootUrl = "rest/graphimsiReport/";
var failureClassArray=[];
var imsiArray=[];
var durationArray=[];
var barColors = ["red", "green","blue","orange","brown", "yellow", "aqua", "pink", "purple", "lavender",  "indigo", "lightgreen", "lightblue", "crimson", "turquoise", "gray", "cream", "violet"];
var imsiVal = $("#imsi-dropdown").val();

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

function searchByImsi() {
	imsiVal = $("#imsi-dropdown").val();
	var start_date_val = $("#start_date").val();
	var end_date_val = $("#end_date").val();
	console.log(imsiVal);

	var network = { events: [] }
	$.ajax({
		url : rootUrl + imsiVal +"/" + start_date_val + "/" + end_date_val,
        type : 'GET',
        dataType:'json',
        success : function(data) {              
            console.log('Data: '+JSON.stringify(data));
            $.each( data, function( key, value ) {
            	  network.events.push( {
            		    failureClass: data[key][0], 
						imsi : data[key][1]	,
						duration : data[key][2]						
            		});	

				failureClassArray.push(data[key][0]);
				imsiArray.push(data[key][1]);
				durationArray.push(data[key][2]);	
						
            });
			drawTable(network.events);
			chartGraph();
		}
		
	});
}
		
$(document).on('click', '#btnSearch', function() {
	let myChart = document.getElementById('myChart').getContext('2d');

    document.getElementById("myChart").style.display = "block";
    // Global Options
    //Chart.defaults.global.defaultFontFamily = 'Arial';
    //Chart.defaults.global.defaultFontSize = 15;
    Chart.defaults.global.defaultFontColor = '#777';
	
	searchByImsi();
	clearTable();
	return false;
});		
		
var chartGraph=function(){
	imsiVal = $("#imsi-dropdown").val();

	new Chart("myChart", {
  		type: "bar",
  		data: {
    	labels: imsiArray,
    	datasets: [{
      	backgroundColor: barColors,
		label : ' Total call Failure duration',
      	data: durationArray,
          borderWidth:1,
          borderColor:'#777',
          hoverBorderWidth:3,
          hoverBorderColor:'#000'

    	}]
  		},
  		options: {	
		      indexAxis: 'y',
			  skipNull: true,
		      plugins: {
        		legend: {
		          display: true,
		          position: "right",
				
				        }       
      			},
    		title: {
			      display: true,
			      text: "Failure Details for IMSI - " + imsiVal,
        		  fontSize:20
		
    		},
	        layout:{
    		      padding:{
            		left:10,
            		right:0,
            		bottom:0,
            		top:0
          	}
        },
        tooltips:{
          enabled:true
        }

		}
	});
}

function drawTable(data){
	clearTable();
	$('#table_body')
              
              $.each(data, function(key,value){
	var record='';
            	  record += '<tr>';
            	  
            	  record += '<td>' + 
                      value.failureClass + '</td>';
            	
                  record += '<td>' + 
                     value.imsi+ '</td>';
                   record += '<td>' + 
                     value.duration+ '</td>';

                   record += '</tr>';

					$('#table_body').append(record);
		  	  }); 

$('#table_id').DataTable();
}  

function clearTable() {
   var div = document.getElementById('table_body');

    while(div.firstChild) {
               div.removeChild(div.firstChild);
  	}
//$('#table_id').DataTable().clear().draw();
}

$(document).ready(function(){    
	displayAllImsiForNetwork();
});