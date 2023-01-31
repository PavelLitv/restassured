package reqres.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginUserRequestModel {

    private String email, password;
}
