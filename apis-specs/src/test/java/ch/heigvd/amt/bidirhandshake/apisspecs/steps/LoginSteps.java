package ch.heigvd.amt.bidirhandshake.apisspecs.steps;

import ch.heigvd.amt.bidirhandshake.authapi.dto.UserCredential;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class LoginSteps {
    private World world;
    private APISteps apiSteps;

    UserCredential userCredential;

    public LoginSteps(World world, APISteps apiSteps) {
        this.world = world;
        this.apiSteps = apiSteps;
    }

    @Given("I have the user {string}")
    public void iHaveTheUser(String email) {
        userCredential = new ch.heigvd.amt.bidirhandshake.authapi.dto.UserCredential();
        userCredential.setEmail(email);

        this.world.body = userCredential;
    }

    @Given("I have a user without email")
    public void iHaveAUserWithoutEmail() {
        this.world.body = this.userCredential = new ch.heigvd.amt.bidirhandshake.authapi.dto.UserCredential();
    }

    @Given("I am logged in as {string} with password {string}")
    public void iAmLoggedInAs(String email, String password) {
        iHaveTheUser(email);
        iHaveThePassword(password);
        this.apiSteps.iPOSTItToTheRoute("/login");
        this.apiSteps.iReceiveAStatusCode(200);
        iAmLoggedInAs(email);
    }

    @And("I have the password {string}")
    public void iHaveThePassword(String password) {
        userCredential.setPassword(password);
    }

    @And("I am logged in as {string}")
    public void iAmLoggedInAs(String username) {
        System.out.println("Logged in as " + username);
        this.world.token = this.world.response.getHeaders().get("Authorization").get(0);
    }
}
