Feature: View Category
	As a logged in customer
	I want to view a category of clothing
	So that I can decide what to purchase
	
	Scenario: the user should be able to view the T-shirt category
		Given user is successfully logged in
		When user selects tshirts tab
		Then user gets tshirts displayed
	#	And user gets there is one product displayed
		