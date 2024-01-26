package utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ApiResponseDeserializer {

    public static <T> T deserializeResponse(Response response, Class<T> responseType) {
        ObjectMapper objectMapper = new ObjectMapper();
        Object jsonResponse = response.getBody().jsonPath().get();

        T responseObject;
        if (jsonResponse instanceof Map) {
            responseObject = objectMapper.convertValue(jsonResponse, responseType);
        } else {
            throw new IllegalArgumentException("Unsupported JSON structure");
        }

        // Set status code if responseType has a "statusCode" field
        setFieldIfExists(responseType, responseObject, "statusCode", response.getStatusCode());

        // Set headers if responseType has a "headers" field
        Map<String, String> headersMap = convertHeadersToMap(response.getHeaders());
        setFieldIfExists(responseType, responseObject, "headers", headersMap);

        return responseObject;
    }

    private static <T> void setFieldIfExists(Class<T> responseType, T responseObject, String fieldName, Object value) {
        try {
            Field field = responseType.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(responseObject, value);
            field.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, String> convertHeadersToMap(Headers headers) {
        Map<String, String> headersMap = new HashMap<>();
        for (Header header : headers) {
            headersMap.put(header.getName(), header.getValue());
        }
        return headersMap;
    }
}