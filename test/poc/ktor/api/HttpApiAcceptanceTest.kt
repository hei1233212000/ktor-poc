package poc.ktor.api

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.server.testing.*
import junit.framework.TestCase.assertEquals
import org.junit.Test
import poc.restApiModule

class HttpApiAcceptanceTest {
    @Test
    fun `should receive greeting message`() = withTestApplication(Application::restApiModule) {
        with(handleRequest(HttpMethod.Get, "/greeting")) {
            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals("Hello", response.content)
        }
    }
}
