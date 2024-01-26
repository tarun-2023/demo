import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserSignupTest extends BaseAPITest {

    @Test
    public void successfulUserRegistration() {
        // Arrange
        String signupResourceEndpoint = "/api/auth/signup";
        String email = "abc13kfdajc@ultralesson.com";
        String signupRequestBody = "{\"email\": \"abc13kfdajc@ultralesson.com\", \"password\": \"1234567890\"}";

        // Act
        Response response = RestAssured.given()
                .body(signupRequestBody)
                .contentType(ContentType.JSON)
                .post(signupResourceEndpoint);

        // Assert
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);
        Assert.assertEquals(response.jsonPath().get("data.user.email"), email);
    }
}
