package ch.heigvd.amt.bidirhandshake.apisspecs;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/scenarios/", plugin = {"pretty", "html:target/cucumber"})
public class SpecificationTest {

}