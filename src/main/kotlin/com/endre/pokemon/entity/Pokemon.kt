package com.endre.pokemon.entity


import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.Id
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size
import kotlin.math.max

/**
 * Created by Endre on 02.09.2018.
 * news
 * Verson:
 */
@Entity
class Pokemon(

        @get:NotBlank @get:Size(max = 128)
        var name: String,

        @get:NotBlank @get:Size(max = 2048)
        var img: String? = null,

        var candyCount: Int? = null,

        @get:NotNull
        var egg: String,

        @get:ElementCollection
        @get:NotNull
        var type: Set<String>? = setOf(),

        @get:ElementCollection
        @get:NotNull
        var weaknesses: Set<String>? = setOf(),

        @get:ElementCollection
        var prevEvolution: Set<String>? = setOf(),

        @get:ElementCollection
        var nextEvolution: Set<String>? = setOf(),

        @get:Id @get:NotNull
        var id: Long

        /*
            Note how we need to explicitly state that id can be null (eg when entity
            is not in sync with database).
            The "= null" is used to provide a default value if caller does not
            provide one.
         */
)