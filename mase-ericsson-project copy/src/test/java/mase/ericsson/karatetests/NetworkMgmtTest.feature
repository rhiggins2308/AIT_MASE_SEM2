Feature: Network Mgmt Level Queries
  Test successful execution of queries accessible only to Network Mgmt Engineers


	Scenario: Retrieve Full list of IMSI
  	Given url 'http://139.59.165.255:8084/mase-group-project/rest/networkevents/findAllImsiForNetwork'
  	And header Accept = 'application/json'
  	When method GET
  	Then status 200
 
 
 Scenario: Count Failures/Duration by IMSI for Period
  	Given url 'http://139.59.165.255:8084/mase-group-project/rest/networkevents/imsireport/191911000456426/2020-01-01T14:00/2022-01-01T14:00'
  	And header Accept = 'application/json'
  	When method GET
  	Then status 200
 
  
	Scenario: Top Ten Failure Frequency By Group for Period
  	Given url 'http://139.59.165.255:8084/mase-group-project/rest/nmetop10/2020-01-01T14:00/2022-01-01T14:00'
  	And header Accept = 'application/json'
  	When method GET
  	Then status 200
  
  
  Scenario: Retrieve list of phone models for display
    Given url 'http://139.59.165.255:8084/mase-group-project/rest/model'
  	And header Accept = 'application/json'
  	When method GET
  	Then status 200
  
  
  Scenario: Retrieve failure data by phone model
    Given url 'http://139.59.165.255:8084/mase-group-project/rest/model/G410'
  	And header Accept = 'application/json'
  	When method GET
  	Then status 200
  	
  	
  Scenario: Retrieve Top 10 IMSI with Failures for Period
    Given url 'http://139.59.165.255:8084/mase-group-project/rest/networkevents/top10_imsiCountforNtwEng/2020-01-01T14:00/2022-01-01T14:00'
  	And header Accept = 'application/json'
  	When method GET
  	Then status 200	