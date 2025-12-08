package com.example.connectu.data

class UserRepository(private val dao: UserDao) {

    suspend fun registerUser(username: String, password: String, displayName: String) {
        val hashed = password.hashCode().toString()

        val user = UserEntity(
            username = username,
            passwordHash = hashed,
            displayName = displayName
        )
        dao.insertUser(user)
    }

    suspend fun loginUser(username: String, password: String): UserEntity? {
        val user = dao.getUserByUsername(username)
        val hashed = password.hashCode().toString()

        return if (user?.passwordHash == hashed) user else null
    }
}
