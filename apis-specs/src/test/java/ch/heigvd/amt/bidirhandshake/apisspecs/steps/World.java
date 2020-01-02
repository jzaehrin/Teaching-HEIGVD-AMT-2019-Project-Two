package ch.heigvd.amt.bidirhandshake.apisspecs.steps;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.json.JSONObject;

public class World {
    final String BASE_URL = "http://localhost:8080";
    public String route;
    public Object body;
    public String token;
    HttpResponse<JsonNode> response;
}
