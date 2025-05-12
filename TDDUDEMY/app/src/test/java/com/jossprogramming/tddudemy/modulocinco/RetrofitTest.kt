package com.jossprogramming.tddudemy.modulocinco

import com.jossprogramming.tddudemy.modulocincoretrofitroom.network.AuthApi
import com.jossprogramming.tddudemy.modulocincoretrofitroom.network.LoginRequest
import com.jossprogramming.tddudemy.modulocincoretrofitroom.network.LoginResponse
import com.jossprogramming.tddudemy.modulocincoretrofitroom.utils.Utils
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var authApi: AuthApi

    @Before
    fun setUp(){
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))//URL FALSO
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        authApi = retrofit.create(AuthApi::class.java)
    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }

    @Test
    fun `login should return success response`() = runTest {
        val responseBody = """{"success":true,"token":"abc123"}"""
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(responseBody)
        )

        val request = LoginRequest("user@test.com","Password1")
        val response = authApi.login(request)
        assertTrue(response.success)
    }

    @Test
    fun `login should return success response with json`() = runTest {
        val responseBody = Utils.readRawJson("login_response.json")
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(responseBody)
        )

        val request = LoginRequest("user@test.com","Password1")
        val response = authApi.login(request)
        assertTrue(response.success)
    }

    @Test
    fun `login return success true using MockK`() = runTest {
        val authApi = mockk<AuthApi>()

        //Given
        val request = LoginRequest("user@test.com","Password1")
        val expectedResponse = LoginResponse(success = true)
        coEvery{authApi.login(request)} returns expectedResponse

        //When
        val response = authApi.login(request)

        //Then
        assertEquals(true,response.success)
        coVerify{authApi.login(request)}
    }

    @Test
    fun `login return success true using Mockito`() = runTest {
        val authApi = mock<AuthApi>()

        //Given
        val request = LoginRequest("user@test.com","Password1")
        val expectedResponse = LoginResponse(success = true)
        whenever(authApi.login(request)).thenReturn(expectedResponse)

        //When
        val response = authApi.login(request)

        //Then
        assertEquals(true,response.success)
        verify(authApi).login(request)
    }




}