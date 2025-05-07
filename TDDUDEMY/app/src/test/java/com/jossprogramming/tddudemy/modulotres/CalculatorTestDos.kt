package com.jossprogramming.tddudemy.modulotres

import com.jossprogramming.tddudemy.modulodos.Calculator
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import org.junit.Assert.assertEquals
import org.junit.Test

class CalculatorTestDos {
    val calculator: Calculator = mockk()

    @Test
    fun `verify different interactions`() {
        every{calculator.suma(any(),any())} returns 100
        calculator.suma(1,2)
        calculator.suma(2,3)
        verify(exactly = 2){calculator.suma(any(),any())}
        verifyOrder{
            calculator.suma(1,2)
            calculator.suma(2,3)
        }
    }

    @Test
    fun `test exception`() {
        every{calculator.suma(any(),any())} throws IllegalArgumentException("Invalid numbers")
        try{
            calculator.suma(1,2)
        }catch (e:IllegalArgumentException){
            assertEquals("Invalid numbers",e.message)
        }
    }
}