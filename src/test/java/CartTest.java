import clients.CartClient;
import clients.ProductClient;
import clients.UserClient;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import models.response.AddItemToCartResponseModel;
import models.response.CreateCartResponseModel;
import models.response.ProductFetchResponseModel;
import models.response.SignupResponseModel;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;

public class CartTest extends BaseAPITest {

    @Test
    public void addItemToCart() {
        String email = UUID.randomUUID() + "@ultralesson.com";
        String password = "1234567890";

        UserClient userClient = new UserClient();
        ProductClient productClient = new ProductClient();
        CartClient cartClient = new CartClient();

        SignupResponseModel userSignupResponse = userClient.createUser(email, password);

        String accessToken = userSignupResponse.getData().getSession().getAccessToken();

        ProductFetchResponseModel productFetchResponseModel = productClient.fetchProducts(accessToken);
        String productId = productFetchResponseModel.getProducts().get(0).getId();

        CreateCartResponseModel createCartResponseModel = cartClient.createCart(accessToken);
        String cartID = createCartResponseModel.getCartID();

        AddItemToCartResponseModel addItemToCartResponseModel = cartClient.addItemCart(productId, accessToken, cartID, 10);

        // Assertions
        Assert.assertNotNull(addItemToCartResponseModel.getCartItemID());
        Assert.assertEquals( addItemToCartResponseModel.getProductID(), productId);
        Assert.assertEquals( addItemToCartResponseModel.getCartID(), cartID);
        Assert.assertEquals(addItemToCartResponseModel.getQuantity(), 10);
        Assert.assertEquals(addItemToCartResponseModel.getStatusCode(), 201);
    }

}
