package com.endre.pokemon.util

import com.endre.pokemon.entity.PokemonDto
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
        val reader = JsonReader(FileReader("pokemons.json"))
        val pokemons: List<PokemonDto> = Gson().fromJson(reader, object : TypeToken<List<PokemonDto>>() {}.type)

        println("=== List from JSON ===")
        pokemons.forEach { println(it) }

        pokemonService.batchCreatePokemon(pokemons)


    }
}