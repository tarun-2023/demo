package clients;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserClient {

    public Response createUser(String email, String password) {
        String signupResourceEndpoint = "/api/auth/signup";
        String signupRequestBody = String.format("{\"email\": \"%s\", \"password\": \"%s\"}", email, password);

        // Act
        Response response = RestAssured.given()
                .body(signupRequestBody)
                .contentType(ContentType.JSON)
                .post(signupResourceEndpoint);

        return response;
    }

}
