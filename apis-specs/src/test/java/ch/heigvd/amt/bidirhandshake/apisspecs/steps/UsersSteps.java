package ch.heigvd.amt.bidirhandshake.apisspecs.steps;

import ch.heigvd.amt.bidirhandshake.authapi.dto.UserCredential;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import static org.junit.Assert.*;

public class UsersSteps {
    UserCredential userCredential;

    private String baseUrl = "http://localhost:8080";
    HttpResponse<JsonNode> response;

    public UsersSteps() {
        Unirest.config()
                .setDefaultHeader("Content-Type", "application/json")
                .setDefaultHeader("Accept", "application/json");
    }

    @Given("^I have a user login payload$")
    public void i_have_a_user_login_payload() {
        userCredential = new ch.heigvd.amt.bidirhandshake.authapi.dto.UserCredential();
        userCredential.setEmail("pete842@mail.com");
        userCredential.setPassword("totem");
    }

    @When("^I POST it to the (/[a-zA-Z/]+) endpoint$")
    public void i_POST_it_to_the_users_login_endpoint(String path) throws JsonProcessingException {
        response = Unirest.post(baseUrl + path)
                .header("Content-Type", "application/json")
                .body(userCredential)
                .asJson();
    }

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int statusCode) {
        assertEquals(statusCode, response.getStatus());
    }

    @And("^I receive a response with a jwt in (\\w+) header$")
    public void iReceiveAResponseWithAJwtInAuthorizationHeader(String headerKey) {
        assertTrue(response.getHeaders().containsKey(headerKey));
        assertNotNull(response.getHeaders().get(headerKey));
    }
}
