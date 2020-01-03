package ch.heigvd.amt.bidirhandshake.apisspecs.steps;

import ch.heigvd.amt.bidirhandshake.apisspecs.steps.authapi.LoginSteps;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;

public class World {
    final String BASE_URL = "http://localhost:8081/api";
    public String route;
    public Object body;
    public String token;
    public HttpResponse<JsonNode> response;
    public APISteps apiSteps = new APISteps(this);
    public LoginSteps loginSteps = new LoginSteps(this);
}
