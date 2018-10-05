package com.endre.pokemon.service

import com.endre.pokemon.model.dto.PokemonDto
import com.endre.pokemon.model.WrappedResponse
import com.endre.pokemon.model.hal.PageDto
import org.springframework.http.ResponseEntity

/**
 * Created by Endre on 02.09.2018.
 * news
 * Verson:
 */
interface PokemonService{

    fun createPokemon(pokemonDto : PokemonDto) : ResponseEntity<WrappedResponse<PokemonDto>>

    fun batchCreatePokemon(pokemons: List<PokemonDto>): ResponseEntity<Void>

    fun findBy(name: String?, num: String?, type: String?, offset: Int, limit: Int): ResponseEntity<WrappedResponse<PageDto<PokemonDto>>>

    fun find(num: String?): ResponseEntity<WrappedResponse<PokemonDto>>

    fun update(num: String?, dto: PokemonDto): ResponseEntity<WrappedResponse<PokemonDto>>

    fun patch(num: String?, jsonBody: String): ResponseEntity<WrappedResponse<PokemonDto>>

    fun delete(num: String?): ResponseEntity<WrappedResponse<PokemonDto>>
}