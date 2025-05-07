package com.jossprogramming.tddudemy.modulotres

import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.any
import org.mockito.kotlin.inOrder
import org.mockito.kotlin.times
import org.mockito.kotlin.whenever

class PaymentServiceTest {
    lateinit var mockBankApi: BankApi
    lateinit var paymentService: PaymentService

    @Before
    fun setUp() {
        mockBankApi = mock()
        paymentService = PaymentService(mockBankApi)
    }

    @Test
    fun `test process payment with mocked bank api`(){
        //Definir el comportamiento del mock
        whenever(mockBankApi.charge(100.0)).thenReturn(true)
        val result = paymentService.processPayment(100.0)

        assertTrue(result)
    }

    @Test
    fun `test process payment with correct parameter`(){
        //Definir el comportamiento del mock
        whenever(mockBankApi.charge(100.0)).thenReturn(true)
        val result = paymentService.processPayment(100.0)
        verify(mockBankApi).charge(100.0)
    }

    @Test
    fun `test multiple responses from mock`(){
        whenever(mockBankApi.charge(100.0)).thenReturn(true,false)
        val firstResult = paymentService.processPayment(100.0)
        assertTrue(firstResult)

        val secondResult = paymentService.processPayment(100.0)
        assertFalse(secondResult)
    }

    @Test
    fun `verify function is called multiple times`(){
        paymentService.processPayment(100.0)
        paymentService.processPayment(134.0)

        verify(mockBankApi,times(2)).charge(any())
    }

    @Test
    fun `verify function is called in specif order`(){
        paymentService.processPayment(134.0)
        paymentService.processPayment(100.0)

        val inOrder = inOrder(mockBankApi)
        inOrder.verify(mockBankApi).charge(100.0)
        inOrder.verify(mockBankApi).charge(134.0)
    }














}