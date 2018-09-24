package com.endre.pokemon.util

import com.endre.pokemon.entity.PokemonDto
import com.endre.pokemon.service.PokemonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

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

        val pokemonDto = PokemonDto("5005", "Random Pokemon", "http://www.serebii.net/pokemongo/pokemon/147.png", 50, "5 km")

        pokemonService.createPokemon(pokemonDto)
    }

}