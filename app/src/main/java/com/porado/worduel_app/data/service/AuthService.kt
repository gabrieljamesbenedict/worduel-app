package com.porado.worduel_app.data.service

import com.porado.worduel_app.data.RetrofitProvider
import com.porado.worduel_app.data.api.AuthApi
import com.porado.worduel_app.data.api.UsernamePassword
import com.porado.worduel_app.data.api.authEndpoint
import retrofit2.http.GET
import retrofit2.http.POST

object AuthService {

    val authService: AuthApi = RetrofitProvider.retrofit.create(AuthApi::class.java)

    suspend fun login(payload: UsernamePassword) {
        authService.login(payload);
    }

    suspend fun register(payload: UsernamePassword) {
        authService.register(payload);
    }

    suspend fun logout() {
        authService.logout();
    }

    suspend fun me() {
        authService.me();
    }

}