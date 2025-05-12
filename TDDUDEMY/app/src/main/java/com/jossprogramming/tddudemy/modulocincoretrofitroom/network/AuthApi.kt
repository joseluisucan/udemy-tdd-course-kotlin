package com.jossprogramming.tddudemy.modulocincoretrofitroom.network

import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}

data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val success: Boolean)

class FakeAuthApi : AuthApi {
    override suspend fun login(request: LoginRequest): LoginResponse {
        return LoginResponse(success = true) // puedes personalizar esta respuesta si quieres
    }
}