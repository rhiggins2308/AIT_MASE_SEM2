var rootUrl ="rest/nmetop10/";
var list;
var xValues = [];
var yValues = []; 	
var barColors = ["red", "green","blue","orange","brown", "yellow", "aqua", "pink", "purple", "lavender",  "indigo", "lightgreen", "lightblue", "crimson", "turquoise", "gray", "cream", "violet"];

function top10FailuresByDate(startDate, endDate) {
	if (startDate > endDate) {
		$('#dateErrorModal').modal('show');	
	} else {
		$.ajax({
			type: 'GET',
			url: rootUrl + startDate + "/" + endDate,
			dataType: "json",
			success: function(data) {
				if (!$.trim(data)) {
					$('#noDataModal').modal('show');	
				} else {
					$.each(data, function(key, value) {
						var record = "<tr>"
										+ "<td>" + data[key][0] + "</td>"
										+ "<td>" + data[key][1] + "</td>"
										+ "<td>" + data[key][2] + "</td>"
										+ "<td>" + data[key][3] + "</td>"
									+ "</tr>";
						
						$('#table_body').append(record);
						
						xValues.push(data[key][0] + ", " + data[key][1] + ", " + data[key][2]  );
						yValues.push(data[key][3]);          		

					}); 
	       		displayGraph();
				}
	        },
	        error : function(request,error)
	        {
	            alert("Request: "+JSON.stringify(request));
	        }
		});
	}
}
  	
$(document).on("click", "#btnSearch", function() {
    let myChart = document.getElementById('myChart').getContext('2d');

    document.getElementById("myChart").style.display = "block";
    // Global Options
    //Chart.defaults.global.defaultFontFamily = 'Arial';
    //Chart.defaults.global.defaultFontSize = 15;
    Chart.defaults.global.defaultFontColor = '#777';
	
	
	top10FailuresByDate($('#start_date').val(), $('#end_date').val());
});

$(document).on("click", "#btnRefresh", function() {
	var div = document.getElementById('table_body');
    while(div.firstChild) {
               div.removeChild(div.firstChild);
  	}
});



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
				 fontSize : 20
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
		}

		}
	});
	
	
};
