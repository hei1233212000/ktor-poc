package poc

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.dsl.module
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.inject
import poc.repository.UserRepository
import poc.service.UserService

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@kotlin.jvm.JvmOverloads
fun Application.restApiModule(@Suppress("UNUSED_PARAMETER") testing: Boolean = false) {
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }
    install(CallLogging)

    val userModule = module {
        single { UserService(get()) } // get() Will resolve HelloRepository
        single { UserRepository() }
    }
    install(Koin) {
        modules(userModule)
    }

    val service by inject<UserService>()

    routing {
        get("/greeting") {
            environment.log.info("called")
            call.respondText("Hello")
        }

        get("/users/{userName}") {
            val userName = call.parameters["userName"]!!
            call.respond(service.findUser(userName))
        }
    }
}
