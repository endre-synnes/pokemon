package com.endre.pokemon.repository

import com.endre.pokemon.model.entity.Pokemon
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

/**
 * Created by Endre on 02.09.2018.
 * news
 * Verson:
 */
@Repository
interface PokemonRepository : CrudRepository<Pokemon, Long> {

    fun findAllByName(name: String): Iterable<Pokemon>

    fun findByNum(num: String): Iterable<Pokemon>

    @Query("select p from Pokemon p WHERE :type in elements(p.type)")
    fun findAllByType(@Param("type") type: String) : Iterable<Pokemon>

}