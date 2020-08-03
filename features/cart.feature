@cartFeature
Feature: Cart

  Background:
    Given the user is on the Product page

    Scenario: Cart is initially empty
      When Eva navigates to the cart page
      Then the cart should be empty

    Scenario: Add Item to Cart
      When Eva adds item to her cart
      And she navigates to the cart page
      Then her item should appear in her cart

      Scenario: Remove Item from Cart
        When Mario adds item to his cart
        And he navigates to the cart page
        And he removes the item from the cart
        Then the cart should be empty