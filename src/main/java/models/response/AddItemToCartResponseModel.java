package models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class AddItemToCartResponseModel {
    private int statusCode;
    private Map<String, String> headers;
    @JsonProperty("cart_item_id")
    private String cartItemID;
    @JsonProperty("cart_id")
    private String cartID;
    @JsonProperty("product_id")
    private String productID;
    private long price;
    private int quantity;
}
