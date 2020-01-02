package ch.heigvd.amt.bidirhandshake.apisspecs.steps;

import ch.heigvd.amt.bidirhandshake.authapi.dto.PasswordChanger;
import io.cucumber.java.en.And;

public class UsersSteps {
    private final APISteps apiSteps;
    private LoginSteps loginSteps;
    private World world;

    PasswordChanger passwordChanger;
    PasswordChanger passwordChangerRollback;

    public UsersSteps(World world, LoginSteps loginSteps, APISteps apiSteps) {
        this.loginSteps = loginSteps;
        this.world = world;
        this.apiSteps = apiSteps;
    }

    @And("^I have a well-formed password payload$")
    public void iHaveAWellFormedPasswordPayload() {
        passwordChanger = new PasswordChanger();
        passwordChanger.setCurrentPassword("totem");
        passwordChanger.setPassword("newTotem");
        passwordChanger.setConfirmPassword("newTotem");

        this.world.body = passwordChanger;

        passwordChangerRollback = new PasswordChanger();
        passwordChangerRollback.setCurrentPassword("newTotem");
        passwordChangerRollback.setPassword("totem");
        passwordChangerRollback.setConfirmPassword("totem");
    }

    @And("I prepare a rollback")
    public void iPrepareARollback() {
        this.world.body = passwordChangerRollback;
    }
}
