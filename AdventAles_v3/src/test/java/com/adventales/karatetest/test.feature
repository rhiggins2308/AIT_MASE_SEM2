Feature: Test Cart Load
  Test successful load of cart items



  Scenario: Cart items displayed on page load
    Given url 'http://localhost:8082/AdventAles_v3/rest/cart'
    When method GET
  	Then status 200
  
  Scenario: All calendar products displayed on store page load
    Given url 'http://localhost:8082/AdventAles_v3/rest/calendars'
    When method GET
  	Then status 200
  
  Scenario: Specific calendar details displayed on store page load
    Given url 'http://localhost:8082/AdventAles_v3/rest/calendars/1'
    When method GET
  	Then status 200