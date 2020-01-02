package ch.heigvd.amt.bidirhandshake.apisspecs.steps;

import ch.heigvd.amt.bidirhandshake.authapi.dto.UserDTO;
import io.cucumber.java.en.When;
import org.apache.commons.lang.RandomStringUtils;

public class AdminSteps {
    private World world;

    public AdminSteps(World world) {
        this.world = world;
    }

    @When("I have a well-formed user payload")
    public void iHaveAWellFormedUserPayload() {
        UserDTO user = new UserDTO();
        user.username(RandomStringUtils.randomAlphabetic(10));
        user.setEmail(user.getUsername() + "@domain.com");
        user.setPassword("password");
        user.setConfirmPassword("password");
        user.setRole("user");

        this.world.body = user;
    }
}
