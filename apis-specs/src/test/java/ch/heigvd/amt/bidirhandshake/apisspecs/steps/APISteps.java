package ch.heigvd.amt.bidirhandshake.apisspecs.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import kong.unirest.Unirest;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class APISteps {
    private World world;

    public APISteps(World world) {
        this.world = world;

        // workaround to respect snake case with Unirest
        ObjectMapper jacksonMapper = new com.fasterxml.jackson.databind.ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        Unirest.config()
                .setDefaultHeader("Content-Type", "application/json")
                .setDefaultHeader("Accept", "application/json")
                .setObjectMapper(new kong.unirest.ObjectMapper() {
                    @Override
                    public <T> T readValue(String s, Class<T> aClass) {
                        try {
                            return jacksonMapper.readValue(s, aClass);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                            throw new RuntimeException(e);
                        }
                    }

                    @Override
                    public String writeValue(Object o) {
                        try {
                            return jacksonMapper.writeValueAsString(o);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                            throw new RuntimeException(e);
                        }
                    }
                });
    }

    @When("I POST it to the route {string} with token")
    public void iPOSTItToTheRouteWithToken(String route) {
        this.world.route = route;

        this.world.response = Unirest.post(this.world.BASE_URL + this.world.route)
                .body(this.world.body).headerReplace("Authorization", this.world.token)
                .asJson();
    }

    @When("I POST it to the route {string}")
    public void iPOSTItToTheRoute(String route) {
        this.world.route = route;

        this.world.response = Unirest.post(this.world.BASE_URL + this.world.route)
                .body(this.world.body)
                .asJson();
    }

    @When("I POST it to the route {string} as {string}")
    public void iPOSTItToTheGivenRouteAsGivenContentType(String route, String contentType) {
        world.response = Unirest.post(world.BASE_URL + route)
                .body(this.world.body/*userCredential*/)
                .headerReplace("Content-Type", contentType)
                .asJson();
    }

    @When("I POST it to the route {string} as malformed {string}")
    public void iPOSTItToTheGivenRouteAsMalformedGivenContentType(String route, String contentType) {
        world.response = Unirest.post(world.BASE_URL + route)
                .body(this.world.body/*userCredential*/.toString())
                .headerReplace("Content-Type", contentType)
                .asJson();
    }

    @When("I PUT it to the route {string} with token")
    public void iPUTItToTheRouteWithToken(String route) {
        this.world.route = route;

        this.world.response = Unirest.put(this.world.BASE_URL + this.world.route)
                .body(this.world.body).headerReplace("Authorization", this.world.token)
                .asJson();
    }

    @Then("^I receive a (\\d+) status code$")
    public void iReceiveAStatusCode(int statusCode) {
        assertEquals(statusCode, this.world.response.getStatus());
    }

    @And("^I receive a response with a jwt in (\\w+) header$")
    public void iReceiveAResponseWithAJwtInAuthorizationHeader(String headerKey) {
        assertTrue(this.world.response.getHeaders().containsKey(headerKey));
        assertNotNull(this.world.response.getHeaders().get(headerKey));
    }

    @And("^I receive a response without (\\w+) header$")
    public void iReceiveAResponseWithoutAuthorizationHeader(String headerKey) {
        assertFalse(this.world.response.getHeaders().containsKey(headerKey));
    }

    @When("I GET the route {string} with token")
    public void iGETTheRouteWithToken(String route) {
        world.response = Unirest.get(world.BASE_URL + route).headerReplace("Authorization", this.world.token).asJson();
    }

    @When("I GET the route {string}")
    public void iGETTheRoute(String route) {
        world.response = Unirest.get(world.BASE_URL + route).asJson();
    }

    @When("I DELETE the route {string} with token")
    public void iDELETETheRouteWithToken(String route) {
        world.response = Unirest.delete(world.BASE_URL + route).headerReplace("Authorization", this.world.token).asJson();
    }
}
