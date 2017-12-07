Feature: A client could add products to the shopping basket.

  Background:
    Given A browser is open
    And Url is loaded
  Scenario Outline: Logged in user can add 2 elements to shopping basket
    Given Log in with user: "<user>" and password: "<password>"
    And Shopping cart is empty
    Then Go to main page
    And Select "<product>" and brand "<brand>"
    And Click first product
    And Select size and Add to cart
    And Go to main page
    And Select "<product>" and brand "<brand>"
    And Click first product
    And Select size and Add to cart
    Then Go to shopping cart and check that number of items are 2
    Examples:
      | user                  | password        | product | brand   |
      | ferranferri@gmail.com | ferranferri1234 | Zapatos | Reebok  |



