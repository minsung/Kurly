package ms.study.kurly.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Schema(description = "로그인 요청")
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(AccessLevel.PRIVATE)
public class LoginRequest {

    @Schema(description = "이메일 주소", defaultValue = "aa@bb.cc")
    @NotBlank
    private String email;

    @Schema(description = "비밀번호", defaultValue = "Aa12345!")
    @NotBlank
    private String password;
}
