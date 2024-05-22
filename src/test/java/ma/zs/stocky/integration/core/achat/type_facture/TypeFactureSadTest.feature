Feature: TypeFacture

  Background:
    * call read('karate-config.js')
    * call read('db_cleaner.js')
    * url typeFactureUrl
    * header Content-Type = 'application/json'

    * def postBody = read('TypeFactureSave.json')
    * def FindAllSchema = read('TypeFactureSchema.json')
    * def uuid = function() { return '' + java.util.UUID.randomUUID(); }
    * postBody.code = uuid()

  @duplicate
  Scenario Outline: POST TypeFacture Twice with same code - expect <responseCode> as response code
    * postBody.code = uniqueId
    * def count = db.readValue('select count(*) FROM item.type_facture')

    * path ''
    * header Authorization = 'Bearer ' + adminToken
    * request postBody
    * method POST
    * status <responseCode>
    * eval if(__num==1 && count != db.readValue('select count(*) FROM item.type_facture')) karate.fail("type_facture count values in DB are different")

    Examples:
      | responseCode |
      | 201          |
      | 226          |

  Scenario: Fail - GetByID Not Found

    * path 'id', 99999999
    * header Authorization = 'Bearer ' + adminToken
    * method GET
    * status 404
    * match response.length == 0



  Scenario: Fail - POST TypeFacture without Body

    * path ''
    * header Authorization = 'Bearer ' + adminToken
    * method POST
    * status 400
    * match response.error == "Bad Request"



  Scenario: Fail - POST TypeFacture without Authorization

    * path ''
    * header Authorization = 'Bearer unvalid'
    * request postBody
    * method POST
    * status 500



  Scenario: Fail - Save TypeFacture with method PATCH

    * path ''
    * header Authorization = 'Bearer ' + adminToken
    * method PATCH
    * status 405
    * match response.error == "Method Not Allowed"
