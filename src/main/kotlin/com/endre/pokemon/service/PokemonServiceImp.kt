package com.endre.pokemon.service

import com.endre.pokemon.entity.PokemonDto
import com.endre.pokemon.entity.SimplePokemonDto
import com.endre.pokemon.repository.PokemonRepository
import com.endre.pokemon.util.PokemonConverter.Companion.convertFromDto
import com.endre.pokemon.util.PokemonConverter.Companion.convertFromSimplePokemonDto
import com.endre.pokemon.util.PokemonConverter.Companion.convertToDto
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.common.base.Throwables
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import javax.validation.ConstraintViolationException
import com.fasterxml.jackson.module.kotlin.*

/**
 * Created by Endre on 02.09.2018.
 * news
 * Verson:
 */
@Service("PokemonService")
class PokemonServiceImp : PokemonService {


    @Autowired
    private lateinit var pokemonRepository : PokemonRepository


    override fun createPokemon(pokemonDto : PokemonDto) : ResponseEntity<Long> {
        if (pokemonDto.num == null || pokemonDto.name == null ||
                pokemonDto.candy_count == null || pokemonDto.egg == null ||
                pokemonDto.img == null || pokemonDto.type == null || pokemonDto.weaknesses == null)
            return ResponseEntity(HttpStatus.BAD_REQUEST)

        val id: Long?

        try {
            id = pokemonRepository.save(convertFromDto(pokemonDto)).id
        } catch (e : Exception) {
            if(Throwables.getRootCause(e) is ConstraintViolationException) {
                return ResponseEntity.status(400).build()
            }
            throw e
        }

        return ResponseEntity(id, HttpStatus.CREATED)
    }

    override fun batchCreatePokemon(pokemons: List<PokemonDto>): ResponseEntity<Void> {
        pokemonRepository.saveAll(convertFromDto(pokemons))
        return ResponseEntity.ok().build()
    }

    override fun findBy(name: String?, num: String?): ResponseEntity<List<PokemonDto>> {
        val list = if (name.isNullOrBlank() && num.isNullOrBlank()) {
            pokemonRepository.findAll()
        }else if(!name.isNullOrBlank() && !num.isNullOrBlank()) {
            return ResponseEntity.status(400).build()
        } else if (!name.isNullOrBlank()){
            pokemonRepository.findAllByName(name!!)
        } else {
            pokemonRepository.findByNum(num!!)
        }

        return ResponseEntity(convertToDto(list), HttpStatus.OK)
    }

    override fun find(num: String?): ResponseEntity<PokemonDto> {
        val id: Long

        try {
            id = num!!.toLong()
        } catch (e: Exception) {
            return ResponseEntity.status(404).build()
        }

        val dto = pokemonRepository.findById(id).orElse(null) ?: return ResponseEntity.status(404).build()

        return ResponseEntity.ok(convertToDto(dto))
    }

    override fun update(num: String?, dto: PokemonDto): ResponseEntity<Void> {
        val id: Long

        try {
            id = num!!.toLong()
        } catch (e: Exception) {
            return ResponseEntity.status(400).build()
        }

        if (!pokemonRepository.existsById(id)) {
            return ResponseEntity.status(404).build()
        }

        if (dto.num == null || dto.img == null || dto.egg == null
                || dto.candy_count == null || dto.name == null
                || dto.type == null || dto.weaknesses == null) {
            return ResponseEntity.status(400).build()
        }

        var pokemon = pokemonRepository.findById(id).get()

        pokemon.num = dto.num!!
        pokemon.candyCount = dto.candy_count!!
        pokemon.egg = dto.egg!!
        pokemon.img = dto.img!!
        pokemon.name = dto.name!!
        pokemon.weaknesses = dto.weaknesses!!
        pokemon.type = dto.type!!
        pokemon.prevEvolution = convertFromSimplePokemonDto(dto.prev_evolution)
        pokemon.nextEvolution = convertFromSimplePokemonDto(dto.next_evolution)

        pokemonRepository.save(pokemon).id

        return ResponseEntity.status(204).build()
    }

    override fun patch(num: String?, jsonBody: String): ResponseEntity<Void> {
        val id: Long

        try {
            id = num!!.toLong()
        } catch (e: Exception) {
            return ResponseEntity.status(400).build()
        }

        if (!pokemonRepository.existsById(id)) {
            return ResponseEntity.status(404).build()
        }

        val jackson = ObjectMapper()

        val jsonNode: JsonNode

        try {
            jsonNode = jackson.readValue(jsonBody, JsonNode::class.java)
        } catch (e: Exception) {
            //Invalid JSON data as input
            return ResponseEntity.status(400).build()
        }

        if (jsonNode.has("id")) {
            //shouldn't be allowed to modify the counter id
            return ResponseEntity.status(409).build()
        }

        var pokemon = pokemonRepository.findById(id).get()

        if (jsonNode.has("num")){
            val num = jsonNode.get("num")
            if (num.isTextual){
                pokemon.num = num.asText()
            } else {
                return ResponseEntity.status(400).build()
            }
        }

        if (jsonNode.has("name")){
            val name = jsonNode.get("name")
            if (name.isTextual){
                pokemon.name = name.asText()
            } else {
                return ResponseEntity.status(400).build()
            }
        }

        if (jsonNode.has("img")){
            val img = jsonNode.get("img")
            if (img.isTextual){
                pokemon.img = img.asText()
            } else {
                return ResponseEntity.status(400).build()
            }
        }

        if (jsonNode.has("candy_count")){
            val candyCount = jsonNode.get("candy_count")
            if (candyCount.isInt){
                pokemon.candyCount = candyCount.asInt()
            } else {
                return ResponseEntity.status(400).build()
            }
        }

        if (jsonNode.has("egg")){
            val egg = jsonNode.get("egg")
            if (egg.isTextual){
                pokemon.egg = egg.asText()
            } else {
                return ResponseEntity.status(400).build()
            }
        }

        if (jsonNode.has("type")){
            val type = jsonNode.get("type")
            if (type.isNull){
                pokemon.type = null
            } else if (type.isArray){
                pokemon.type = type.toSet().map { it.asText() }.toSet()
            } else {
                return ResponseEntity.status(400).build()
            }
        }

        if (jsonNode.has("weaknesses")){
            val weaknesses = jsonNode.get("weaknesses")
            if (weaknesses.isNull){
                pokemon.weaknesses = null
            } else if (weaknesses.isArray){
                pokemon.weaknesses = weaknesses.toSet().map { it.asText() }.toSet()
            } else {
                return ResponseEntity.status(400).build()
            }
        }

        if (jsonNode.has("prev_evolution")){
            val prevEvolution = jsonNode.get("prev_evolution")
            if (prevEvolution.isNull){
                pokemon.prevEvolution = null

            } else if (prevEvolution.isArray){
                val mapper = jacksonObjectMapper()
                val tmp: Set<SimplePokemonDto> = mapper.readValue(prevEvolution.toString())
                pokemon.prevEvolution = convertFromSimplePokemonDto(tmp)

            } else {
                return ResponseEntity.status(400).build()
            }
        }

        if (jsonNode.has("next_evolution")){
            val nextEvolution = jsonNode.get("next_evolution")
            if (nextEvolution.isNull){
                pokemon.nextEvolution = null

            } else if (nextEvolution.isArray){
                val mapper = jacksonObjectMapper()
                val tmp: Set<SimplePokemonDto> = mapper.readValue(nextEvolution.toString())
                pokemon.nextEvolution = convertFromSimplePokemonDto(tmp)

            } else {
                return ResponseEntity.status(400).build()
            }
        }

        pokemonRepository.save(pokemon).id

        return ResponseEntity.status(204).build()
    }

    override fun delete(num: String?): ResponseEntity<Any> {
        val id: Long

        try {
            id = num!!.toLong()
        } catch (e: Exception) {
            return ResponseEntity.status(400).build()
        }

        if (!pokemonRepository.existsById(id)) {
            return ResponseEntity.status(404).build()
        }

        pokemonRepository.deleteById(id)
        return ResponseEntity.status(204).build()
    }
}