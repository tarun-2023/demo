package models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductFetchResponseModel {
    private int statusCode;
    private Map<String, String> headers;
    private List<ProductModel> products;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ProductModel {
        @JsonProperty("created_at")
        private String createdAt;

        private String name;
        private String description;
        private double price;
        private int quantity;
        private String id;

        @JsonProperty("category_id")
        private String categoryId;
    }
}
