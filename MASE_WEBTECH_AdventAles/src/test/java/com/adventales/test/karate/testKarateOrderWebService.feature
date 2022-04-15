Feature: Order Web Service
  Test successful execution of all OrderWS endpoints

  Scenario: Make Order
    Given url 'http://localhost:8082/MASE_WEBTECH_AdventAles/rest/order/makeorder'
    And request { "orderShipped": "0", "userEmail": "karate@email.com", "orderTotal": "120.00", "address1": "Address 1", "address2": "Address 2", "town": "Town", "county": "County" }
    When method POST
  	Then status 201
  	
	Scenario: Get Order Total
    Given url 'http://localhost:8082/MASE_WEBTECH_AdventAles/rest/order'
    When method GET
    Then status 200
    
  Scenario: Get Open Orders
    Given url 'http://localhost:8082/MASE_WEBTECH_AdventAles/rest/order/getopenorders'
    When method GET
    Then status 200
    
   
  Scenario: Ship Order
    Given url 'http://localhost:8082/MASE_WEBTECH_AdventAles/rest/order/shiporder/1'
    And request { "orderShipped": "1", "userEmail": "karate@email.com", "orderTotal": "120.00", "address1": "Address 1", "address2": "Address 2", "town": "Town", "county": "County" }
    When method PUT
    Then status 200