Feature: Login
  Various User Types should be able to loginn and redirect to the appropriate dashboard
	
	Scenario Outline: User login
		Given url 'http://139.59.165.255:8084/mase-group-project/rest/users/login/<userId>/<password>'
  	And header Accept = 'application/json'
  	When method GET
  	Then status <status>

    Examples: 
      | name        | userId | password | status |
      | ServiceRep1 | 1      | password | 201    |
      | ServiceRep2 | 1      | passwor  | 400    |
			| SupportEng1 | 2      | password | 201    |
      | SupportEng2 | 2      | passwor  | 400    |      
			| NetworkEng1 | 3      | password | 201    |
      | NetworkEng2 | 3      | passwor  | 400    |  
      | sysAdmin1   | 4      | password | 201    |
      | sysAdmin2   | 4      | passwor  | 400    |