package clients;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.response.ProductFetchResponseModel;
import utilities.ApiResponseDeserializer;
import utilities.EndpointsConfig;

public class ProductClient {

    public ProductFetchResponseModel fetchProducts(String accessToken) {
        String productFetchResourceEndpoint = EndpointsConfig.getEndpoint("product", "getProductsList");
        Response productFetchResponse = RestAssured.given()
                .header("Authorization", String.format("Bearer %s", accessToken))
                .get(productFetchResourceEndpoint);
        return ApiResponseDeserializer.deserializeResponse(productFetchResponse, ProductFetchResponseModel.class);
    }
}
