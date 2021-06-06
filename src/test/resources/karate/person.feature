Feature: Testing Person Controller Rest Endpoints
  Background:
    * url 'http://localhost:8989'

  Scenario: Http GET method for getting person by personId
    When url 'http://localhost:8989/persons/1'
    And method GET
    Then status 200
    And match $ contains {id:"#notnull", name:"Amsidh Lokhande", email:"amsidh@gmail.com"}

  Scenario: Http GET method for not found person by personId
    When url 'http://localhost:8989/persons/10'
    And method GET
    Then status 404
    And match $ contains 'Person with personId 10 not found.'


  Scenario: Http GET method for getting all persons
    When url 'http://localhost:8989/persons'
    And method GET
    Then status 200

  Scenario: Http Post method to save the person
    When url 'http://localhost:8989/persons'
    And request {"id":"5","name":"Suresh Rupnar","email":"suresh@gmail.com"}
    And method POST
    Then status 201
    And match $ contains {id:"5",name:"Suresh Rupnar",email:"suresh@gmail.com"}

  Scenario: Http Post method to save the person
    When url 'http://localhost:8989/persons'
    And request {"id":"6","name":"Viru Lokhande","email":"viru@gmail.com"}
    And method POST
    Then status 201
    And match $ contains {id:"6",name:"Viru Lokhande",email:"viru@gmail.com"}


  Scenario: Http PUT method to update the person
    When url 'http://localhost:8989/persons/2'
    And request {"name":"Viru Babasha Lokhande","email":"virulokhande@gmail.com"}
    And method PUT
    Then status 200
    And match $ contains {id:"2",name:"Viru Babasha Lokhande",email:"virulokhande@gmail.com"}
