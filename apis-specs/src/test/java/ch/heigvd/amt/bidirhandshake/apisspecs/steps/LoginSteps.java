package ch.heigvd.amt.bidirhandshake.apisspecs.steps;

import ch.heigvd.amt.bidirhandshake.authapi.dto.UserCredential;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import kong.unirest.*;

import static org.junit.Assert.*;

public class LoginSteps {
    UserCredential userCredential;

    private final String BASE_URL = "http://localhost:8080";
    HttpResponse<JsonNode> response;

    public LoginSteps() {
        Unirest.config()
                .setDefaultHeader("Content-Type", "application/json")
                .setDefaultHeader("Accept", "application/json");
    }

    @Given("I have the user {string}")
    public void iHaveTheUser(String email) {
        userCredential = new ch.heigvd.amt.bidirhandshake.authapi.dto.UserCredential();
        userCredential.setEmail(email);
    }

    @Given("I have the a user without email")
    public void iHaveTheAUserWithoutEmail() {
        userCredential = new ch.heigvd.amt.bidirhandshake.authapi.dto.UserCredential();
    }

    @And("I have the password {string}")
    public void iHaveThePassword(String password) {
        userCredential.setPassword(password);
    }

    @When("I POST it to the route {string} as {string}")
    public void iPOSTItToTheGivenRouteAsGivenContentType(String route, String contentType) {
        response = Unirest.post(BASE_URL + route)
                .body(userCredential)
                .headerReplace("Content-Type", contentType)
                .asJson();
        }

    @When("I POST it to the route {string} as malformed {string}")
    public void iPOSTItToTheGivenRouteAsMalformedGivenContentType(String route, String contentType) {
        response = Unirest.post(BASE_URL + route)
                .body(userCredential.toString())
                .headerReplace("Content-Type", contentType)
                .asJson();
    }

    @Then("^I receive a (\\d+) status code$")
    public void iReceiveAStatusCode(int statusCode) {
        assertEquals(statusCode, response.getStatus());
    }

    @And("^I receive a response with a jwt in (\\w+) header$")
    public void iReceiveAResponseWithAJwtInAuthorizationHeader(String headerKey) {
        assertTrue(response.getHeaders().containsKey(headerKey));
        assertNotNull(response.getHeaders().get(headerKey));
    }

    @And("^I receive a response without (\\w+) header$")
    public void iReceiveAResponseWithoutAuthorizationHeader(String headerKey) {
        assertFalse(response.getHeaders().containsKey(headerKey));
    }
}
