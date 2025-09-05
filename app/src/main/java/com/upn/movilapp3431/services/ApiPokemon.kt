package com.upn.movilapp3431.services

import com.upn.movilapp3431.entities.PokemonResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiPokemon {
    // GET -> https://pokeapi.co/api/v2/pokemon
    @GET("pokemon")
    suspend fun getPokemons(): PokemonResponse
}