package com.jossprogramming.tddudemy.modulocincoretrofitroom.utils


object Utils{
    fun readRawJson(fileName: String): String {
        return object {}.javaClass.classLoader
            ?.getResource("raw/$fileName")
            ?.readText()
            ?: throw IllegalArgumentException("Archivo raw/$fileName no encontrado")
    }
}