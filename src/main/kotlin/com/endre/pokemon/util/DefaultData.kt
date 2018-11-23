package com.endre.pokemon.util

import com.endre.pokemon.model.dto.PokemonDto
import com.endre.pokemon.service.PokemonService
import com.google.gson.reflect.TypeToken
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import java.io.FileReader


/**
 * Created by Endre on 02.09.2018.
 * news
 * Verson:
 */

@Component
class DefaultData {


    @Autowired
    private lateinit var pokemonService: PokemonService

    @PostConstruct
    fun initializeDefault() {
        //val reader = JsonReader(FileReader("pokemons.json"))
        //val pokemons: List<PokemonDto> = Gson().fromJson(reader, object : TypeToken<List<PokemonDto>>() {}.type)



        val num = "1001"

        val prev = PokemonDto(num = "1000")
        val next = PokemonDto(num = "1002")

        val dto = PokemonDto(
                num = num,
                name = "Test",
                img = "url.com",
                candy_count = 100,
                egg = "5km",
                prev_evolution = mutableSetOf(prev),
                next_evolution = mutableSetOf(next),
                type = mutableSetOf("Grass"),
                weaknesses = mutableSetOf("Fire"))

        val pokemons = mutableListOf(dto)

        println("=== List from JSON ===")
        pokemons.forEach { println(it) }

        pokemonService.batchCreatePokemon(pokemons)


    }
}