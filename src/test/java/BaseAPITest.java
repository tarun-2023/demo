import io.restassured.RestAssured;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import utilities.PropertyFileUtils;

public class BaseAPITest {
    @BeforeClass
    public static void setup() {
        // Base URL fetch from property file
        String BASE_URL = PropertyFileUtils.getProperty("base.url");

        // Set RestAssured configurations
        RestAssured.baseURI = BASE_URL;
    }
}