package com.jossprogramming.tddudemy.modulocuatro

class LoginValidator {
    fun validateEmail(email:String):Boolean{
        val regex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        return email.isNotEmpty() && regex.matches(email)
    }

    fun validatePassword(pass: String): Boolean {
        val regex = Regex("^(?=.*[A-Z])(?=.*\\d).{6,}$")
        return regex.matches(pass)
    }
}