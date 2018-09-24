package com.endre.pokemon.controller

import com.endre.pokemon.entity.PokemonDto
import com.endre.pokemon.service.PokemonService
import io.swagger.annotations.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.lang.Deprecated


/**
 * Created by Endre on 02.09.2018.
 * news
 * Verson:
 */

const val BASE_JSON = "application/json;charset=UTF-8"

@Api(value = "/pokemon", description = "Handling of creating and retrieving pokemon's")
@RequestMapping(
        path = ["/pokemon"], // when the url is "<base>/news", then this class will be used to handle it
        produces = [BASE_JSON]
)
@RestController
class ArticleController {


    @Value("\${server.servlet.context-path}")
    private lateinit var contextPath : String

    @Autowired
    private lateinit var pokemonService: PokemonService


    @ApiOperation("Get all pokemon's")
    @GetMapping
    fun getAll(@ApiParam("Name of the Pokemon")
            @RequestParam("name", required = false)
            name : String?): ResponseEntity<List<PokemonDto>> {
        return pokemonService.findBy(name)
    }


    @ApiOperation("Create new Pokemon")
    @PostMapping
    fun post(@RequestBody pokemonDto: PokemonDto): ResponseEntity<Long> {
        return pokemonService.createPokemon(pokemonDto)
    }

    @ApiOperation("Get single pokemon by the Pokedex number")
    @GetMapping(path = ["/{id}"])
    fun get(@ApiParam("The pokedex number of the pokemon")
            @PathVariable("id")
            num: String?) : ResponseEntity<PokemonDto> {
        return pokemonService.find(num)
    }

    @ApiOperation("Update the whole pokemon with new information")
    @PutMapping(path = ["/{id}"])
    fun update(@ApiParam("The pokedex number of the pokemon")
               @PathVariable("id")
               num: String?,
               @ApiParam("Pokemon data")
               @RequestBody
               pokemonDto: PokemonDto): ResponseEntity<Void> {
        return pokemonService.update(num, pokemonDto)
    }

    @ApiOperation("Update part of the pokemon's data")
    @PatchMapping(path = ["/{id}"])
    fun patch(@ApiParam("The pokedex number of the pokemon")
            @PathVariable("id")
            num: String?,
            @ApiParam("The partial patch")
            @RequestBody
            jsonPatch: String) : ResponseEntity<Void> {
        return pokemonService.patch(num, jsonPatch)
    }

    @ApiOperation("Delete a Pokemon by pokedex number")
    @DeleteMapping(path = ["/{id}"])
    fun delete(@ApiParam("id")
                @PathVariable("id")
                num: String?) : ResponseEntity<Any> {
        return pokemonService.delete(num)
    }

    @ApiOperation("Get single pokemon by pokedex number")
    @ApiResponses(ApiResponse(code = 301, message = "Deprecated URI, moved permanently."))
    @GetMapping(path = ["/id/{id}"])
    @Deprecated
    fun deprecatedFindById(
            @ApiParam("Id of a Pokemon")
            @PathVariable("id")
            num: String?) : ResponseEntity<PokemonDto> {
        return ResponseEntity.status(301)
                .location(UriComponentsBuilder.fromUriString("$contextPath/pokemon/$num")
                        .build()
                        .toUri())
                .build()
    }
}