package com.endre.pokemon.repository

import com.endre.pokemon.entity.Pokemon
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * Created by Endre on 02.09.2018.
 * news
 * Verson:
 */
@Repository
interface PokemonRepository : CrudRepository<Pokemon, Long> {

    fun findAllByName(country: String): Iterable<Pokemon>
}