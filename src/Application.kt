package poc

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@kotlin.jvm.JvmOverloads
fun Application.restApiModule(@Suppress("UNUSED_PARAMETER") testing: Boolean = false) {
    routing {
        get("/greeting") {
            call.respondText("Hello")
        }
    }
}

