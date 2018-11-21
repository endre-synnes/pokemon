package com.endre.pokemon.api

import com.endre.pokemon.PokemonApplication
import com.endre.pokemon.PokemonApplicationTests
import com.endre.pokemon.model.PokemonResponse
import com.endre.pokemon.model.dto.PokemonDto
import com.endre.pokemon.model.hal.PageDto
import io.restassured.RestAssured
import io.restassured.RestAssured.*
import io.restassured.http.ContentType
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [(PokemonApplication::class)],
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class TestSetup {

    @LocalServerPort
    protected var port = 0

    @Before
    @After
    fun clean(){
        baseURI = "http://localhost"
        RestAssured.port = port
        basePath = "/newsrest/api/news"
        enableLoggingOfRequestAndResponseIfValidationFails()

        /*
           Here, we read each resource (GET), and then delete them
           one by one (DELETE)
         */
        val list = given().accept(ContentType.JSON).get()
                .then()
                .statusCode(200)
                .extract()
                .`as`(PokemonResponse::class.java)


        /*
            Code 204: "No Content". The server has successfully processed the request,
            but the return HTTP response will have no body.
         */
//        list.stream().forEach {
//            given().pathParam("id", it.newsId)
//                    .delete("/{id}")
//                    .then()
//                    .statusCode(204)
//        }
//
//        given().get()
//                .then()
//                .statusCode(200)
//                .body("size()", equalTo(0))
    }


}