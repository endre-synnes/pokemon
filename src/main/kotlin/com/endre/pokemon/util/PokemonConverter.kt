package com.endre.pokemon.util

import com.endre.pokemon.entity.Pokemon
import com.endre.pokemon.entity.PokemonDto
import com.endre.pokemon.entity.SimplePokemonDto

/**
 * Created by Endre on 02.09.2018.
 * news
 * Verson:
 */
class PokemonConverter {

    companion object {

        fun convertFromDto(pokemonDto: PokemonDto) : Pokemon {
            if (pokemonDto.id.isNullOrBlank()){
                throw IllegalStateException("Missing id from DTO")
            }

            val id = pokemonDto.id!!.toLong()

            return Pokemon(pokemonDto.name!!, pokemonDto.img!!, pokemonDto.candy_count,
                    pokemonDto.egg!!, pokemonDto.type, pokemonDto.weaknesses,
                    convertFromSimplePokemonDto(pokemonDto.prev_evolution),
                    convertFromSimplePokemonDto(pokemonDto.next_evolution),
                    id
            )
        }


        fun convertFromDto(pokemon: Iterable<PokemonDto>): List<Pokemon> {
            return pokemon.map { convertFromDto(it) }
        }


        fun convertToDto(pokemon: Pokemon): PokemonDto {
            return PokemonDto(
                    pokemon.id.toString(),
                    pokemon.name,
                    pokemon.img,
                    pokemon.candyCount,
                    pokemon.egg,
                    pokemon.type,
                    pokemon.weaknesses,
                    convertToSimplePokemonDto(pokemon.prevEvolution),
                    convertToSimplePokemonDto(pokemon.nextEvolution)
                    )
        }

        fun convertToDto(pokemon: Iterable<Pokemon>): List<PokemonDto> {
            return pokemon.map { convertToDto(it) }
        }

        private fun convertToSimplePokemonDto(pokemonNums: Set<String>?) : Set<SimplePokemonDto>?{
            return if (pokemonNums != null) {
                if (pokemonNums.isNotEmpty()) {
                    pokemonNums.asSequence().map { SimplePokemonDto(it, null) }.toSet()
                } else null
            }else {
                null
            }
        }

        private fun convertFromSimplePokemonDto(simpleDtos: Set<SimplePokemonDto>?): Set<String>? {
            return if (simpleDtos != null) {
                if (simpleDtos.isNotEmpty()) {
                    simpleDtos.asSequence().map { it.num!! }.toSet()
                } else null
            } else {
                null
            }
        }
    }
}