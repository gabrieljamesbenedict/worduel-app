package com.porado.worduel_app.data.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

data class UsernamePassword (
    val username: String,
    val password: String
)

data class LoginResponse(
    val token: String
)

const val authEndpoint = "api/auth";

interface AuthApi {

    @POST("$authEndpoint/login")
    suspend fun login(@Body payload: UsernamePassword): LoginResponse;

    @POST("$authEndpoint/register")
    suspend fun register(@Body payload: UsernamePassword);

    @POST("$authEndpoint/logout")
    suspend fun logout();

    @GET("$authEndpoint/me")
    suspend fun me();

}