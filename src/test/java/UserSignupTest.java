import clients.UserClient;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.response.SignupResponseModel;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;

public class UserSignupTest extends BaseAPITest {

    @Test
    public void successfulUserRegistration() {
        // Arrange
        String email = UUID.randomUUID() + "@ultralesson.com";
        String password = "1234567890";

        // Act
        UserClient userClient = new UserClient();
        SignupResponseModel userSignupResponse = userClient.createUser(email, password);

        // Assert
//        int statusCode = userSignupResponse.getStatusCode();
//        Assert.assertEquals(statusCode, 201);
        Assert.assertEquals(userSignupResponse.getData().getUser().getEmail(), email);
    }
}
