package ch.heigvd.amt.bidirhandshake.movieapi.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

//https://medium.com/@bcarunmail/set-up-and-run-cucumber-tests-in-spring-boot-application-d0c149d26220

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", plugin = {"pretty", "json:target/cucumber-report.json"})
public class CucumberTest {

}
