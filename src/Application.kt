package poc

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.response.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@kotlin.jvm.JvmOverloads
fun Application.restApiModule(@Suppress("UNUSED_PARAMETER") testing: Boolean = false) {
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }

    routing {
        get("/greeting") {
            environment.log.info("called")
            call.respondText("Hello")
        }

        get("/users/{userName}") {
            val userName = call.parameters["userName"]!!
            call.respond(User(name = userName))
        }
    }
}

data class User(
    val name: String
)

