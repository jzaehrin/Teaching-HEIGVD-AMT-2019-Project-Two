package ch.heigvd.amt.bidirhandshake.apisspecs.steps;

import ch.heigvd.amt.bidirhandshake.apisspecs.steps.authapi.LoginSteps;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Properties;

@Service
public class World {
    Properties properties = new Properties();
    public String BASE_URL;

    public String route;
    public Object body;
    public String token;
    public HttpResponse<JsonNode> response;
    public APISteps apiSteps = new APISteps(this);
    public LoginSteps loginSteps = new LoginSteps(this);

    public World() throws IOException {
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        BASE_URL = properties.getProperty("ch.heigvd.amt.bidirhandshake.url");
    }
}
