package ms.study.kurly.feature.user.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(AccessLevel.PRIVATE)
public class LoginModel {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
