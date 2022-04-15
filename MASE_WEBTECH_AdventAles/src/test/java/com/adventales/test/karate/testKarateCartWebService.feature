Feature: Cart Web Service
  Test successful execution of all CartWS endpoints

  Scenario: Add Item To Cart
    Given url 'http://localhost:8082/MASE_WEBTECH_AdventAles/rest/cart/add'
    And request { "calId": "1", "calType": "stout", "calSize": "12", "cost": "29.99", "image": "anyimage.jpg" }
    When method POST
  	Then status 201
  	
	Scenario: Get All Cart Items
    Given url 'http://localhost:8082/MASE_WEBTECH_AdventAles/rest/cart/getall'
    When method GET
    Then status 200
    
  Scenario: Clear Cart Items
    Given url 'http://localhost:8082/MASE_WEBTECH_AdventAles/rest/cart/clearcart'
    When method DELETE
    Then status 204