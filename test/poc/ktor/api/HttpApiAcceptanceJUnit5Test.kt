package poc.ktor.api

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test
import poc.restApiModule

class HttpApiAcceptanceJUnit5Test {
    @Test
    fun `should receive greeting message`(): Unit = withTestApplication(Application::restApiModule) {
        with(handleRequest(HttpMethod.Get, "/greeting")) {
            response.status() `should be equal to` HttpStatusCode.OK
            response.content `should be equal to` "Hello"
        }
    }
}
