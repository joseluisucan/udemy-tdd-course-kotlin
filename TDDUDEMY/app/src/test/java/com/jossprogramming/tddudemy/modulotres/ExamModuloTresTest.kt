package com.jossprogramming.tddudemy.modulotres

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


/*
Examen:
- Utiliza la anotacióm @MockK o Mockito para crear un mock de UserRepository.
- configura getUserById() para que devuelva un objeto con el nombre "John Doe" cuando se le pase el ID 1.
- Verifica que el método getUserById() se haya llamado exactamente una vez.
- Utiliza un Argument Captor para capturar el valor del argumento pasado.
- Valida que el argumento mándado sea 1

Resultado Esperado:
✅ La prueba debe:

Pasar con éxito.
Capturar el argumento 1.
Verificar que el nombre devuelto sea "John Doe"
* */
class ExamModuloTresTest {
    @MockK
    lateinit var userRepository:UserRepository

    private lateinit var userService:UserService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        userService = UserService(userRepository)
    }

    @Test
    fun `should return user name and capture argument`() {
        val slot = slot<Int>()
        every{userRepository.getUserById(capture(slot))} returns User(1,"John Doe")
        val result = userService.getUserName(1)

        assertEquals("John Doe",result)

        verify(exactly = 1){userRepository.getUserById(1)}

        assertEquals(1,slot.captured)
    }
}