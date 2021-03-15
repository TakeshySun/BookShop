
Feature: Desktop Checkout for Guest User
  As a customer
  I want to be able proceed to checkout
  So that I can specify my delivery and payment details and place the order

  @Selenide
  Scenario: Proceed to checkout, final review and place order as guest user
    Given open the "Initial home page"
    And search for "Thinking in Java"
    And Search results contain the following product "Thinking in Java"
    And I click Add to basket button for product with name "Thinking in Java"
    And I select BasketCheckout in basket pop-up
    And Basket order summary is following:
      | Delivery cost | Total   |
      | FREE          | 79,77 € |
