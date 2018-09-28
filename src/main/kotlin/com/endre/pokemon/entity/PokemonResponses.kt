package com.endre.pokemon.entity

class PokemonResponses (
        code: Int? = null,
        data: List<PokemonDto>? = null,
        message: String? = null,
        status: ResponseStatus? = null

) : WrappedResponse<List<PokemonDto>>(code, data, message, status)