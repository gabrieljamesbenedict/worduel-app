package com.porado.worduel_app.data.repository

import com.porado.worduel_app.data.RetrofitProvider
import com.porado.worduel_app.data.api.User
import com.porado.worduel_app.data.api.UserApi

object UserRepository {
    val userService: UserApi = RetrofitProvider.retrofit.create(UserApi::class.java);

    suspend fun getAllUsers(): List<User> {
        return userService.getAllUsers();
    }

    suspend fun getUserById(id: Long): User {
        return userService.getUserById(id);
    }
}