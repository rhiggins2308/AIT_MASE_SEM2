Feature: Calendar Web Service
  Test successful execution of all CalendarWS endpoints

  Scenario: Find All Calendars
    Given url 'http://localhost:8082/MASE_WEBTECH_AdventAles/rest/calendars'
    When method GET
  	Then status 200
  	
	Scenario: Find Calendar By Id
    Given url 'http://localhost:8082/MASE_WEBTECH_AdventAles/rest/calendars/1'
    When method GET
    Then status 200