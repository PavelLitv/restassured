package reqres.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUserResponseModel {

    private String
            name,
            job,
            id;
}
