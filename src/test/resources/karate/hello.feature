Feature: Testing Rest API with Karate

  Scenario: Testing valid GET endpoint
    Given url 'http://localhost:8989/hello/Amsidh'
    When method GET
    Then status 200
    And match $ == 'Hello! Amsidh, How are you?'
    Then print 'Get method called successfully'

  Scenario: Testing GET endpoint with bad request
    Given url 'http://localhost:8989/hello/bad'
    When method GET
    Then status 200
    And match $ == 'Custom Bad request exception thrown'
    Then print 'Get method called successfully'

  Scenario: Testing GET endpoint with not found
    Given url 'http://localhost:8989/hello/notfound'
    When method GET
    Then status 200
    And match $ == 'Custom NotFound request exception thrown'
    Then print 'Get method called successfully'

  Scenario: Testing GET endpoint with not found
    Given url 'http://localhost:8989/hello/forbidden'
    When method GET
    Then status 200
    And match $ == 'To show an example of a custom message'
    Then print 'Get method called successfully'

  Scenario: Testing GET endpoint with not found
    Given url 'http://localhost:8989/dummy'
    When method GET
    Then status 404
    Then print 'Get method called with not found endpoint'
