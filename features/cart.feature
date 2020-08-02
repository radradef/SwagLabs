@cartFeature
Feature: Cart

  Background:
    Given the user is on the Product page

    Scenario: Add to Cart
      When Eva adds item to her cart
      And she navigates to the cart page
      Then her item should appear in her cart