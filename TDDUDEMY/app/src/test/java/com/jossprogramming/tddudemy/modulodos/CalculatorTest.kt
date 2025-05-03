package com.jossprogramming.tddudemy.modulodos

import org.junit.After
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class CalculatorTest {
    private lateinit var calculator: Calculator

    @Before
    fun setUp(){
        calculator = Calculator()
    }

    @Test
    fun sumShouldReturnCorrectResult(){
        val result = calculator.suma(3,6)
        assertEquals(9,result)
    }

    @Test
    fun `sum should return incorrect result`(){
        val result = calculator.suma(3,6)
        assertNotEquals(7,result)
    }

    @Test
    fun `sum should be positive result`(){
        val result = calculator.suma(1,0)
        assertTrue(result>0)
    }

    @Test
    fun `sum should be negative result`(){
        val result = calculator.suma(-63,0)
        assertFalse(result>0)
    }

    @Test
    fun `sum should return null for zero values`(){
        val result = calculator.sumNull(0,0)
        assertNull(result)
    }

    @Test
    fun `sum should return not null`(){
        val result = calculator.sumNull(0,0)
        assertNotNull(result)
    }

    @Test
    fun `should be the same calculator instance`(){
        val calculator1 = calculator
        val calculator2 = calculator
        assertSame(calculator1,calculator2)
    }

    @Test
    fun `should not be the same calculator instance`(){
        val calculator1 = Calculator()
        val calculator2 = Calculator()
        assertNotSame(calculator1,calculator2)
    }

    @Test
    fun `sum should throw expcetion for negative input`(){
        assertThrows(IllegalArgumentException::class.java){
            calculator.sumNull(-1,3)
        }
    }

    /*Prueba del módulo
    Crea una prueba unitaria utilizando la función sum() de la clase Calculator que
    cumpla con los siguientes requisitos:
    1. La prueba debe verificar que los resultados de tres sumas correspondan con los valores esperados en un array.
    2. Crea un array para hacer la comparación
    3. Utiliza la aserción assertArrayEquals() de JUnit para comparar dos arrays.
     */
    @Test
    fun `sum should return expected result in array`(){
        val suma1 = calculator.suma(1,2)
        val suma2 = calculator.suma(2,2)
        val suma3 = calculator.suma(4,2)
        val resultsExpected = arrayOf(3,4,6)
        assertArrayEquals(resultsExpected,arrayOf(suma1,suma2,suma3))
    }



    @After
    fun tearDown(){
        println("Limpiando después de cada prueba")
    }
}