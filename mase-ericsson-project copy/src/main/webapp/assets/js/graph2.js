      var barOptions_stacked = {
    		    tooltips: {
    		        enabled: true
    		    },
    		    hover :{
    		        animationDuration:0
    		    },
    		    scales: {
    		        xAxes: [{
    		            ticks: {
    		                beginAtZero:true,
    		                fontFamily: "'Open Sans Bold', sans-serif",
    		                fontSize:11
    		            },
    		            scaleLabel:{
    		                display:false
    		            },
    		            gridLines: {
    		            }, 
    		            stacked: true
    		        }],
    		        yAxes: [{
    		            gridLines: {
    		                display:false,
    		                color: "#fff",
    		                zeroLineColor: "#808080",
    		                zeroLineWidth: 100
    		            },
    		            ticks: {
    		                fontFamily: "'Open Sans Bold', sans-serif",
    		                fontSize:11
    		            },
    		            stacked: true
    		        }]
    		    },
    		    legend:{
    		        display:false
    		    },
    		    
    		    animation: {
    		        onComplete: function () {
    		            var chartInstance = this.chart;
    		            var ctx = chartInstance.ctx;
    		            ctx.textAlign = "centre";
    		            ctx.font = "9px Open Sans";
    		            ctx.fillStyle = "#fff";

    		            Chart.helpers.each(this.data.datasets.forEach(function (dataset, i) {
    		                var meta = chartInstance.controller.getDatasetMeta(i);
    		                Chart.helpers.each(meta.data.forEach(function (bar, index) {
    		                    data = dataset.data[index];
    		                    if(i==0){
    		                        ctx.fillText(data, 50, bar._model.y+4);
    		                    } else {
    		                        ctx.fillText(data, bar._model.x-25, bar._model.y+4);
    		                    }
    		                }),this)
    		            }),this);
    		        }
    		    },
    		    pointLabelFontFamily : "Quadon Extra Bold",
    		    scaleFontFamily : "Quadon Extra Bold",
    		};

    		var ctx = document.getElementById("Chart1");
    		var myChart = new Chart(ctx, {
    		    type: 'horizontalBar',
    		    data: {
    		        labels: ["Japan-NTT DoCoMo", "Australia-Telstra", "India-Reliance Infocomm-IN", "Antigua and Barbuda-AT&T Wireless-Antigua AG","United States of America-AT&T Mobility", "Sweden-Banverket SE",],
    		        
    		        datasets: [{
    		        	label: 'CellID:1',
        		        data: [2951,3239,1217,0,0,0],
        		        backgroundColor: "rgba(255, 159, 64, 1)",
    		            hoverBackgroundColor: "rgba(255, 159, 64, 1)"
    		        },{
    		        	label: 'CellID:2',
    		            data: [3494,2652,1154,0,0,0],
    		            backgroundColor: "rgba(255, 99, 132, 1)",
    		            hoverBackgroundColor: "rgba(255, 99, 132, 1)"
    		        },{
    		        	label: 'CellID:3',
    		            data: [3164,3599,925,0,0,0],
    		            backgroundColor: "rgba(54, 162, 235, 1)",
    		            hoverBackgroundColor: "rgba(54, 162, 235, 1)"
    		        },
    		        {
    		        	label: 'CellID:4',
    		            data: [3412,3104,1217,175,135,135],
    		            backgroundColor: "rgba(255, 206, 86, 1)",
    		            hoverBackgroundColor: "rgba(255, 206, 86, 1)"
    		        },
    		        {
    		        	label: 'CellID:5',
    		            data: [0,0,0,120,90,90],
    		            backgroundColor: "rgba(75, 192, 192, 1)",
    		            hoverBackgroundColor: "rgba(75, 192, 192, 1)"
    		        },
    		        {
    		        	label: 'CellID:3842',
    		            data: [0,0,0,100,75,75],
    		            backgroundColor: "rgba(153, 102, 255, 1)",
    		            hoverBackgroundColor: "rgba(153, 102, 255, 1)"
    		        }]
    		    },

    		    options: barOptions_stacked,
    		});