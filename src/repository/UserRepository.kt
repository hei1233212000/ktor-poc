package poc.repository

import org.slf4j.LoggerFactory
import poc.model.User

class UserRepository {
    fun findUser(userName: String): User {
        logger.info("userName: {}", userName)
        return User(name = userName)
    }

    private companion object {
        private val logger = LoggerFactory.getLogger(UserRepository::class.java)
    }
}
