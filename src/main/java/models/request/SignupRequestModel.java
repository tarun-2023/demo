package models.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupRequestModel {
    private String email;
    private String password;
}
