package com.porado.worduel_app.data.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

data class NicknameRequest(
    val nickname: String
)

data class Player(
    val id: String,
    val nickname: String
)

data class User(
    val userId: Long?,
    val username: String,
    val password: String,
    val role: String
)

const val userEndpoint = "api/users"

interface UserApi {

    @GET(userEndpoint)
    suspend fun getAllUsers(): List<User>

    @GET("$userEndpoint/{id}")
    suspend fun getUserById(@Path("id") id: Long): User

    @POST("/players/nickname")
    suspend fun setNickname(@Body payload: NicknameRequest): Response<Player>
}