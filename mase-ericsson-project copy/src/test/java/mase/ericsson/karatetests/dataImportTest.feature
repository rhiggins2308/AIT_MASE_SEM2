Feature: Data Import
  I want test for successful data import

  Scenario: data imported successfully
    Given url 'http://localhost:8082/mase-group-project/rest/database/populate'
    And method GET
    Then status 201