package com.jossprogramming.tddudemy.modulotres

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PaymentServiceMockTest {
    @MockK
    lateinit var mockBankApi: BankApi

    lateinit var paymentService: PaymentService

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        paymentService = PaymentService(mockBankApi)
    }

    @Test
    fun `process payment should call charge`(){
        every{mockBankApi.charge(100.0)} returns true

        val result = paymentService.processPayment(110.0)
        assertEquals(true, result)
        verify{mockBankApi.charge(100.0)}
    }

    @Test
    fun `should capture amount passed to charge`(){
        val slot = slot<Double>()
        every{mockBankApi.charge(capture(slot))} returns true
        paymentService.processPayment(99.99)
        assertEquals(100.0,slot.captured,0.05)
    }

    @Test
    fun `should capture multple amounts and validate order`(){
        val slots = mutableListOf<Double>()
        every{mockBankApi.charge(capture(slots))} returns true

        paymentService.processPayment(300.0)
        paymentService.processPayment(100.0)
        paymentService.processPayment(200.0)
        assertEquals(listOf(100.0,200.0,300.0),slots)

    }


}