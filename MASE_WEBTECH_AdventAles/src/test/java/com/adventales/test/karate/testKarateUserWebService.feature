Feature: User Web Service
  Test successful execution of all UserWS endpoints

  Scenario Outline: User Login
    Given url 'http://localhost:8082/MASE_WEBTECH_AdventAles/rest/users/login/<userEmail>/<userPassword>'
    When method GET
  	Then status <status>
  	
  	Examples:
			| userEmail        		| userPassword 	| status |
		  | customer@email.com 	| password 			| 200    |
		  | customer@email.com	| wrongpassword | 403    |
			| staff@email.com 		| password 			| 200    |
		  | staff@email.com 		| wrongpassword | 403    |   

	
	Scenario: Register User
    Given url 'http://localhost:8082/MASE_WEBTECH_AdventAles/rest/users/register'
    And request { "firstName": "Test", "lastName": "Staff", "userEmail": "teststaff@email.com", "dob": "01012000", "pword": "password","userType": "2" }
		When method POST
    Then status 201