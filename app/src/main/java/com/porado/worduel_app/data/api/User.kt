package com.porado.worduel_app.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import kotlin.time.Instant

data class User(
    val userId: Long?,
    val username: String,
    val password: String,
    val role: String
);

const val userEndpoint = "api/users";

interface UserApi {

    @GET(userEndpoint)
    suspend fun getAllUsers(): List<User>;

    @GET("$userEndpoint/{id}")
    suspend fun getUserById(@Path("id") id: Long): User

}