package com.jossprogramming.tddudemy.modulocuatro

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


/*
* Usar la metodología TDD para Validar Un Login con correo y password con los siguientes casos de prueba:
* -
* -
* */
class LoginValidatorTest {
    lateinit var loginValidator:LoginValidator

    @Before
    fun setUp(){
        loginValidator = LoginValidator()
    }

    //El email no debe estar vacío. Se espera devuelva false
    @Test
    fun `cuando el email es vacio devuelve false`() {
        val emailValid = loginValidator.validateEmail("")
        assertFalse(emailValid)
    }

    //El email debe tener un formato válido. Se espera devuelva false
    @Test
    fun `El email debe tener un formato valido en caso contrari devolver false`() {
        val emailValid = loginValidator.validateEmail("invalid-email")
        assertFalse(emailValid)
    }

    //Email con formato correcto. Se espera devuelva true
    @Test
    fun `El email devuelve true cuando se ingresa un formato correcto`() {
        val emailValid = loginValidator.validateEmail("test@example.com")
        assertTrue(emailValid)
    }

    //El password no debe estar vacío. Se espera devuelva false
    @Test
    fun `El password vacio debe devolver false`() {
        val passwordValid = loginValidator.validatePassword("")
        assertFalse(passwordValid)
    }

    //El password debe tener 6 caracteres. Se espera devuelva false si tiene menos
    @Test
    fun `cuando se ingresa un password menor de seis caracteres devuelve false`() {
        val passwordValid = loginValidator.validatePassword("12345")
        assertFalse(passwordValid)
    }

    //El password debe tener al menos un número. Se espera devuelva false si no tiene
    @Test
    fun `cuando se ingrese un password sin un numero debe devolver false`() {
        val passwordValid = loginValidator.validatePassword("PASSWORD")
        assertFalse(passwordValid)
    }

    //El password debe tener al menos una letra mayúscula. Se espera devuelva false si no tiene
    @Test
    fun `cuando se ingrese un password sin una mayuscula debe devolver false`() {
        val passwordValid = loginValidator.validatePassword("password123")
        assertFalse(passwordValid)
    }

    @Test
    fun `Al ingresar un password valido debe devolver True`() {
        val passwordValid = loginValidator.validatePassword("Password123")
        assertTrue(passwordValid)
    }
}