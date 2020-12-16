package poc.ktor.api

import com.google.gson.Gson
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should not be`
import org.amshove.kluent.shouldStartWith
import org.junit.jupiter.api.Test
import poc.model.User
import poc.restApiModule

class HttpApiAcceptanceJUnit5Test {
    @Test
    fun `should receive greeting message`(): Unit = get("/greeting") {
        response.status() `should be equal to` HttpStatusCode.OK
        response.content `should be equal to` "Hello"
    }

    @Test
    fun `should receive user object`() {
        val userName = "Peter"
        get("/users/$userName") {
            response.status() `should be equal to` HttpStatusCode.OK
            response.headers[HttpHeaders.ContentType]?.shouldStartWith(ContentType.Application.Json.toString())
            val user = response.content?.let {
                Gson().fromJson(it, User::class.java)
            }
            user `should not be` null
            user?.name `should be equal to` userName
        }
    }
}

fun <R> get(url: String, test: TestApplicationCall.() -> R): R {
    return withTestApplication(Application::restApiModule) {
        with(handleRequest(HttpMethod.Get, url)) {
            test()
        }
    }
}
