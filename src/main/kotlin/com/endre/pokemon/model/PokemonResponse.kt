package com.endre.pokemon.model

import com.endre.pokemon.model.dto.PokemonDto

class PokemonResponse(
        code: Int? = null,
        data: PokemonDto? = null,
        message: String? = null,
        status: ResponseStatus? = null

) : WrappedResponse<PokemonDto>(code, data, message, status)