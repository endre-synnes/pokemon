package com.endre.pokemon.util

import com.endre.pokemon.entity.Pokemon
import com.endre.pokemon.entity.PokemonDto

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

            return Pokemon(pokemonDto.name!!, pokemonDto.img!!, pokemonDto.candy_count, pokemonDto.egg!!, id)
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
                    pokemon.egg)
        }

        fun convertToDto(pokemon: Iterable<Pokemon>): List<PokemonDto> {
            return pokemon.map { convertToDto(it) }
        }
    }
}