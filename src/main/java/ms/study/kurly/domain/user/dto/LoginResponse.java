package ms.study.kurly.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter(AccessLevel.PRIVATE)
public class LoginResponse {

    @NotBlank
    private String accessToken;

    @NotBlank
    private String refreshToken;
}
