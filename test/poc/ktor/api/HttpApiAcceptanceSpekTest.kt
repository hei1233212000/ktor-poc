package poc.ktor.api

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.amshove.kluent.`should be equal to`
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import poc.restApiModule

/**
 * Seems like using Ktor + Spek is not a good combination
 */
object HttpApiAcceptanceSpekTest : Spek({
    Feature("Greeting") {
        Scenario("adding items") {
            lateinit var response: TestApplicationResponse

            When("call the greeting API") {
                withTestApplication(Application::restApiModule) {
                    response = handleRequest(HttpMethod.Get, "/greeting").response
                }
            }

            Then("the response status should be OK") {
                response.status() `should be equal to` HttpStatusCode.OK
            }

            Then("the greeting message should be \"Hello\"") {
                response.content `should be equal to` "Hello"
            }
        }
    }
})
