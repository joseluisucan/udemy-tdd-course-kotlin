package com.jossprogramming.tddudemy.modulocincoretrofitroom.repository

import com.jossprogramming.tddudemy.modulocincoretrofitroom.db.BitacoraLogins
import com.jossprogramming.tddudemy.modulocincoretrofitroom.db.BitacoraLoginsDao
import com.jossprogramming.tddudemy.modulocincoretrofitroom.network.AuthApi
import com.jossprogramming.tddudemy.modulocincoretrofitroom.network.LoginRequest

class AuthRepository(
    private val api:AuthApi,
    private val dao:BitacoraLoginsDao)
{
    suspend fun loginAndSave(request:LoginRequest):Boolean{
        val response = api.login(request)
        if(response.success){
            dao.insert(BitacoraLogins(email =request.email,password = request.password))
        }
        return response.success
    }
}