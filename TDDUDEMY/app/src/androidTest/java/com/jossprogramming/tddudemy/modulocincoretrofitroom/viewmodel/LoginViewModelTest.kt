package com.jossprogramming.tddudemy.modulocincoretrofitroom.viewmodel

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jossprogramming.tddudemy.modulocincoretrofitroom.db.AppDatabase
import com.jossprogramming.tddudemy.modulocincoretrofitroom.db.BitacoraLoginsDao
import com.jossprogramming.tddudemy.modulocincoretrofitroom.network.AuthApi
import com.jossprogramming.tddudemy.modulocincoretrofitroom.network.FakeAuthApi
import com.jossprogramming.tddudemy.modulocincoretrofitroom.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class LoginViewModelTest {
    private lateinit var database:AppDatabase
    private lateinit var dao: BitacoraLoginsDao
    private var testDispatcher = StandardTestDispatcher()
    private lateinit var authApi:AuthApi
    private lateinit var repository: AuthRepository
    private lateinit var viewModel:LoginViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        authApi = FakeAuthApi()
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room
            .inMemoryDatabaseBuilder(context,AppDatabase::class.java)
            .allowMainThreadQueries().build()
        dao = database.bitacoraLoginsDao()
        repository = AuthRepository(authApi,dao)
        viewModel = LoginViewModel(repository,testDispatcher)
    }

    @After
    fun tearDown() {
        if(this::database.isInitialized){
            database.close()
        }
    }

    @Test
    fun loginShouldEmitSuccessAndSaveInRoom() = runTest {
        val email = "user@test.com"
        val password = "Password1"

        viewModel.login(email,password)
        advanceUntilIdle()//Espera a que se ejecuten las corutinas

        val result = viewModel.loginSuccess.filterNotNull().first()
        assertEquals(true,result)

        val lastLogin = dao.getLastLogin()
        assertNotNull(lastLogin)
        assertEquals(email,lastLogin?.email)
        assertEquals(password,lastLogin?.password)
    }
}