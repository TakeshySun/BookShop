Feature: Verify API


  @API
  Scenario: Create API call and verify Web
    Given create a cart
    And add product in cart
    Then open cart in web and verify product
