# Pokemon REST API

This is a Pokemon REST API written in Kotlin. I make use of technologies like SpringBoot, 
Spring Security, Crud Repository, etc.

<!--- Travis CI build status banner -->
[![Build Status](https://travis-ci.org/synend16/pokemon.svg?branch=master)](https://travis-ci.org/synend16/pokemon)

## Supported requests ##

### Pokemons ###
 - GET
 - GET /{id}
 - POST
 - PATCH /{id}
 - DELETE /{id}


## How to test/run the application ##
1. Either clone og download the ZIP file.
2. Open folder and run: `mvn clean install`.
3. Start the Spring application.
4. Go to this url in your web browser: http://localhost:8080/api/swagger-ui.html#/
5. Test the different requests presentet on this page.
