package poc.service

import poc.model.User
import poc.repository.UserRepository

class UserService(private val userRepository: UserRepository) {
    fun findUser(userName: String): User {
        return userRepository.findUser(userName)
    }
}
