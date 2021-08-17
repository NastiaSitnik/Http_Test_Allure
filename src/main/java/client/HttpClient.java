package client;

import config.ServiceConfig;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;

import static io.restassured.RestAssured.*;

public class HttpClient {

    public static Response get(String endpoint) {
        return HttpClient.sendRequest(Method.GET, endpoint);
    }


    public static Response post(String endpoint, String body) {
        return HttpClient.sendRequest(Method.POST, endpoint, body);
    }

    public static Response put(String endpoint, String body) {
        return HttpClient.sendRequest(Method.PUT, endpoint, body);
    }

    public static Response delete(String endpoint) {
        return HttpClient.sendRequest(Method.DELETE, endpoint);
    }

    private static Response sendRequest(Method method, String endpoint) {
        return HttpClient.sendRequest(method, endpoint, null);
    }

    private static Response sendRequest(Method method, String endpoint, String body) {
        RestAssured.baseURI = ServiceConfig.HOST;

        RequestSpecification spec = RestAssured.given();
        spec.header("Content-Type", "application/json");
        if (body != null) spec.body(body);
        return spec.request(method, endpoint);

    }
}
