Feature: Register a user
	As an admin, when I fill in all the fields to register a user and 
	click register, then the user is registered. 

	Scenario: The User should be able to fill in the fields
		Given user is logged in and navigates to registration page
		When clicks on the different fields and fills in the details and clicks register user
		Then the alert should show the success

