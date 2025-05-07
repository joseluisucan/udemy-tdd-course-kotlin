package com.jossprogramming.tddudemy.modulotres

class BankApi {
    fun charge(amount:Double):Boolean{
        return true
    }
}

class PaymentService(private val bankApi: BankApi) {
    fun processPayment(amount:Double):Boolean{
        return bankApi.charge(amount)
    }
}