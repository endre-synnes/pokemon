package com.endre.pokemon.model.dto

import io.swagger.annotations.ApiModelProperty

/**
 * Created by Endre on 02.09.2018.
 * news
 * Verson:
 */
data class PokemonDto(

        @ApiModelProperty("The id of the Pokemon")
        var id: String? = null,

        @ApiModelProperty("The number of the Pokemon")
        var num: String? = null,

        @ApiModelProperty("The name of the Pokemon")
        var name: String? = null,

        @ApiModelProperty("The img of the Pokemon")
        var img: String? = null,

        @ApiModelProperty("The number of candy needed to evolve")
        var candy_count: Int? = null,

        @ApiModelProperty("Distance needed to walk to earn one candy")
        var egg: String? = null,

        var type: Set<String>? = null,

        var weaknesses: Set<String>? = null,

        var prev_evolution: Set<SimplePokemonDto>? = null,

        var next_evolution: Set<SimplePokemonDto>? = null
)