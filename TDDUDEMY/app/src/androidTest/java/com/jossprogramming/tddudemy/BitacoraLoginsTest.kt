package com.jossprogramming.tddudemy

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jossprogramming.tddudemy.modulocincoretrofitroom.db.AppDatabase
import com.jossprogramming.tddudemy.modulocincoretrofitroom.db.BitacoraLogins
import com.jossprogramming.tddudemy.modulocincoretrofitroom.db.BitacoraLoginsDao
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class BitacoraLoginsTest {

    private lateinit var database:AppDatabase
    private lateinit var dao:BitacoraLoginsDao

    @Before
    fun setUp(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.bitacoraLoginsDao()
    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun insertAndGetLastLoginShouldReturnInseertedObject() = runTest{
        val login = BitacoraLogins(email = "user@test.com",password = "Password1")

        dao.insert(login)
        val lastLogin = dao.getLastLogin()

        assertNotNull(lastLogin)
        assertEquals("user@test.com",lastLogin?.email)
        assertEquals("Password1",lastLogin?.password)
    }











}