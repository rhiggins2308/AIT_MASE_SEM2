var rootUrl="rest/graphmodel/";
var eventIDArray=[];
var causeCodeArray=[];
var descriptionArray=[];
var occurenceArray=[];
var modelVal =$("#model-dropdown").val();

const dataArray=[];
//var barColors = ["aqua", "gray", "purple", "lavender","red", "green","blue","orange","brown", "yellow", "aqua", "gray", "purple", "lavender","red", "green","blue","orange","brown", "yellow"];
var barColors = ["red", "green","blue","orange","brown", "yellow", "aqua", "pink", "purple", "lavender",  "indigo", "lightgreen", "lightblue", "crimson", "turquoise", "gray", "cream", "violet"];

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



function searchByModel(){
	modelVal =$("#model-dropdown").val();

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
						description : data[key][2],
						numOccurences: data[key][3]					
            		});
				eventIDArray.push(data[key][0]);
				causeCodeArray.push(data[key][1]);
				occurenceArray.push(data[key][3]);
				descriptionArray.push(data[key][2].substr(0,25));
   			});
			drawTable(network.events);
			chartGraph();
		}
	});
}

$(document).on('click','#btnSearch',function() {
	let myChart = document.getElementById('myChart').getContext('2d');

    document.getElementById("myChart").style.display = "block";

    //Chart.defaults.global.defaultFontFamily = 'Arial';
    //Chart.defaults.global.defaultFontSize = 15;
    Chart.defaults.global.defaultFontColor = '#777';
	
	searchByModel();
	clearTable();
	return false;
});

var chartGraph=function(){

	modelVal =$("#model-dropdown").val();

	new Chart("myChart", {
  		type: "horizontalBar",
  		data: {
    	labels: descriptionArray,
    	datasets: [{
      	backgroundColor: barColors,
		label : 'Description of Event and Cause code',
      	data: occurenceArray,
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
			      text: "Event cause Details for Model " + modelVal,
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
	console.log(data.length);
	console.log(data);
	clearTable();
	$('#table_body')
              
              $.each(data, function(key,value){
	var record='';
            	  record += '<tr>';
            	  
            	  record += '<td>' + 
                      value.eventId + '</td>';
            	  
                  record += '<td>' + 
                     value.causeCode + '</td>';

                  record += '<td>' + 
                     value.numOccurences+ '</td>';
                   record += '<td>' + 
                     value.description+ '</td>';

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
}

$(document).ready(function(){    
	displayAllModel();
});