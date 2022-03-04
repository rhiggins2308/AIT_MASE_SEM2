Feature: Login Feature
	As a user I want to login so that I can access my account

	Scenario: User can login with corrrect username and password
		Given user is on the login page
		When user enters correct username
		And user enters correct password
		And user clicks the login button
		Then user get login confirmation
		
	Scenario: User cannot login with corrrect username and incorrect password
		Given user is on the login page
		When user enters correct username
		And user enters incorrect password
		And user clicks the login button
		Then user gets invalid credentials warning
		
	Scenario Outline: User can login with corrrect username and password
		Given user is on the login page
		When user enters username "<username>"
		And user enters  password "<password>"
		And user clicks the login button
		Then user get "<welcome>" message
		
	Examples:
		|username | password | welcome |
		|mase@gmail.com | 12345| Joe Bloggs|
		