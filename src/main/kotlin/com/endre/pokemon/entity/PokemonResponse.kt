package com.endre.pokemon.entity

class PokemonResponse(
        code: Int? = null,
        data: PokemonDto? = null,
        message: String? = null,
        status: ResponseStatus? = null

) : WrappedResponse<PokemonDto>(code, data, message, status)