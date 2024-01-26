package clients;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.request.AddCartItemRequestModel;
import models.response.AddItemToCartResponseModel;
import models.response.CreateCartResponseModel;
import utilities.ApiResponseDeserializer;

public class CartClient {

    public CreateCartResponseModel createCart(String accessToken) {
        String createCartEndpoint = "/api/cart";
        Response response = RestAssured.given()
                .header("Authorization", String.format("Bearer %s", accessToken))
                .contentType(ContentType.JSON)
                .post(createCartEndpoint);
        return ApiResponseDeserializer.deserializeResponse(response, CreateCartResponseModel.class);
    }

    public AddItemToCartResponseModel addItemCart(String productID, String accessToken, String cartID, int quantity) {
        String addItemToCartResourceEndpoint = String.format("/api/cart/%s/items", cartID);
        AddCartItemRequestModel addCartItemRequestModel = AddCartItemRequestModel.builder()
                .productID(productID)
                .quantity(quantity)
                .build();

        Response addItemToCartResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", String.format("Bearer %s", accessToken))
                .body(addCartItemRequestModel)
                .post(addItemToCartResourceEndpoint);
        return ApiResponseDeserializer.deserializeResponse(addItemToCartResponse, AddItemToCartResponseModel.class);
    }
}
