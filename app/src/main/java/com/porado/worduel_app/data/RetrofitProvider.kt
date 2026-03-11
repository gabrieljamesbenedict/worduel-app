package com.porado.worduel_app.data

import com.porado.worduel_app.data.api.AuthApi
import com.porado.worduel_app.data.api.UserApi
import com.porado.worduel_app.data.service.AuthService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {

    // 1. Define the base URL once
    private const val BASE_URL = "http://10.0.2.2:8080/"

    // 2. Build the client with the interceptor
    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            AuthService.jwtToken?.let { token ->
                requestBuilder.addHeader("Authorization", "Bearer $token")
            }
            chain.proceed(requestBuilder.build())
        }
        .build()

    // 3. Build Retrofit AND attach the client
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client) // <-- THIS IS THE CRITICAL ADDITION
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // 4. Expose the API implementations to the rest of your app
    val authApi: AuthApi = retrofit.create(AuthApi::class.java)
    val userApi: UserApi = retrofit.create(UserApi::class.java)
}