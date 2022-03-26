Feature: Customer Service Rep Level Queries
  Test successful execution of queries accessible to all support users types


  Scenario: Network Event Failures By IMSI
    Given url 'http://139.59.165.255:8084/mase-group-project/rest/networkevents/allimsi'
    And header Accept = 'application/json'
  	When method GET
  	Then status 200

  
  Scenario: Unique Network Failure Events by IMSI
    Given url 'http://139.59.165.255:8084/mase-group-project/rest/networkevents/191911000456426'
  	And header Accept = 'application/json'
  	When method GET
  	Then status 200
  
  
  Scenario: Count of Network Event Failures By IMSI for Date Range
    Given url 'http://139.59.165.255:8084/mase-group-project/rest/networkevents/imsiCountForServiceRep/191911000456426/2020-01-01T14:00/2022-01-01T14:00'
  	And header Accept = 'application/json'
  	When method GET
  	Then status 200
  
  
  Scenario: Network Event Failure Causes per IMSI
    Given url 'http://139.59.165.255:8084/mase-group-project/rest/networkevents/causecode/191911000423586'
    And header Accept = 'application/json'
  	When method GET
  	Then status 200