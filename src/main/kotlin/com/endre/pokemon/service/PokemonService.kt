package com.endre.pokemon.service

import com.endre.pokemon.entity.PokemonDto
import org.springframework.http.ResponseEntity

/**
 * Created by Endre on 02.09.2018.
 * news
 * Verson:
 */
interface PokemonService{

    fun createPokemon(pokemonDto : PokemonDto) : ResponseEntity<Long>

    fun batchCreatePokemon(pokemons: List<PokemonDto>): ResponseEntity<Void>

    fun findBy(name: String?): ResponseEntity<List<PokemonDto>>

    fun find(num: String?): ResponseEntity<PokemonDto>

    fun update(num: String?, dto: PokemonDto): ResponseEntity<Void>

    fun patch(num: String?, jsonBody: String): ResponseEntity<Void>

    fun delete(num: String?): ResponseEntity<Any>
}