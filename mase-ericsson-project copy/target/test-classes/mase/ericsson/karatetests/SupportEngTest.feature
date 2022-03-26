Feature: Support Engineer Level Queries
  Test successful execution of queries accessible to all Network Mgmt and Support Engineers
	
	
	Scenario: Retrieve List of Failing IMSI for Period
		Given url 'http://139.59.165.255:8084/mase-group-project/rest/networkevents/queryimsi/2020-01-01T14:00/2022-01-01T14:00'
  	And header Accept = 'application/json'
  	When method GET
  	Then status 200
  	
  	
  Scenario: Failures By Phone Model for Period
		Given url 'http://139.59.165.255:8084/mase-group-project/rest/networkevents/UEreport/33002235/2020-01-01T14:00/2022-01-01T14:00'
  	And header Accept = 'application/json'
  	When method GET
  	Then status 200
  	
  
  Scenario: Retrieve List of Failure Class
		Given url 'http://139.59.165.255:8084/mase-group-project/rest/networkevents/allfailureclass'
  	And header Accept = 'application/json'
  	When method GET
  	Then status 200
  	
  	
  Scenario: Retrieve List of Failing IMSI by Failure Class
		Given url 'http://139.59.165.255:8084/mase-group-project/rest/networkevents/failureclass/0'
  	And header Accept = 'application/json'
  	When method GET
  	Then status 200  	