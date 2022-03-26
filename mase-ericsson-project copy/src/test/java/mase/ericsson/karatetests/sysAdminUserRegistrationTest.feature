Feature: Login
  Various User Types should be able to loginn and redirect to the appropriate dashboard


Scenario Outline: User Registration
	Given url 'http://localhost:8082/mase-group-project/rest/users/register'
	And request {"userType": <userType>, "firstName": <firstName>, "lastName": <lastName>, "password": <password>}	
	When method POST
  Then status <status>

    Examples: 
      | name        | userType	| firstName | lastName 	| password | status |
      | ServiceRep1 | 1 				| test			| Service  	| password | 201    |
      | SupportEng1 | 2 				|	test		 	|	Support		| password | 201    |
			| NetworkEng1 | 3					|	test		 	|	Network		| password | 201    |
			

Scenario: List all Existing Users for System Administrator
	Given url 'http://139.59.165.255:8084/mase-group-project/rest/users/allUsers'
	And header Accept = 'application/json'
  When method GET
  Then status 200