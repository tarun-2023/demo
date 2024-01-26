package clients;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.request.SignupRequestModel;
import models.response.SignupResponseModel;

public class UserClient {

    public SignupResponseModel createUser(String email, String password) {
        String signupResourceEndpoint = "/api/auth/signup";
        SignupRequestModel signupRequestModel = SignupRequestModel.builder()
                .email(email)
                .password(password)
                .build();

        // Act
        SignupResponseModel signupResponseModel = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(signupRequestModel)
                .post(signupResourceEndpoint).as(SignupResponseModel.class);

        return signupResponseModel;
    }

}
