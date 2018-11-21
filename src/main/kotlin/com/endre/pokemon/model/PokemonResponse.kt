package com.endre.pokemon.model

import com.endre.pokemon.model.dto.PokemonDto
import com.endre.pokemon.model.hal.PageDto

class PokemonResponse(
        code: Int? = null,
        data: PageDto<PokemonDto>? = null,
        message: String? = null,
        status: ResponseStatus? = null

) : WrappedResponse<PageDto<PokemonDto>>(code, data, message, status)