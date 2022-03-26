var rootUrl ='rest/nmegraphicaltop10'; 
var network = { events: [] } ;
var xValues = [];
var yValues = []; 	
var xDetails = [];
var yDetails = []; 	
var barColors = ["red", "green","blue","orange","brown", "yellow", "aqua", "pink", "purple", "lavender",  "indigo", "lightgreen", "lightblue", "crimson", "turquoise", "gray", "cream", "violet"];
const ctx = document.getElementById('myChart');
//var myChart=[];

var getTopTen=function(){

	    $.ajax({
	
	        url : rootUrl ,
	        type : 'GET',
	        dataType:'json',
	        success : function(data) {              
	            //console.log('Data: '+JSON.stringify(data));
	            $.each( data, function( key, value ) {
	            	  network.events.push( {
							market: data[key][0],
							operator: data[key][1],
	            		    cellID: data[key][2],
	            		    numFailures: data[key][3]
	            		});
		  			xValues.push(data[key][0] + ", " + data[key][1] + ", " + data[key][2]  );
					yValues.push(data[key][3]);          		
	            	});
	       		displayGraph();
			}
			
						
		});
};


var displayGraph=function(){

	let myChart = 	new Chart("myChart", {
  		type: "horizontalBar",
  		data: {
    	labels: xValues,
    	datasets: [{
      	backgroundColor: barColors,
		label : 'Market, Operator, Cell ID',
      	data: yValues,
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
				text: "Market, Operator, Cell ID"
				        }       
      			},
    		title: {
			      display: true,
			      text: "Top 10 Failures",
        		  fontSize:25
		
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
        },
	    onClick: function (click, index) {
	        var activePoint = myChart.getElementAtEvent(click)[0];
    	    var data = activePoint._chart.data;
	        var datasetIndex = activePoint._datasetIndex;
    	    var label = data.datasets[datasetIndex].label;
        	var value = data.datasets[datasetIndex].data[activePoint._index];
        	e = index[0];
	        var x_value = this.data.labels[e._index];
			var firstValIndex =  x_value.indexOf("," );
			var country = x_value.substr(0,firstValIndex);
			var secondValIndex =  x_value.indexOf(",", firstValIndex+1);
			var numChar = secondValIndex - firstValIndex;
			numChar = numChar -2;
			var operator = x_value.substr(firstValIndex + 2, numChar);
			var cellID = x_value.substr(secondValIndex+ 2);
			populateDetails(country, operator, cellID);
		}

		}
	});
	
	
};

//DOM is loaded
$(document).ready(function(){    

    let myChart = document.getElementById('myChart').getContext('2d');

    // Global Options
    //Chart.defaults.global.defaultFontFamily = 'Arial';
    Chart.defaults.global.defaultFontSize = 15;
    Chart.defaults.global.defaultFontColor = '#777';

	getTopTen(); 
		
  	return false;
});

$(document).on("click", "#close", function(){
	$('#detailsModal').modal('hide');
	clearDetailGraph()
	
});



var populateDetails =function(country, operator, cellID){

	  $.ajax({
	        url : rootUrl + "/details/" + country+ "/" + operator + "/" + cellID,
	        type : 'GET',
	        dataType:'json',
	        success : function(data) {              
	            $.each( data, function( key, value ) {
		  			xDetails.push(data[key][0].substr(0,15) + ", " + data[key][1].substr(0,15) );
					yDetails.push(data[key][2]);          		
	            	});
				let myChartDetail = new Chart("myChartDetail", {
  					type: "pie",
  					data: {
	    				labels: xDetails,
	    				datasets: [{
    		  				backgroundColor: barColors,
							label : "Event and Call Failure details",
		      				data: yDetails,
    						}]
  						},
	    			options: {
    	  				title: {
        				display: true,
	        			text: 'Event and Call Failure Details '
	      					},
						plugins: {
					    labels: {
      						position: 'outside',
      						render: (args) => {
        					return `${args.label}: ${args.value}%`;
      								}
    						}
						}

	    			}
				});

			
				$('#detailsModal').find('.modal-title').text("Event and Call Failure Details for Market - " + country + ", Operator - " + operator + ", Cell ID - " + cellID);
				$('#detailsModal').modal('show');
			
			}			
			
		});
			
};

var clearDetailGraph =function(){

	xDetails = [];
	yDetails = [];
	
	/*
	var div = document.getElementById('myChartDetail');

    var div = document.getElementsByClassName('containerDetail');
    while(div.firstChild) {
			alert("Remove child");
               div.removeChild(div.firstChild);
  	}	
	$('#containerDetail').append('<canvas id="myChartDetail" ></canvas>');
	*/
	
	var div1 = document.getElementById('myChartDetail');
	
	if (div1 == null){
	} else {
		
		
		 let canvas = document.getElementById('myChartDetail');
      	 let context = canvas.getContext('2d');
	     context.clearRect(0, 0, canvas.width, canvas.height);


		// Below not working 
		//$('#myChartDetail').clear(); 
		//$('#myChartDetail').destroy(); 
		//$('#containerDetail').append('<canvas id="myChartDetail" ></canvas>');		
	}
	
	//document.getElementById("containerDetail").innerHTML = '&nbsp;';
	//document.getElementById("containerDetail").innerHTML = '<canvas id="myChartDetail"></canvas>';
	//var ctx = document.getElementById("containerDetail").getContext("2d");	
	
	//div.append('<canvas id="myChartDetail" style="width:100%;max-width:600px" ></canvas>');

}