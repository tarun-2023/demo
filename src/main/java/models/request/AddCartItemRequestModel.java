package models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddCartItemRequestModel {
    @JsonProperty("product_id")
    private String productID;
    private int quantity;
}
