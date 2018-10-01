package com.endre.pokemon.entity

import com.endre.pokemon.entity.hal.PageDto

class PokemonResponses (
        code: Int? = null,
        data: PageDto<PokemonDto>? = null,
        message: String? = null,
        status: ResponseStatus? = null

) : WrappedResponse<PageDto<PokemonDto>>(code, data, message, status)