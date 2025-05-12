package com.jossprogramming.tddudemy.modulocincoretrofitroom.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jossprogramming.tddudemy.modulocincoretrofitroom.network.LoginRequest
import com.jossprogramming.tddudemy.modulocincoretrofitroom.repository.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: AuthRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {
    private val _loginSuccess = MutableStateFlow<Boolean?>(null)
    val loginSuccess: StateFlow<Boolean?> = _loginSuccess

    fun login(email:String,password:String){
        viewModelScope.launch(dispatcher){
            val request = LoginRequest(email, password)
            val success = repository.loginAndSave(request)
            _loginSuccess.value = success
        }
    }
}