package com.endre.pokemon.model.dto

import io.swagger.annotations.ApiModelProperty

data class SimplePokemonDto(
        @ApiModelProperty("The number of the Pokemon")
        var num: String? = null

//        @ApiModelProperty("The name of the Pokemon")
//        var name: String? = null
)