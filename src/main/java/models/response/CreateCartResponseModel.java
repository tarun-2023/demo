package models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class CreateCartResponseModel {
    private int statusCode;
    private Map<String, String> headers;
    @JsonProperty("cart_id")
    private String cartID;
    @JsonProperty("user_id")
    private String userID;
    @JsonProperty("created_at")
    private String createdAt;
}
