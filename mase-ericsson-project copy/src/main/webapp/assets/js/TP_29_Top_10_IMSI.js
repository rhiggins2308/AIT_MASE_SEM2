var rootUrl ='rest/networkevents/';  

var imsiArray=[];
var failureArray=[];

var barColors = ["red", "green","blue","orange","brown", "yellow", "aqua", "pink", "purple", "lavender",  "indigo", "lightgreen", "lightblue", "crimson", "turquoise", "gray", "cream", "violet"];

function top_10_imsiFindByDates(startDate, endDate) {
	var network = { events: [] }
	$.ajax({
		type: 'GET',
		url: rootUrl +'top10_imsiCountforNtwEng/'+ startDate + '/' + endDate,
		dataType: "json",
		success: function(data) {
            $.each( data, function( key, value ) {
            	network.events.push({
					imsi: data[key][0],
            		callfailure: data[key][1],
            	});

				imsiArray.push(data[key][0]);
				failureArray.push(data[key][1]);
   			});
 			drawTable(network.events);
			chartGraph();
		}
	});
}

var chartGraph=function(){

	new Chart("myChart", {
  		type: "horizontalBar",
  		data: {
    	labels: imsiArray,
    	datasets: [{
      	backgroundColor: barColors,
		label : ' Call Failures Count',
      	data: failureArray,
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
			      text: "Top 10 IMSI and Call Failures",
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
   	
$(document).on("click", "#btnSearch", function() {
		
	let myChart = document.getElementById('myChart').getContext('2d');

    document.getElementById("myChart").style.display = "block";

    // Global Options
    //Chart.defaults.global.defaultFontFamily = 'Arial';
    //Chart.defaults.global.defaultFontSize = 15;
    Chart.defaults.global.defaultFontColor = '#777';

	top_10_imsiFindByDates($('#start_date').val(), $('#end_date').val());
		clearTable();
		return false;
	});
	function drawTable(data){
	clearTable();
	$('#table_body')
              
    $.each(data, function(key,value){
		var record='';
            	  record += '<tr>';
            	  
            	  record += '<td>' + 
                      value.imsi + '</td>';
            	  
                  record += '<td>' + 
                     value.callfailure + '</td>';

                  
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