package com.jossprogramming.tddudemy.modulotres

data class User(val id: Int, val name: String)

class UserRepository {
    fun getUserById(userId: Int): User {
        return User(userId, "User $userId")
    }
}

class UserService(private val userRepository: UserRepository) {
    fun getUserName(userId: Int): String {
        return userRepository.getUserById(userId).name
    }
}
