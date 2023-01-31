package reqres.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserRequestModel {

    private String name, job;
}
