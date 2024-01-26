import clients.CartClient;
import clients.ProductClient;
import clients.UserClient;
import models.response.AddItemToCartResponseModel;
import models.response.CreateCartResponseModel;
import models.response.ProductFetchResponseModel;
import models.response.SignupResponseModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.DataLoader;

import java.util.UUID;

public class CartTest extends BaseAPITest {

    @Test
    public void addItemToCart() {
        String email = UUID.randomUUID() + "@ultralesson.com";
        String passwordFromDataLoader = DataLoader.getData("createCartData", "password");
        String productIDFromDataLoader = DataLoader.getData("createCartData", "product_id");
        int quantityFromDataLoader = Integer.parseInt (DataLoader.getData("createCartData", "quantity"));

        UserClient userClient = new UserClient();
        ProductClient productClient = new ProductClient();
        CartClient cartClient = new CartClient();

        SignupResponseModel userSignupResponse = userClient.createUser(email, passwordFromDataLoader);

        String accessToken = userSignupResponse.getData().getSession().getAccessToken();

        ProductFetchResponseModel productFetchResponseModel = productClient.fetchProducts(accessToken);
        String productId = productFetchResponseModel.getProducts().get(0).getId();

        CreateCartResponseModel createCartResponseModel = cartClient.createCart(accessToken);
        String cartID = createCartResponseModel.getCartID();

        AddItemToCartResponseModel addItemToCartResponseModel = cartClient.addItemCart(productIDFromDataLoader, accessToken, cartID, quantityFromDataLoader);

        // Assertions
        Assert.assertNotNull(addItemToCartResponseModel.getCartItemID());
        Assert.assertEquals( addItemToCartResponseModel.getProductID(), productId);
        Assert.assertEquals( addItemToCartResponseModel.getCartID(), cartID);
        Assert.assertEquals(addItemToCartResponseModel.getQuantity(), 10);
        Assert.assertEquals(addItemToCartResponseModel.getStatusCode(), 201);
    }

}
